package com.example.teacherservices.query;

import com.example.teacherservices.core.TeacherEntity;
import com.example.teacherservices.core.data.TeacherRepository;
import com.example.teacherservices.core.event.TeacherCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TeacherEventsHandler {
    private final TeacherRepository teacherRepository;

    public TeacherEventsHandler(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }
    @EventHandler
    public void on(TeacherCreatedEvent event){
        TeacherEntity teacherEntity = new TeacherEntity();
        BeanUtils.copyProperties(event, teacherEntity);
        teacherRepository.save(teacherEntity);
    }
}
