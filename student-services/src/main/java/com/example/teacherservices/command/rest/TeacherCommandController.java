package com.example.teacherservices.command.rest;

import com.example.teacherservices.command.CreateStudentCommand;
import com.example.teacherservices.command.DeleteStudentCommand;
import com.example.teacherservices.command.UpdateStudentCommand;
import com.example.teacherservices.query.rest.StudentRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students")
public class TeacherCommandController {
    private final CommandGateway commandGateway;
    @Autowired
    public TeacherCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createStudent(@RequestBody CreateStudentRestModel model){
        CreateStudentCommand command = CreateStudentCommand.builder()
                .studentId(model.getStudentId())
                .student_name(model.getStudent_name())
                .student_age(model.getStudent_age())
                .student_birth(model.getStudent_birth())
                .student_class(model.getStudent_class())
                .student_address(model.getStudent_address())
                .student_phone(model.getStudent_phone())
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
    public String updateStudent(@RequestBody StudentRestModel model){
        UpdateStudentCommand command = UpdateStudentCommand.builder()
                .studentId(model.getStudentId())
                .student_name(model.getStudent_name())
                .student_age(model.getStudent_age())
                .student_birth(model.getStudent_birth())
                .student_class(model.getStudent_class())
                .student_address(model.getStudent_address())
                .student_phone(model.getStudent_phone())
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
    public String deleteStudent(@RequestBody StudentRestModel model){
        DeleteStudentCommand command = DeleteStudentCommand.builder()
                .studentId(model.getStudentId())
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
