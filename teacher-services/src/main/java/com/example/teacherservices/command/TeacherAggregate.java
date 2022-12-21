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

import java.util.ArrayList;
import java.util.List;

@Aggregate
public class TeacherAggregate {
    @AggregateIdentifier
    private String _id;

    private String name;
    private Integer age;
    private String birth;
    private String address;
    private String phone;
    private List<String> subjects;
    private String email;

    public TeacherAggregate(){
    }

    @CommandHandler
    public TeacherAggregate(CreateTeacherCommand createTeacherCommand){
        if(createTeacherCommand.getEmail() == null || createTeacherCommand.getEmail().isBlank()){
            throw new IllegalArgumentException("Teacher Email cannot be empty");
        }
        TeacherCreatedEvent teacherCreatedEvent = new TeacherCreatedEvent();
        BeanUtils.copyProperties(createTeacherCommand, teacherCreatedEvent);
        AggregateLifecycle.apply(teacherCreatedEvent);
    }
    @CommandHandler
    public void updateTeacher(UpdateTeacherCommand updateTeacherCommand){
        if(updateTeacherCommand.getAge() <= 0 || updateTeacherCommand.getAge() > 60){
            throw new IllegalArgumentException("Age cannot be less than or equal to zero and more than 60");
        }
        if(updateTeacherCommand.getName() == null || updateTeacherCommand.getName().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(updateTeacherCommand.getPhone().length() != 10){
            throw new IllegalArgumentException("Phone must be 10 digits");
        }
        if(updateTeacherCommand.get_id() == null|| updateTeacherCommand.get_id().isBlank()){
            throw new IllegalArgumentException("TeacherID cannot be empty");
        }
        TeacherUpdatedEvent teacherUpdatedEvent = new TeacherUpdatedEvent();
        BeanUtils.copyProperties(updateTeacherCommand, teacherUpdatedEvent);
        AggregateLifecycle.apply(teacherUpdatedEvent);
    }

    @CommandHandler
    public void deleteTeacher(DeleteTeacherCommand deleteTeacherCommand){
        if(deleteTeacherCommand.get_id() == null|| deleteTeacherCommand.get_id().isBlank()){
            throw new IllegalArgumentException("TeacherID cannot be empty");
        }
        TeacherDeletedEvent teacherDeletedEvent = new TeacherDeletedEvent();
        BeanUtils.copyProperties(deleteTeacherCommand, teacherDeletedEvent);
        AggregateLifecycle.apply(teacherDeletedEvent);
    }

    @EventSourcingHandler
    public void on(TeacherCreatedEvent teacherCreatedEvent){
        this._id = teacherCreatedEvent.get_id();
        this.name = teacherCreatedEvent.getName();
        this.age = teacherCreatedEvent.getAge();
        this.birth = teacherCreatedEvent.getBirth();
        this.address = teacherCreatedEvent.getAddress();
        this.phone = teacherCreatedEvent.getPhone();
        this.subjects = teacherCreatedEvent.getSubjects();
        this.email = teacherCreatedEvent.getEmail();
    }
    @EventSourcingHandler
    public void on(TeacherUpdatedEvent teacherUpdatedEvent){
        this._id = teacherUpdatedEvent.get_id();
        this.name = teacherUpdatedEvent.getName();
        this.age = teacherUpdatedEvent.getAge();
        this.birth = teacherUpdatedEvent.getBirth();
        this.address = teacherUpdatedEvent.getAddress();
        this.phone = teacherUpdatedEvent.getPhone();
        this.subjects = teacherUpdatedEvent.getSubjects();
        this.email = teacherUpdatedEvent.getEmail();
        System.out.println("Update teacher Id: " + this._id);
    }
    @EventSourcingHandler
    public void on(TeacherDeletedEvent teacherDeletedEvent){
        this._id = teacherDeletedEvent.get_id();
        System.out.println("Delete teacher Id: " + this._id);
    }
}
