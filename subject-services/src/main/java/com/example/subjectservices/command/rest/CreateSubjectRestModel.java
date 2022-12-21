package com.example.subjectservices.command.rest;

import lombok.Data;

@Data
public class CreateSubjectRestModel {
    private String _id;
    private String subjectName;
    private Integer periodTime;
    private String teacherName;
    private String teacherId;
}
