package com.example.teacherservices.query.rest;

import com.example.teacherservices.query.FindTeacherQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherQueryController {
    @Autowired
    QueryGateway queryGateway;
    @GetMapping
    public List<TeacherRestModel> getTeachers(){
        FindTeacherQuery findTeacherQuery = new FindTeacherQuery();
        List<TeacherRestModel> teachers = queryGateway
                .query(findTeacherQuery, ResponseTypes.multipleInstancesOf(TeacherRestModel.class)).join();
        return teachers;
    }
}
