package com.example.subjectservices.query;

import com.example.subjectservices.core.SubjectEntity;
import com.example.subjectservices.core.data.SubjectRepository;
import com.example.subjectservices.query.rest.SubjectRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectQueryHandler {
    private final SubjectRepository subjectRepository;
    public SubjectQueryHandler(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }
    @QueryHandler
    List<SubjectRestModel> findSubject(FindSubjectQuery query){
        List<SubjectRestModel> subjectRest = new ArrayList<>();
        List<SubjectEntity> subjects = subjectRepository.findAll();
        for(SubjectEntity subjectEntity : subjects){
            SubjectRestModel subjectRestModel = new SubjectRestModel();
            BeanUtils.copyProperties(subjectEntity, subjectRestModel);
            subjectRest.add(subjectRestModel);
        }
        return subjectRest;
    }
}
