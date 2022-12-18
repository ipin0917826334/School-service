package com.example.teacherservices.core.data;

import com.example.teacherservices.core.TeacherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<TeacherEntity, String> {
    TeacherEntity findBy_id(String _id);
    TeacherEntity findBy_idOrName(String _id, String name);
}
