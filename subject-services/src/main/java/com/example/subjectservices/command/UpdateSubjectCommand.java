package com.example.subjectservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Builder
@Data
public class UpdateSubjectCommand {
    @TargetAggregateIdentifier
    private String subjectId;

    private String subjectName;
    private Integer periodTime;
    private String teacherName;
}
