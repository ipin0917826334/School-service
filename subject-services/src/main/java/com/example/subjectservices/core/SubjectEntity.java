package com.example.subjectservices.core;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "subjects")
@Data
public class SubjectEntity implements Serializable{

    private static final long serialVersionUID = 469746693403326764L;

    @Id
    @Column(unique = true)
    private String subjectId;
    private String subjectName;
    private Integer periodTime;
    private String teacherName;
}
