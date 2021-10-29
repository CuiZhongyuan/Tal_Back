package com.taltools.utils;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author Chris Yin
 */
public class Content {
    public static final com.taltools.utils.Content NO_CONTENT;
    private final byte[] raw;
    private final ContentType type;

    public Content(byte[] raw, ContentType type) {
        this.raw = raw;
        this.type = type;
    }

    public ContentType getType() {
        return this.type;
    }

    public byte[] asBytes() {
        return (byte[])this.raw.clone();
    }

    public String asString() {
        Charset charset = this.type.getCharset();
        if (charset == null) {
            charset = Consts.UTF_8;
        }

        return this.asString(charset);
    }

    public String asString(Charset charset) {
        return new String(this.raw, charset);
    }

    public InputStream asStream() {
        return new ByteArrayInputStream(this.raw);
    }

    public String toString() {
        return this.asString();
    }

    static {
        NO_CONTENT = new com.taltools.utils.Content(new byte[0], ContentType.DEFAULT_BINARY);
    }
}
