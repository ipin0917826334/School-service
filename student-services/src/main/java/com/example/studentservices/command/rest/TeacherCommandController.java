package com.example.studentservices.command.rest;

import com.example.studentservices.command.CreateStudentCommand;
import com.example.studentservices.command.DeleteStudentCommand;
import com.example.studentservices.command.UpdateStudentCommand;
import com.example.studentservices.query.rest.StudentRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class TeacherCommandController {
    private final CommandGateway commandGateway;
    @Autowired
    public TeacherCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public String createStudent(@RequestBody CreateStudentRestModel model){
        CreateStudentCommand command = CreateStudentCommand.builder()
                ._id(new ObjectId().toString())
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
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping
    public String updateStudent(@RequestBody StudentRestModel model){
        UpdateStudentCommand command = UpdateStudentCommand.builder()
                ._id(model.get_id())
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

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping
    public String deleteStudent(@RequestBody StudentRestModel model){
        DeleteStudentCommand command = DeleteStudentCommand.builder()
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
