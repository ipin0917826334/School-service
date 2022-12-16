package com.example.teacherservices.command.rest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTeacherRestModel {
    private String name;
    private Integer age;
    private String birth;
    private String address;
    private String phone;
}
