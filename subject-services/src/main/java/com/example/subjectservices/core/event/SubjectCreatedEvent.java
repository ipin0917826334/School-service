package com.example.subjectservices.core.event;

import lombok.Data;

@Data
public class SubjectCreatedEvent {
    private String _id;
    private String subjectName;
    private Integer periodTime;
    private String teacherName;
}
