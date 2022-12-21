package com.example.studentservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Builder
@Data
public class UpdateStudentCommand {
    @TargetAggregateIdentifier
    private final String _id;
    private final String student_name;
    private final Integer student_age;
    private final String student_birth;
    private final String student_class;
    private final String student_address;
    private final String student_phone;
}
