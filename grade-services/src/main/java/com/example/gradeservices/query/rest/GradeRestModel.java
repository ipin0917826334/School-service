package com.example.gradeservices.query.rest;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class GradeRestModel {
    private String _id;
    private String studentId;
    private String subjectName;
    private String grade;
    private String[] student;
}
