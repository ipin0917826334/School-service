package com.example.gradeservices.command;

import com.example.gradeservices.core.event.GradeCreatedEvent;
import com.example.gradeservices.core.event.GradeDeletedEvent;
import com.example.gradeservices.core.event.GradeUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class GradeAggregate {
    @AggregateIdentifier
    private String _id;
    private String studentId;
    private String subjectName;
    private String grade;

    public GradeAggregate(){
    }

    @CommandHandler
    public GradeAggregate(CreateGradeCommand createGradeCommand){
        if(createGradeCommand.getSubjectName() == null || createGradeCommand.getSubjectName().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(createGradeCommand.getStudentId() == null || createGradeCommand.getStudentId().isBlank()){
            throw new IllegalArgumentException("StudentId cannot be empty");
        }
        GradeCreatedEvent gradeCreatedEvent = new GradeCreatedEvent();
        BeanUtils.copyProperties(createGradeCommand, gradeCreatedEvent);
        AggregateLifecycle.apply(gradeCreatedEvent);
    }
    @CommandHandler
    public void updateGrade(UpdateGradeCommand updateGradeCommand){
        if(updateGradeCommand.get_id() == null || updateGradeCommand.get_id().isBlank()){
            throw new IllegalArgumentException("GradeId cannot be empty");
        }
        if(updateGradeCommand.getSubjectName() == null || updateGradeCommand.getSubjectName().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(updateGradeCommand.getStudentId() == null|| updateGradeCommand.getStudentId().isBlank()){
            throw new IllegalArgumentException("SubjectId cannot be empty");
        }
        GradeUpdatedEvent gradeUpdatedEvent = new GradeUpdatedEvent();
        BeanUtils.copyProperties(updateGradeCommand, gradeUpdatedEvent);
        AggregateLifecycle.apply(gradeUpdatedEvent);
    }

    @CommandHandler
    public void deleteGrade(DeleteGradeCommand deleteGradeCommand){
        if(deleteGradeCommand.get_id() == null|| deleteGradeCommand.get_id().isBlank()){
            throw new IllegalArgumentException("GradeId cannot be empty");
        }
        GradeDeletedEvent gradeDeletedEvent = new GradeDeletedEvent();
        BeanUtils.copyProperties(deleteGradeCommand, gradeDeletedEvent);
        AggregateLifecycle.apply(gradeDeletedEvent);
    }

    @EventSourcingHandler
    public void on(GradeCreatedEvent gradeCreatedEvent){
        this._id = gradeCreatedEvent.get_id();
        this.subjectName = gradeCreatedEvent.getSubjectName();
        this.studentId = gradeCreatedEvent.getStudentId();
        this.grade = gradeCreatedEvent.getGrade();
    }
    @EventSourcingHandler
    public void on(GradeUpdatedEvent gradeUpdatedEvent){
        this._id = gradeUpdatedEvent.get_id();
        this.subjectName = gradeUpdatedEvent.getSubjectName();
        this.studentId = gradeUpdatedEvent.getStudentId();
        this.grade = gradeUpdatedEvent.getGrade();
        System.out.println("Update grade Id: " + this._id);
    }
    @EventSourcingHandler
    public void on(GradeDeletedEvent gradeDeletedEvent){
        this._id = gradeDeletedEvent.get_id();
        System.out.println("Delete grade Id: " + this._id);
    }
}
