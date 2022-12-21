package com.example.subjectservices.query.rest;

import com.example.subjectservices.core.data.SubjectRepository;
import com.example.subjectservices.query.FindSubjectQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectQueryController {
    private final SubjectRepository subjectRepository;

    @Autowired
    QueryGateway queryGateway;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public SubjectQueryController(SubjectRepository subjectRepository) {

        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/getSubjects")
    public List<SubjectRestModel> getSubjects(@RequestBody SubjectRestModel model){
        List<SubjectRestModel> subjects = subjectRepository.findByTeacherName(model.getTeacherName());
        return subjects;
    }

    @GetMapping
    List<SubjectRestModel> findSubjectByTeacherName(@RequestBody SubjectRestModel model) throws IOException {
        List<SubjectRestModel> subjects = subjectRepository.findByTeacherName(model.getTeacherName());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        for (SubjectRestModel element : subjects) {
            out.writeUTF(element.getSubjectName());
        }
        byte[] bytes = baos.toByteArray();
        rabbitTemplate.convertAndSend("subjectExchange","teacher", bytes);
        return subjects;
    }
}
