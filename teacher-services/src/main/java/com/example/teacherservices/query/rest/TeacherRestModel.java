package com.example.teacherservices.query.rest;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TeacherRestModel {
    private String _id;
    private String name;
    private Integer age;
    private String birth;
    private String address;
    private String phone;
    private List<String> subjects;
    private String email;
}
