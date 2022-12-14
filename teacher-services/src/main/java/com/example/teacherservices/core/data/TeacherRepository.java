package com.example.teacherservices.core.data;

import com.example.teacherservices.core.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, String> {
    TeacherEntity findByTeacherId(String teacherId);
    TeacherEntity findByTeacherIdOrName(String teacherId, String name);
}
