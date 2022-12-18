package com.example.teacherservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteTeacherCommand {
    @TargetAggregateIdentifier
    private final String _id;
}
