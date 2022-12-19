package com.example.gradeservices.core.event;

import lombok.Data;

@Data
public class GradeUpdatedEvent {
    private String _id;
    private String studentId;
    private String subjectName;
    private String grade;
}
