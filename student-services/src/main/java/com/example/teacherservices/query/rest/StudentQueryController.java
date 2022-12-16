package com.example.teacherservices.query.rest;

import com.example.teacherservices.query.FindStudentQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentQueryController {
    @Autowired
    QueryGateway queryGateway;
    @GetMapping
    public List<StudentRestModel> getStudents(){
        FindStudentQuery findStudentQuery = new FindStudentQuery();
        List<StudentRestModel> students = queryGateway
                .query(findStudentQuery, ResponseTypes.multipleInstancesOf(StudentRestModel.class)).join();
        return students;
    }
}
