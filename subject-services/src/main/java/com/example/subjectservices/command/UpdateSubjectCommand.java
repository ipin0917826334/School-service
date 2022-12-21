package com.example.subjectservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.ArrayList;

@Builder
@Data
public class UpdateSubjectCommand {
    @TargetAggregateIdentifier
    private String _id;
    private String subjectName;
    private Integer periodTime;
    private String teacherName;
    private String teacherId;
}
