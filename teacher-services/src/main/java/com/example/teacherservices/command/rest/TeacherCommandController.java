package com.example.teacherservices.command.rest;

import com.example.teacherservices.command.CreateTeacherCommand;
import com.example.teacherservices.command.DeleteTeacherCommand;
import com.example.teacherservices.command.UpdateTeacherCommand;
import com.example.teacherservices.query.rest.TeacherRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.bson.types.ObjectId;
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

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public String createTeacher(@RequestBody CreateTeacherRestModel model){
        CreateTeacherCommand command = CreateTeacherCommand.builder()
                ._id(new ObjectId().toString())
                .name(model.getName())
                .age(model.getAge())
                .birth(model.getBirth())
                .address(model.getAddress())
                .phone(model.getPhone())
                .subjects(model.getSubjects())
                .email(model.getEmail())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch(Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping
    public String updateTeacher(@RequestBody TeacherRestModel model){
        UpdateTeacherCommand command = UpdateTeacherCommand.builder()
                ._id(model.get_id())
                .name(model.getName())
                .age(model.getAge())
                .birth(model.getBirth())
                .address(model.getAddress())
                .phone(model.getPhone())
                .subjects(model.getSubjects())
                .email(model.getEmail())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch(Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping
    public String deleteTeacher(@RequestBody TeacherRestModel model){
        DeleteTeacherCommand command = DeleteTeacherCommand.builder()
                ._id(model.get_id())
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
