package com.example.teacherservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Builder
@Data
public class UpdateTeacherCommand {
    @TargetAggregateIdentifier
    private final String teacherId;

    private final String name;
    private final Integer age;
    private final String birth;
    private final String address;
    private final String phone;
}
