package com.example.teacherservices.core.event;

import lombok.Data;

@Data
public class TeacherUpdatedEvent {
    private String teacherId;
    private String name;
    private Integer age;
    private String birth;
    private String address;
    private String phone;
}
