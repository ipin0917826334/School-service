package com.example.gradeservices.query.rest;

import com.example.gradeservices.query.FindGradeQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<GradeRestModel> getGrades(){
        FindGradeQuery findGradeQuery = new FindGradeQuery();
        List<GradeRestModel> grade = queryGateway
                .query(findGradeQuery, ResponseTypes.multipleInstancesOf(GradeRestModel.class)).join();
        return grade;
    }
}
