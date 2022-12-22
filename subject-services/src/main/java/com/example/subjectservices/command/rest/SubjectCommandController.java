package com.example.subjectservices.command.rest;

import com.example.subjectservices.command.CreateSubjectCommand;
import com.example.subjectservices.command.DeleteSubjectCommand;
import com.example.subjectservices.command.UpdateSubjectCommand;
import com.example.subjectservices.core.SubjectEntity;
import com.example.subjectservices.core.data.SubjectRepository;
import com.example.subjectservices.query.FindSubjectQuery;
import com.example.subjectservices.query.rest.SubjectRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subjects")
public class SubjectCommandController {
    private final CommandGateway commandGateway;

    @Autowired
    public SubjectCommandController(CommandGateway commandGateway, SubjectRepository subjectRepository){
        this.commandGateway = commandGateway;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public String createSubject(@RequestBody CreateSubjectRestModel model){
        CreateSubjectCommand command = CreateSubjectCommand.builder()
                ._id(new ObjectId().toString())
                .subjectName(model.getSubjectName())
                .periodTime(model.getPeriodTime())
                .teacherName(model.getTeacherName())
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
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping
    public String updateSubject(@RequestBody SubjectRestModel model){
        UpdateSubjectCommand command = UpdateSubjectCommand.builder()
                ._id(model.get_id())
                .subjectName(model.getSubjectName())
                .periodTime(model.getPeriodTime())
                .teacherName(model.getTeacherName())
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

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping
    public String deleteSubject(@RequestBody SubjectRestModel model){
        DeleteSubjectCommand command = DeleteSubjectCommand.builder()
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
