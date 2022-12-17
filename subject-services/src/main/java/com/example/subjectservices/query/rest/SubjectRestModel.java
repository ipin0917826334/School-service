package com.example.subjectservices.query.rest;

import lombok.Data;


@Data
public class SubjectRestModel {
    private String subjectId;
    private String subjectName;
    private Integer periodTime;
    private String teacherName;
}
