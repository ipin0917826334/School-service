package com.example.gradeservices.query;

import com.example.gradeservices.core.GradeEntity;
import com.example.gradeservices.core.data.GradeRepository;
import com.example.gradeservices.query.rest.GradeRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradeQueryHandler {
    private final GradeRepository gradeRepository;
    public GradeQueryHandler(GradeRepository gradeRepository){
        this.gradeRepository = gradeRepository;
    }
    @QueryHandler
    List<GradeRestModel> findSubject(FindGradeQuery query){
        List<GradeRestModel> subjectRest = new ArrayList<>();
        List<GradeEntity> subjects = gradeRepository.findAll();
        for(GradeEntity gradeEntity : subjects){
            GradeRestModel gradeRestModel = new GradeRestModel();
            BeanUtils.copyProperties(gradeEntity, gradeRestModel);
            subjectRest.add(gradeRestModel);
        }
        return subjectRest;
    }
}
