package com.taltools.entity;

import lombok.Data;

/**
 * @author czy
 */
@Data
public class CookieData {
    private String value;
    private String path;
    private String domain;
    private String expires;
    private String source;
}
