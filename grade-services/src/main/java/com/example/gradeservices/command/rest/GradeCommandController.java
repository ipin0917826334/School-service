package com.example.gradeservices.command.rest;

import com.example.gradeservices.command.CreateGradeCommand;
import com.example.gradeservices.command.DeleteGradeCommand;
import com.example.gradeservices.command.UpdateGradeCommand;
import com.example.gradeservices.query.rest.GradeRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.bson.types.ObjectId;
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

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public String createGrade(@RequestBody CreateGradeRestModel model){
        CreateGradeCommand command = CreateGradeCommand.builder()
                ._id(new ObjectId().toString())
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
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping
    public String updateGrade(@RequestBody GradeRestModel model){
        UpdateGradeCommand command = UpdateGradeCommand.builder()
                ._id(model.get_id())
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

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping
    public String deleteGrade(@RequestBody GradeRestModel model){
        DeleteGradeCommand command = DeleteGradeCommand.builder()
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
