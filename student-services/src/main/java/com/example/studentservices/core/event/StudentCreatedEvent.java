package com.example.studentservices.core.event;

import lombok.Data;

@Data
public class StudentCreatedEvent {
    private String _id;
    private String student_name;
    private Integer student_age;
    private String student_birth;
    private String student_class;
    private String student_address;
    private String student_phone;
}
