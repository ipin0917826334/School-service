package com.example.teacherservices.core.event;

import lombok.Data;

@Data
public class StudentDeletedEvent {
    private String studentId;
}
