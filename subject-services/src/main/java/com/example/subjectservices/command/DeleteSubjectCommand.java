package com.example.subjectservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteSubjectCommand {
    @TargetAggregateIdentifier
    private String _id;
}
