package com.example.subjectservices.query.rest;

import com.example.subjectservices.core.data.SubjectRepository;
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

    //    @GetMapping
//    public List<SubjectRestModel> getSubjects(){
//        FindSubjectQuery findSubjectQuery = new FindSubjectQuery();
//        List<SubjectRestModel> subject = queryGateway
//                .query(findSubjectQuery, ResponseTypes.multipleInstancesOf(SubjectRestModel.class)).join();
//        return subject;
//    }
//    @GetMapping
//    public List<SubjectRestModel> getSubjectsByTeacherName(){
//        FindSubjectByTeacherNameQuery findSubjectByTeacherNameQuery = new FindSubjectByTeacherNameQuery();
//        List<SubjectRestModel> subject = queryGateway
//                .query(findSubjectByTeacherNameQuery, ResponseTypes.multipleInstancesOf(SubjectRestModel.class)).join();
//        return subject;
//    }
@GetMapping
List<SubjectRestModel> findSubjectByTeacherName(@RequestBody SubjectRestModel model) throws IOException {
//        List<String> subjectRest = new ArrayList<>();
    List<SubjectRestModel> subjects = subjectRepository.findByTeacherName(model.getTeacherName());
//        subjectRest.add(subjects.toString());
//        List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
//        subjects.add(subjectRepository.findByTeacherName(model.getTeacherName()));
//        for(String subjectEntity : subjects){
//            subjectRest.add(subjects.toString());
//        }
//        System.out.print(subjects);
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
