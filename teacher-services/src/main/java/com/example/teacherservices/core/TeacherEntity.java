package com.example.teacherservices.core;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Document("teachers")
@Data
public class TeacherEntity implements Serializable{

    private static final long serialVersionUID = -4656058165108328564L;

    @Id
    private String _id;
    private String name;
    private Integer age;
    private String birth;
    private String address;
    private String phone;
    private List<String> subjects = new ArrayList<>();
    private String email;
}
