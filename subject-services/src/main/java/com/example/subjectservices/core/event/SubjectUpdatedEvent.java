package com.example.subjectservices.core.event;

import lombok.Data;

@Data
public class SubjectUpdatedEvent {
    private String subjectId;
    private String subjectName;
    private Integer periodTime;
    private String teacherName;
}
