package com.taltools.utils;

import com.taltools.test.CommonKeyValuePair;
import com.taltools.entity.CommonStepData;
import com.taltools.test.http.HttpStepHeader;
import com.taltools.test.http.HttpStepParam;
import com.taltools.test.enums.exec.TestStepType;
import com.taltools.entity.CookieData;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Chris Yin
 */
public class ExecUtil {
    public static CookieData parseCookie(String cookie) {
        if (cookie == null || cookie.isEmpty()) {
            return null;
        }
        String[] tokens = cookie.split(";");
        if (tokens.length < 1) {
            return null;
        }
        CookieData cookieData = new CookieData();
        cookieData.setSource(cookie);
        String value = tokens[0].trim();
        if (!value.isEmpty()) cookieData.setValue(value);
        Arrays.stream(tokens).skip(1).forEach(item -> {
            String[] splits = item.trim().split("=");
            if (splits.length > 1) {
                String key = splits[0];
                String val = splits[1];
                if (Objects.equals("path", key.toLowerCase())) {
                    cookieData.setPath(val);
                } else if (Objects.equals("domain", key.toLowerCase())) {
                    cookieData.setDomain(val);
                } else if (Objects.equals("expires", key.toLowerCase())) {
                    cookieData.setExpires(val);
                }
            }
        });

        return cookieData;
    }

    public static HttpStepHeader generateCookieHeader(List<CookieData> cookieDataList) {
        HttpStepHeader header = new HttpStepHeader();
        header.setKey("Cookie");
        if (cookieDataList != null && !cookieDataList.isEmpty()) {
            String value = cookieDataList.stream().map(CookieData::getValue).distinct().collect(Collectors.joining("; "));
            header.setValue(value);
        } else {
            header.setValue("");
        }
        return header;
    }

    public static TestStepType getStepType(CommonStepData commonStepData) {
        try {
            Field field = commonStepData.getClass().getDeclaredField("type");
            field.setAccessible(true);
            return TestStepType.valueOf(field.get(commonStepData).toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPathFromUri(String uri) {
        return URIUtil.getPath(uri);
    }

    public static String getQueryFromUri(String uri) {
        return URIUtil.getQuery(uri);
    }

    public static String getNameFromUri(String uri) {
        return URIUtil.getName(uri);
    }

    public static String getHostFromUri(String uri) {
        try {
            URI uriObj = URI.create(uri);
            return uriObj.getHost();
        } catch (IllegalArgumentException ex) {
            // ignore
        }

        return null;
    }

    public static String getHostFromUrl(String url) {
        try {
            URL urlObj = new URL(url);
            return urlObj.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String normalizeUri(String uri) {
        try {
            URI uriObj = URI.create(uri);
            return uriObj.normalize().toString();
        } catch (IllegalArgumentException ex) {
            // ignore
        }

        return uri;
    }

    public static String encodeParams(List<HttpStepParam> params) {
        if (params == null || params.isEmpty()) return "";
        List<NameValuePair> nameValuePairs = covert(params);
        return URLEncodedUtils.format(nameValuePairs, Consts.UTF_8);
    }

    public static List<NameValuePair> covert(List<? extends CommonKeyValuePair<String, String>> params) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        params.forEach(param -> {
            if (param.getKey() != null) {
                nameValuePairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
        });
        return nameValuePairs;
    }

    public static byte[] decodeBase64(String src) {
        return Base64.getDecoder().decode(src);
    }

    public static String encodeBase64(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
    }

    public static HttpStepHeader createHttpStepHeader(String key, String value) {
        HttpStepHeader stepHeader = new HttpStepHeader();
        stepHeader.setKey(key);
        stepHeader.setValue(value);
        return stepHeader;
    }

    public static Content handleEntity(HttpEntity entity) throws IOException {
        return entity != null ? new Content(EntityUtils.toByteArray(entity), ContentType.getOrDefault(entity)) : Content.NO_CONTENT;
    }

    public static String printExceptionStack(Exception ex) {
        return ExceptionUtils.getStackTrace(ex);
    }

    public static String digThrowableDetails(Throwable ex, int maxDeep) {
        if (ex == null) return null;
        if (maxDeep <= 0 || ex.getMessage() != null) return ex.getMessage();
        return digThrowableDetails(ex.getCause(), maxDeep - 1);
    }

    public static ContentType guessContentTypeByFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) return ContentType.DEFAULT_BINARY;
        String mimeType = null;
        try {
            mimeType = URLConnection.guessContentTypeFromName(fileName);
        } catch (Exception ignore) {}
        return mimeType != null ? ContentType.create(mimeType) : ContentType.DEFAULT_BINARY;
    }
}
