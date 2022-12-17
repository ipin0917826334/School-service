package com.example.gradeservices.core.event;

import lombok.Data;

@Data
public class GradeCreatedEvent {
    private String gradeId;
    private String studentId;
    private String subjectName;
    private String grade;
}
