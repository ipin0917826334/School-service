package com.example.teacherservices.query;

import com.example.teacherservices.core.TeacherEntity;
import com.example.teacherservices.core.data.TeacherRepository;
import com.example.teacherservices.core.event.TeacherCreatedEvent;
import com.example.teacherservices.core.event.TeacherDeletedEvent;
import com.example.teacherservices.core.event.TeacherUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
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
        System.out.println("Event "+event);
        System.out.println("Entity "+teacherEntity);
    }
    @EventHandler
    public void on(TeacherUpdatedEvent event){
        TeacherEntity teacherEntity = new TeacherEntity();
        BeanUtils.copyProperties(event, teacherEntity);
        teacherRepository.save(teacherEntity);
    }
    @EventHandler
    public void on(TeacherDeletedEvent event){
        teacherRepository.deleteById(event.get_id());
    }
}
