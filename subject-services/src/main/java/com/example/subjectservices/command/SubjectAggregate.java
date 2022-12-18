package com.example.subjectservices.command;

import com.example.subjectservices.core.event.SubjectCreatedEvent;
import com.example.subjectservices.core.event.SubjectDeletedEvent;
import com.example.subjectservices.core.event.SubjectUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class SubjectAggregate {
    @AggregateIdentifier
    private String _id;

    private String subjectName;
    private Integer periodTime;
    private String teacherName;

    public SubjectAggregate(){
    }

    @CommandHandler
    public SubjectAggregate(CreateSubjectCommand createSubjectCommand){
        if(createSubjectCommand.getSubjectName() == null || createSubjectCommand.getSubjectName().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        SubjectCreatedEvent subjectCreatedEvent = new SubjectCreatedEvent();
        BeanUtils.copyProperties(createSubjectCommand, subjectCreatedEvent);
        AggregateLifecycle.apply(subjectCreatedEvent);
    }
    @CommandHandler
    public void updateSubject(UpdateSubjectCommand updateSubjectCommand){
        if(updateSubjectCommand.getSubjectName() == null || updateSubjectCommand.getSubjectName().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(updateSubjectCommand.get_id() == null|| updateSubjectCommand.get_id().isBlank()){
            throw new IllegalArgumentException("Id cannot be empty");
        }
        SubjectUpdatedEvent subjectUpdatedEvent = new SubjectUpdatedEvent();
        BeanUtils.copyProperties(updateSubjectCommand, subjectUpdatedEvent);
        AggregateLifecycle.apply(subjectUpdatedEvent);
    }

    @CommandHandler
    public void deleteSubject(DeleteSubjectCommand deleteSubjectCommand){
        if(deleteSubjectCommand.get_id() == null|| deleteSubjectCommand.get_id().isBlank()){
            throw new IllegalArgumentException("Id() cannot be empty");
        }
        SubjectDeletedEvent subjectDeletedEvent = new SubjectDeletedEvent();
        BeanUtils.copyProperties(deleteSubjectCommand, subjectDeletedEvent);
        AggregateLifecycle.apply(subjectDeletedEvent);
    }

    @EventSourcingHandler
    public void on(SubjectCreatedEvent subjectCreatedEvent){
        this._id = subjectCreatedEvent.get_id();
        this.subjectName = subjectCreatedEvent.getSubjectName();
        this.periodTime = subjectCreatedEvent.getPeriodTime();
        this.teacherName = subjectCreatedEvent.getTeacherName();
    }
    @EventSourcingHandler
    public void on(SubjectUpdatedEvent subjectUpdatedEvent){
        this._id = subjectUpdatedEvent.get_id();
        this.subjectName = subjectUpdatedEvent.getSubjectName();
        this.periodTime = subjectUpdatedEvent.getPeriodTime();
        this.teacherName = subjectUpdatedEvent.getTeacherName();
        System.out.println("Update subject Id: " + this._id);
    }
    @EventSourcingHandler
    public void on(SubjectDeletedEvent subjectDeletedEvent){
        this._id = subjectDeletedEvent.get_id();
        System.out.println("Delete subject Id: " + this._id);
    }
}
