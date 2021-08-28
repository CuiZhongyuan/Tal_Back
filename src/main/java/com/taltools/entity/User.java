package com.taltools.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class User {
    private long id;
    private String name;
    private String pwd;

}
