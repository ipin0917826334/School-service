package com.example.teacherservices.command;

import com.example.teacherservices.core.event.StudentCreatedEvent;
import com.example.teacherservices.core.event.StudentDeletedEvent;
import com.example.teacherservices.core.event.StudentUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class StudentAggregate {
    @AggregateIdentifier
    private String studentId;
    private String student_name;
    private Integer student_age;
    private String student_birth;
    private String student_class;
    private String student_address;
    private String student_phone;

    public StudentAggregate(){
    }

    @CommandHandler
    public StudentAggregate(CreateStudentCommand createStudentCommand){
        if(createStudentCommand.getStudent_age() <= 0 || createStudentCommand.getStudent_age() > 20){
            throw new IllegalArgumentException("Age cannot be less than or equal to zero and more than 20");
        }
        if(createStudentCommand.getStudent_name() == null || createStudentCommand.getStudent_name().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(createStudentCommand.getStudent_class() == null || createStudentCommand.getStudent_class().isBlank()){
            throw new IllegalArgumentException("Class cannot be empty");
        }
        if(createStudentCommand.getStudent_phone().length() != 10){
            throw new IllegalArgumentException("Phone must be 10 digits");
        }
        StudentCreatedEvent studentCreatedEvent = new StudentCreatedEvent();
        BeanUtils.copyProperties(createStudentCommand, studentCreatedEvent);
        AggregateLifecycle.apply(studentCreatedEvent);
    }
    @CommandHandler
    public void updateStudent(UpdateStudentCommand updateStudentCommand){
        if(updateStudentCommand.getStudent_age() <= 0 || updateStudentCommand.getStudent_age() > 20){
            throw new IllegalArgumentException("Age cannot be less than or equal to zero and more than 60");
        }
        if(updateStudentCommand.getStudent_name() == null || updateStudentCommand.getStudent_name().isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(updateStudentCommand.getStudent_class() == null || updateStudentCommand.getStudent_class().isBlank()){
            throw new IllegalArgumentException("Class cannot be empty");
        }
        if(updateStudentCommand.getStudent_phone().length() != 10){
            throw new IllegalArgumentException("Phone must be 10 digits");
        }
        if(updateStudentCommand.getStudentId() == null|| updateStudentCommand.getStudentId().isBlank()){
            throw new IllegalArgumentException("StudentID cannot be empty");
        }
        StudentUpdatedEvent studentUpdatedEvent = new StudentUpdatedEvent();
        BeanUtils.copyProperties(updateStudentCommand, studentUpdatedEvent);
        AggregateLifecycle.apply(studentUpdatedEvent);
    }

    @CommandHandler
    public void deleteStudent(DeleteStudentCommand deleteStudentCommand){
        if(deleteStudentCommand.getStudentId() == null|| deleteStudentCommand.getStudentId().isBlank()){
            throw new IllegalArgumentException("StudentID cannot be empty");
        }
        StudentDeletedEvent studentDeletedEvent = new StudentDeletedEvent();
        BeanUtils.copyProperties(deleteStudentCommand, studentDeletedEvent);
        AggregateLifecycle.apply(studentDeletedEvent);
    }

    @EventSourcingHandler
    public void on(StudentCreatedEvent studentCreatedEvent){
        this.studentId = studentCreatedEvent.getStudentId();
        this.student_name = studentCreatedEvent.getStudent_name();
        this.student_age = studentCreatedEvent.getStudent_age();
        this.student_birth = studentCreatedEvent.getStudent_birth();
        this.student_class = studentCreatedEvent.getStudent_class();
        this.student_address = studentCreatedEvent.getStudent_address();
        this.student_phone = studentCreatedEvent.getStudent_phone();
        System.out.println("Create teacher Id: " + this.studentId);
    }
    @EventSourcingHandler
    public void on(StudentUpdatedEvent studentUpdatedEvent){
        this.studentId = studentUpdatedEvent.getStudentId();
        this.student_name = studentUpdatedEvent.getStudent_name();
        this.student_age = studentUpdatedEvent.getStudent_age();
        this.student_birth = studentUpdatedEvent.getStudent_birth();
        this.student_class = studentUpdatedEvent.getStudent_class();
        this.student_address = studentUpdatedEvent.getStudent_address();
        this.student_phone = studentUpdatedEvent.getStudent_phone();
        System.out.println("Update teacher Id: " + this.studentId);
    }
    @EventSourcingHandler
    public void on(StudentDeletedEvent studentDeletedEvent){
        this.studentId = studentDeletedEvent.getStudentId();
        System.out.println("Delete teacher Id: " + this.studentId);
    }
}
