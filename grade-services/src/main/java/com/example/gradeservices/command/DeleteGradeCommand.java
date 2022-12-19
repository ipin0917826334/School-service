package com.example.gradeservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteGradeCommand {
    @TargetAggregateIdentifier
    private String _id;
}
