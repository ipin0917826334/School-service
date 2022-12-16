package com.example.teacherservices.core;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "teachers")
@Data
public class StudentEntity implements Serializable{

    private static final long serialVersionUID = -4656058165108328564L;
    @Id
    @Column(unique = true)
    private String studentId;
    private String student_name;
    private Integer student_age;
    private String student_birth;
    private String student_class;
    private String student_address;
    private String student_phone;
}
