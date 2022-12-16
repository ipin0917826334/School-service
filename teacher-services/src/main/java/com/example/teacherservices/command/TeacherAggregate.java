package com.example.teacherservices.command;

import com.example.teacherservices.core.event.TeacherCreatedEvent;
import com.example.teacherservices.core.event.TeacherDeletedEvent;
import com.example.teacherservices.core.event.TeacherUpdatedEvent;
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
    private String address;
    private String phone;

    public TeacherAggregate(){
    }

    @CommandHandler
    public TeacherAggregate(CreateTeacherCommand createTeacherCommand){
        if(createTeacherCommand.getAge() <= 0 && createTeacherCommand.getAge() > 60){
            throw new IllegalArgumentException("Age cannot be less than or equal to zero and more than 60");
        }
        if(createTeacherCommand.getName() == null || createTeacherCommand.getName().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(createTeacherCommand.getPhone().length() != 10){
            throw new IllegalArgumentException("Phone must be 10 digits");
        }
        TeacherCreatedEvent teacherCreatedEvent = new TeacherCreatedEvent();
        BeanUtils.copyProperties(createTeacherCommand, teacherCreatedEvent);
        AggregateLifecycle.apply(teacherCreatedEvent);
    }
    @CommandHandler
    public TeacherAggregate(UpdateTeacherCommand updateTeacherCommand){
        TeacherUpdatedEvent teacherUpdatedEvent = new TeacherUpdatedEvent();
        BeanUtils.copyProperties(updateTeacherCommand, teacherUpdatedEvent);
        AggregateLifecycle.apply(teacherUpdatedEvent);
        System.out.println("hello");
    }

    @CommandHandler
    public TeacherAggregate(DeleteTeacherCommand deleteTeacherCommand){
        TeacherDeletedEvent teacherDeletedEvent = new TeacherDeletedEvent();
        BeanUtils.copyProperties(deleteTeacherCommand, teacherDeletedEvent);
        AggregateLifecycle.apply(teacherDeletedEvent);
    }

    @EventSourcingHandler
    public void on(TeacherCreatedEvent teacherCreatedEvent){
        this.teacherId = teacherCreatedEvent.getTeacherId();
        this.name = teacherCreatedEvent.getName();
        this.age = teacherCreatedEvent.getAge();
        this.birth = teacherCreatedEvent.getBirth();
        this.address = teacherCreatedEvent.getAddress();
        this.phone = teacherCreatedEvent.getPhone();
    }
    @EventSourcingHandler
    public void on(TeacherUpdatedEvent teacherUpdatedEvent){
        this.teacherId = teacherUpdatedEvent.getTeacherId();
        this.name = teacherUpdatedEvent.getName();
        this.age = teacherUpdatedEvent.getAge();
        this.birth = teacherUpdatedEvent.getBirth();
        this.address = teacherUpdatedEvent.getAddress();
        this.phone = teacherUpdatedEvent.getPhone();
        System.out.println("Update teacher Id: " + this.teacherId);
    }
    @EventSourcingHandler
    public void on(TeacherDeletedEvent teacherDeletedEvent){
        this.teacherId = teacherDeletedEvent.getTeacherId();
        System.out.println("Delete teacher Id: " + this.teacherId);
    }
}
