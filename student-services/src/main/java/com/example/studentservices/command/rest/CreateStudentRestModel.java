package com.example.studentservices.command.rest;

import lombok.Data;

@Data
public class CreateStudentRestModel {
    private String _id;
    private String student_name;
    private Integer student_age;
    private String student_birth;
    private String student_class;
    private String student_address;
    private String student_phone;
}
