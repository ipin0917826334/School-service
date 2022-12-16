package com.example.teacherservices.core.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TeacherCreatedEvent {
    private String teacherId;
    private String name;
    private Integer age;
    private String birth;
    private String address;
    private String phone;
}
