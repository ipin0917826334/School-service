package com.example.teacherservices.query;

import com.example.teacherservices.core.StudentEntity;
import com.example.teacherservices.core.data.StudentRepository;
import com.example.teacherservices.query.rest.StudentRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentQueryHandler {
    private final StudentRepository studentRepository;
    public StudentQueryHandler(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @QueryHandler
    List<StudentRestModel> findStudent(FindStudentQuery query){
        List<StudentRestModel> studentRest = new ArrayList<>();
        List<StudentEntity> students = studentRepository.findAll();
        for(StudentEntity studentEntity : students){
            StudentRestModel studentRestModel = new StudentRestModel();
            BeanUtils.copyProperties(studentEntity, studentRestModel);
            studentRest.add(studentRestModel);
        }
        return studentRest;
    }
}
