package com.example.teacherservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteStudentCommand {
    @TargetAggregateIdentifier
    private final String studentId;
}
