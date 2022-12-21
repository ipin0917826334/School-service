package com.example.teacherservices.query.rest;

import com.example.teacherservices.core.data.TeacherRepository;
import com.example.teacherservices.query.FindTeacherQuery;
import com.example.teacherservices.query.UpdateTeacherQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherQueryController {
    @Autowired
    QueryGateway queryGateway;
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    public List<TeacherRestModel> getTeachers(){
        FindTeacherQuery findTeacherQuery = new FindTeacherQuery();
        List<TeacherRestModel> teachers = queryGateway
                .query(findTeacherQuery, ResponseTypes.multipleInstancesOf(TeacherRestModel.class)).join();
        return teachers;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/getTeacher")
    public List<TeacherRestModel> getTeachers(@RequestBody TeacherRestModel model){
        List<TeacherRestModel> teachers = teacherRepository.findByEmail(model.getEmail());
        return teachers;
    }
}
