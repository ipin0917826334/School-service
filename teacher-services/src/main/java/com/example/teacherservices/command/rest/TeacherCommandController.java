package com.example.teacherservices.command.rest;

import com.example.teacherservices.command.CreateTeacherCommand;
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
    public String deleteTeacher(){
        return  "teacher deleted";
    }

}
