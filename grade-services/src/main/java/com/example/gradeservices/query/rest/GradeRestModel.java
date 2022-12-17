package com.example.gradeservices.query.rest;

import lombok.Data;


@Data
public class GradeRestModel {
    private String gradeId;
    private String studentId;
    private String subjectName;
    private String grade;
}
