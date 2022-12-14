package com.example.teacherservices.command;

import com.example.teacherservices.core.event.TeacherCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class TeacherAggregate {
    @AggregateIdentifier
    private String teacherId;
    private String name;
    private Integer age;
    private String birth;

    public TeacherAggregate(){
    }
    @CommandHandler
    public TeacherAggregate(CreateTeacherCommand createTeacherCommand){
//        if(createTeacherCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0){
//            throw new IllegalArgumentException("Price cannot be less than or equal to zero");
//        }
//        if(createTeacherCommand.getTitle() == null || createTeacherCommand.getTitle().isBlank()){
//            throw new IllegalArgumentException("Title cannot be empty");
//        }
        TeacherCreatedEvent teacherCreatedEvent = new TeacherCreatedEvent();
        BeanUtils.copyProperties(createTeacherCommand, teacherCreatedEvent);
        AggregateLifecycle.apply(teacherCreatedEvent);
    }
    @EventSourcingHandler
    public void on(TeacherCreatedEvent teacherCreatedEvent){
        this.teacherId = teacherCreatedEvent.getTeacherId();
        this.name = teacherCreatedEvent.getName();
        this.age = teacherCreatedEvent.getAge();
        this.birth = teacherCreatedEvent.getBirth();
    }
}
