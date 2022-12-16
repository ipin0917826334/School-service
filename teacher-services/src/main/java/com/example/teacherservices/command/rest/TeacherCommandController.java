package com.example.teacherservices.command.rest;

import com.example.teacherservices.command.CreateTeacherCommand;
import com.example.teacherservices.command.DeleteTeacherCommand;
import com.example.teacherservices.command.UpdateTeacherCommand;
import com.example.teacherservices.query.rest.TeacherRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/teachers")
public class TeacherCommandController {
    private final CommandGateway commandGateway;
    @Autowired
    public TeacherCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createTeacher(@RequestBody CreateTeacherRestModel model){
        CreateTeacherCommand command = CreateTeacherCommand.builder()
                .teacherId(UUID.randomUUID().toString())
                .name(model.getName())
                .age(model.getAge())
                .birth(model.getBirth())
                .address(model.getAddress())
                .phone(model.getPhone())
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
    public String updateTeacher(@RequestBody TeacherRestModel model){
        UpdateTeacherCommand command = UpdateTeacherCommand.builder()
                .teacherId(model.getTeacherId())
                .name(model.getName())
                .age(model.getAge())
                .birth(model.getBirth())
                .address(model.getAddress())
                .phone(model.getPhone())
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
    public String deleteTeacher(@RequestBody TeacherRestModel model){
        DeleteTeacherCommand command = DeleteTeacherCommand.builder()
                .teacherId(model.getTeacherId())
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
