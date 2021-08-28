package com.taltools.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Data
public class UserEntity implements Serializable {
    private String id;
    private String name;
    private String pwd;
}
