package com.example.studentservices.core;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Document("students")
@Data
public class StudentEntity implements Serializable{

    private static final long serialVersionUID = -4656058165108328564L;
    @Id
    @Column(unique = true)
    private String _id;
    private String student_name;
    private Integer student_age;
    private String student_birth;
    private String student_class;
    private String student_address;
    private String student_phone;
}
