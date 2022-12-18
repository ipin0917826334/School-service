package com.example.teacherservices.query;

import com.example.teacherservices.core.TeacherEntity;
import com.example.teacherservices.core.data.TeacherRepository;
import com.example.teacherservices.query.rest.TeacherRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherQueryHandler {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final TeacherRepository teacherRepository;
    public TeacherQueryHandler(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }
    @QueryHandler
    List<TeacherRestModel> findTeacher(FindTeacherQuery query) throws IOException {
        List<TeacherRestModel> teacherRest = new ArrayList<>();
        List<TeacherEntity> teachers = teacherRepository.findAll();
        for(TeacherEntity teacherEntity : teachers){
            TeacherRestModel teacherRestModel = new TeacherRestModel();
            BeanUtils.copyProperties(teacherEntity, teacherRestModel);
            teacherRest.add(teacherRestModel);
        }
        System.out.println("finish");
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        DataOutputStream out = new DataOutputStream(baos);
//
//        for (TeacherRestModel element : teacherRest) {
//            out.writeUTF(String.valueOf(element));
//        }
//        byte[] bytes = baos.toByteArray();
//        rabbitTemplate.convertAndSend("teacherExchange","subject", bytes);
        return teacherRest;
    }
}
