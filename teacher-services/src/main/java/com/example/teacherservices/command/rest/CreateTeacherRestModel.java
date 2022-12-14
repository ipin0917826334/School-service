package com.example.teacherservices.command.rest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTeacherRestModel {
    private String teacherId;
    private String name;
    private Integer age;
    private String birth;
}
