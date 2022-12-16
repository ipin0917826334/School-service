package com.example.teacherservices.core.event;

import lombok.Data;

@Data
public class StudentCreatedEvent {
    private String studentId;
    private String student_name;
    private Integer student_age;
    private String student_birth;
    private String student_class;
    private String student_address;
    private String student_phone;
}
