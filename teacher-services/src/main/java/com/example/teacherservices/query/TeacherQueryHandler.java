package com.example.teacherservices.query;

import com.example.teacherservices.core.TeacherEntity;
import com.example.teacherservices.core.data.TeacherRepository;
import com.example.teacherservices.query.rest.TeacherRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherQueryHandler {
    private final TeacherRepository teacherRepository;
    public TeacherQueryHandler(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }
    @QueryHandler
    List<TeacherRestModel> findTeacher(FindTeacherQuery query){
        List<TeacherRestModel> teacherRest = new ArrayList<>();
        List<TeacherEntity> teachers = teacherRepository.findAll();
        for(TeacherEntity teacherEntity : teachers){
            TeacherRestModel teacherRestModel = new TeacherRestModel();
            BeanUtils.copyProperties(teacherEntity, teacherRestModel);
            teacherRest.add(teacherRestModel);
        }
        return teacherRest;
    }
}
