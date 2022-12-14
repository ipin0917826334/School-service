package com.example.teacherservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Builder
@Data
public class CreateTeacherCommand {
    @TargetAggregateIdentifier
    private final String teacherId;
    private final String name;
    private final Integer age;
    private final String birth;
}
