package com.example.subjectservices.core.data;

import com.example.subjectservices.core.SubjectEntity;
import com.example.subjectservices.query.rest.SubjectRestModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends MongoRepository<SubjectEntity, String> {
    SubjectEntity findBy_id(String _id);
    @Query(value = "{ 'teacherName' : ?0 }", fields="{'subjectName':1}")
    List<SubjectRestModel> findByTeacherName(String teacherName);
    SubjectEntity findBy_idOrSubjectName(String _id, String subjectName);
}
