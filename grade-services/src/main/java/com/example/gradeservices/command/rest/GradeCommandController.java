package com.example.gradeservices.command.rest;

import com.example.gradeservices.command.CreateGradeCommand;
import com.example.gradeservices.command.DeleteGradeCommand;
import com.example.gradeservices.command.UpdateGradeCommand;
import com.example.gradeservices.query.rest.GradeRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/grades")
public class GradeCommandController {
    private final CommandGateway commandGateway;
    @Autowired
    public GradeCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createGrade(@RequestBody CreateGradeRestModel model){
        CreateGradeCommand command = CreateGradeCommand.builder()
                .gradeId(UUID.randomUUID().toString())
                .studentId(model.getStudentId())
                .subjectName(model.getSubjectName())
                .grade(model.getGrade())
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
    public String updateGrade(@RequestBody GradeRestModel model){
        UpdateGradeCommand command = UpdateGradeCommand.builder()
                .gradeId(model.getGradeId())
                .studentId(model.getStudentId())
                .subjectName(model.getSubjectName())
                .grade(model.getGrade())
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
    public String deleteGrade(@RequestBody GradeRestModel model){
        DeleteGradeCommand command = DeleteGradeCommand.builder()
                .gradeId(model.getGradeId())
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
