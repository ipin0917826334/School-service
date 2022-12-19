package com.example.studentservices.core.data;

import com.example.studentservices.core.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<StudentEntity, String> {
    StudentEntity findBy_id(String studentId);
//    StudentEntity findByStudent_IdOrAndStudent_name(String studentId, String student_name);
}
