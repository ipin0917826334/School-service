package com.example.subjectservices.command.rest;

import com.example.subjectservices.command.CreateSubjectCommand;
import com.example.subjectservices.command.DeleteSubjectCommand;
import com.example.subjectservices.command.UpdateSubjectCommand;
import com.example.subjectservices.query.rest.SubjectRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subjects")
public class SubjectCommandController {
    private final CommandGateway commandGateway;
    @Autowired
    public SubjectCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createSubject(@RequestBody CreateSubjectRestModel model){
        CreateSubjectCommand command = CreateSubjectCommand.builder()
                .subjectId(UUID.randomUUID().toString())
                .subjectName(model.getSubjectName())
                .periodTime(model.getPeriodTime())
                .teacherName(model.getTeacherName())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch(Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
    @PutMapping
    public String updateSubject(@RequestBody SubjectRestModel model){
        UpdateSubjectCommand command = UpdateSubjectCommand.builder()
                .subjectId(model.getSubjectId())
                .subjectName(model.getSubjectName())
                .periodTime(model.getPeriodTime())
                .teacherName(model.getTeacherName())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch(Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @DeleteMapping
    public String deleteSubject(@RequestBody SubjectRestModel model){
        DeleteSubjectCommand command = DeleteSubjectCommand.builder()
                .subjectId(model.getSubjectId())
                .build();

        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch(Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

}
