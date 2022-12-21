package com.example.teacherservices.core.data;

import com.example.teacherservices.core.TeacherEntity;
import com.example.teacherservices.query.rest.TeacherRestModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends MongoRepository<TeacherEntity, String> {
    TeacherEntity findBy_id(String _id);
    TeacherEntity findBy_idOrName(String _id, String name);

    @Query(value = "{ 'email' : ?0 }")
    List<TeacherRestModel> findByEmail(String email);
}
