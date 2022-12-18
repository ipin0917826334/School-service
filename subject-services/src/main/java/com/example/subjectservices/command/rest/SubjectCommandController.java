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
    private final SubjectRepository subjectRepository;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
    @Autowired
    public SubjectCommandController(CommandGateway commandGateway, SubjectRepository subjectRepository){
        this.commandGateway = commandGateway;
        this.subjectRepository = subjectRepository;
    }

    @PostMapping
    public String createSubject(@RequestBody CreateSubjectRestModel model){
        CreateSubjectCommand command = CreateSubjectCommand.builder()
                ._id(new ObjectId().toString())
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
                ._id(model.get_id())
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
//    @GetMapping
//    List<String> findSubjectByTeacherName(@RequestBody SubjectRestModel model) throws IOException {
////        List<String> subjectRest = new ArrayList<>();
//        List<String> subjects = subjectRepository.findByTeacherName(model.getTeacherName());
////        subjectRest.add(subjects.toString());
////        List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
////        subjects.add(subjectRepository.findByTeacherName(model.getTeacherName()));
////        for(String subjectEntity : subjects){
////            subjectRest.add(subjects.toString());
////        }
////        System.out.print(subjects);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        DataOutputStream out = new DataOutputStream(baos);
//
//        for (String element : subjects) {
//            out.writeUTF(element);
//        }
//        byte[] bytes = baos.toByteArray();
//        rabbitTemplate.convertAndSend("subjectExchange","teacher", bytes);
//        return subjects;
//    }
}
