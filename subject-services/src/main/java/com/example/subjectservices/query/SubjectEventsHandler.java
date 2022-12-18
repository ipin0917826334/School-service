package com.example.subjectservices.query;

import com.example.subjectservices.core.SubjectEntity;
import com.example.subjectservices.core.data.SubjectRepository;
import com.example.subjectservices.core.event.SubjectCreatedEvent;
import com.example.subjectservices.core.event.SubjectDeletedEvent;
import com.example.subjectservices.core.event.SubjectUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SubjectEventsHandler {
    private final SubjectRepository subjectRepository;

    public SubjectEventsHandler(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }
    @EventHandler
    public void on(SubjectCreatedEvent event){
        SubjectEntity subjectEntity = new SubjectEntity();
        BeanUtils.copyProperties(event, subjectEntity);
        subjectRepository.save(subjectEntity);
    }
    @EventHandler
    public void on(SubjectUpdatedEvent event){
        SubjectEntity subjectEntity = new SubjectEntity();
        BeanUtils.copyProperties(event, subjectEntity);
        subjectRepository.save(subjectEntity);
    }
    @EventHandler
    public void on(SubjectDeletedEvent event){
        subjectRepository.deleteById(event.get_id());
    }
}
