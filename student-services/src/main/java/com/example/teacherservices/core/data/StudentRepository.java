package com.example.teacherservices.core.data;

import com.example.teacherservices.core.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, String> {
    StudentEntity findByStudentId(String studentId);
//    StudentEntity findByStudent_IdOrAndStudent_name(String studentId, String student_name);
}
