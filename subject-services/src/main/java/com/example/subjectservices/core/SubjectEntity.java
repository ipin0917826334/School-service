package com.example.subjectservices.core;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Document("subjects")
@Data
public class SubjectEntity implements Serializable{

    private static final long serialVersionUID = 469746693403326764L;

    @Id
    @Column(unique = true)
    private String _id;
    private String subjectName;
    private Integer periodTime;
    private String teacherName;
}
