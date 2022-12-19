package com.example.studentservices.query;

import com.example.studentservices.core.StudentEntity;
import com.example.studentservices.core.data.StudentRepository;
import com.example.studentservices.core.event.StudentCreatedEvent;
import com.example.studentservices.core.event.StudentDeletedEvent;
import com.example.studentservices.core.event.StudentUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StudentEventsHandler {
    private final StudentRepository studentRepository;

    public StudentEventsHandler(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @EventHandler
    public void on(StudentCreatedEvent event){
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(event, studentEntity);
        studentRepository.save(studentEntity);
    }
    @EventHandler
    public void on(StudentUpdatedEvent event){
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(event, studentEntity);
        studentRepository.save(studentEntity);
    }
    @EventHandler
    public void on(StudentDeletedEvent event){
        StudentEntity data1 = studentRepository.findBy_id(event.get_id());
        studentRepository.delete(data1);
    }
}
