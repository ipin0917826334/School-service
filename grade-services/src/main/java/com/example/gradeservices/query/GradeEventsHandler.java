package com.example.gradeservices.query;

import com.example.gradeservices.core.GradeEntity;
import com.example.gradeservices.core.data.GradeRepository;
import com.example.gradeservices.core.event.GradeCreatedEvent;
import com.example.gradeservices.core.event.GradeDeletedEvent;
import com.example.gradeservices.core.event.GradeUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GradeEventsHandler {
    private final GradeRepository gradeRepository;

    public GradeEventsHandler(GradeRepository gradeRepository){
        this.gradeRepository = gradeRepository;
    }
    @EventHandler
    public void on(GradeCreatedEvent event){
        GradeEntity gradeEntity = new GradeEntity();
        BeanUtils.copyProperties(event, gradeEntity);
        gradeRepository.save(gradeEntity);
    }
    @EventHandler
    public void on(GradeUpdatedEvent event){
        GradeEntity gradeEntity = new GradeEntity();
        BeanUtils.copyProperties(event, gradeEntity);
        gradeRepository.save(gradeEntity);
    }
    @EventHandler
    public void on(GradeDeletedEvent event){
        gradeRepository.deleteById(event.getGradeId());
    }
}
