package com.example.teacherservices.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class CreateTeacherCommand {
    @TargetAggregateIdentifier
    private final String _id;

    private final String name;
    private final Integer age;
    private final String birth;
    private final String address;
    private final String phone;
    private final List<String> subjects;
}
