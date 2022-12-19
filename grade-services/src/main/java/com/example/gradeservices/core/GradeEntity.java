package com.example.gradeservices.core;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Document("grades")
@Data
public class GradeEntity implements Serializable{

    private static final long serialVersionUID = 7856890615154046145L;

    @Id
    @Column(unique = true)
    private String _id;
    private String studentId;
    private String subjectName;
    private String grade;
}
