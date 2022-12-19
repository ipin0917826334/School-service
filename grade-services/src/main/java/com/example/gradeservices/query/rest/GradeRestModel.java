package com.example.gradeservices.query.rest;

import lombok.Data;


@Data
public class GradeRestModel {
    private String _id;
    private String studentId;
    private String subjectName;
    private String grade;
}
