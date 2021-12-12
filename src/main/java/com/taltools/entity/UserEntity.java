package com.taltools.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Data
public class UserEntity implements Serializable {
    private String id;
    private String school;
    private String name;
    private String createTime;
    private String testResult;
    private String remark;
}
