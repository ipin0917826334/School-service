package com.example.subjectservices.query.rest;

import com.example.subjectservices.query.FindSubjectQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<SubjectRestModel> getSubjects(){
        FindSubjectQuery findSubjectQuery = new FindSubjectQuery();
        List<SubjectRestModel> subject = queryGateway
                .query(findSubjectQuery, ResponseTypes.multipleInstancesOf(SubjectRestModel.class)).join();
        return subject;
    }
}
