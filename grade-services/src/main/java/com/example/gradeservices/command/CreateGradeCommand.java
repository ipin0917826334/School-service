package com.example.gradeservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CreateGradeCommand {
    @TargetAggregateIdentifier
    private String _id;
    private String studentId;
    private String subjectName;
    private String grade;
}
