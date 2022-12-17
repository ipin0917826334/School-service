package com.example.gradeservices.command.rest;

import lombok.Data;

@Data
public class CreateGradeRestModel {
    private String studentId;
    private String subjectName;
    private String grade;
}
