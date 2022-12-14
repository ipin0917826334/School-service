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
public class TeacherEntity implements Serializable{
    private static final long serialVersionUID = -4703102532579738583L;

    @Id
    @Column(unique = true)
    private String teacherId;
    private String name;
    private Integer age;
    private String birth;
}
