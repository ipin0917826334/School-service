package com.example.teacherservices.core.event;

import lombok.Data;

import java.util.List;

@Data
public class TeacherUpdatedEvent {
    private String _id;
    private String name;
    private Integer age;
    private String birth;
    private String address;
    private String phone;
    private List<String> subjects;
    private String email;
}
