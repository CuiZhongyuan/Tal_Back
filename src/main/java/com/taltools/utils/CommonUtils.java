package com.taltools.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class CommonUtils {
    private static final Map<Character, String> needEncoding = new HashMap<>();

    static {
        needEncoding.put(' ', "%20");
        needEncoding.put('"', "%22");
        needEncoding.put('#', "%23");
        needEncoding.put('%', "%25");
        needEncoding.put('&', "%26");
        needEncoding.put('(', "%28");
        needEncoding.put(')', "%29");
        needEncoding.put('+', "%2B");
        needEncoding.put(',', "%2C");
        needEncoding.put('/', "%2F");
        needEncoding.put(':', "%2A");
        needEncoding.put(';', "%3B");
        needEncoding.put('<', "%3C");
        needEncoding.put('=', "%3D");
        needEncoding.put('>', "%3E");
        needEncoding.put('?', "%3F");
        needEncoding.put('@', "%40");
        needEncoding.put('\\', "%5C");
        needEncoding.put('|', "%7C");
    }

    public static String encodeString(String source) {
        if (source == null || source.isEmpty()) return source;
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (needEncoding.containsKey(c)) {
                out.append(needEncoding.get(c));
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    public static final SerializerFeature[] SERIALIZER_FEATURE = {
            SerializerFeature.WriteClassName,
    };

    public static <T> T as(Object obj, Class<T> cls) {
        return cls.isInstance(obj) ? cls.cast(obj) : null;
    }

    public static <T> T checkAndAs(Object obj, Class<T> cls) {
        return cls.cast(obj);
    }

    public static Request createGetRequest(String uri) {
        return createBodyRequest("GET", uri);
    }

    public static Request createBodyRequest(String method, String uri) {
        try {
            return createBodyRequest(method, URI.create(uri));
        } catch (Exception e) {
            throw new RuntimeException("创建带body的Request失败", e);
        }
    }

    public static Request createBodyRequest(String method, URI uri) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Request> requestClass = Request.class;
        Class<?> internalHttpRequestClass = Class.forName("org.apache.http.client.fluent.InternalHttpRequest");
        Class<?> internalEntityEnclosingHttpRequestClass = Class.forName("org.apache.http.client.fluent.InternalEntityEnclosingHttpRequest");

        Constructor<?> internalEntityEnclosingHttpRequestClassConstructor = internalEntityEnclosingHttpRequestClass.getDeclaredConstructor(String.class, URI.class);
        Constructor<?> requestClassConstructor = requestClass.getDeclaredConstructor(internalHttpRequestClass);

        internalEntityEnclosingHttpRequestClassConstructor.setAccessible(true);
        Object httpRequest = internalEntityEnclosingHttpRequestClassConstructor.newInstance(method, uri);

        requestClassConstructor.setAccessible(true);
        Object request = requestClassConstructor.newInstance(httpRequest);
        return as(request, Request.class);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.warn(e.getMessage(), e);
        }
    }

    public static <T> T cloneObject(T obj, Class<T> clazz) {
        if (obj == null) return null;
        String json = JSONObject.toJSONString(obj, SERIALIZER_FEATURE);
        return JSONObject.parseObject(json, clazz);
    }

    public static String extractParamFromQuery(URI uri, String paramName) {
        if (uri == null || paramName == null) return null;
        List<NameValuePair> requestParams = URLEncodedUtils.parse(uri, Consts.UTF_8);
        String ret = null;
        if (requestParams != null && !requestParams.isEmpty()) {
            ret = requestParams.stream().filter(nameValuePair -> paramName.equals(nameValuePair.getName())).findFirst().orElse(new BasicNameValuePair(paramName, null)).getValue();
        }
        return ret;
    }

    public static String extractNameFromUri(String uri) {
        return URIUtil.getName(uri);
    }

    public static String extractPathFromUri(String uri) {
        return URIUtil.getPath(uri);
    }

    public static String generateUUIDString() {
        return UUID.randomUUID().toString();
    }

    public static class DBConnStringBuilder {
        public static final String JDBC_PREFIX = "jdbc:";
        private String schema;
        private String host;
        private int port;
        private String dbName;

        public static DBConnStringBuilder newBuilder() {
            return new DBConnStringBuilder();
        }

        public DBConnStringBuilder schema(String schema) {
            this.schema = schema;
            return this;
        }

        public DBConnStringBuilder host(String host) {
            this.host = host;
            return this;
        }

        public DBConnStringBuilder port(int port) {
            this.port = port;
            return this;
        }

        public DBConnStringBuilder dbName(String dbName) {
            this.dbName = dbName;
            return this;
        }

        public String build() {
            return JDBC_PREFIX + schema + "://" + host + ":" + port + "/" + dbName;
        }
    }
}
