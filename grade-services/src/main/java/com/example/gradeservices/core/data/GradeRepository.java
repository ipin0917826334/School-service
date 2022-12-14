package com.example.gradeservices.core.data;

import com.example.gradeservices.core.GradeEntity;
import com.example.gradeservices.query.rest.GradeRestModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends MongoRepository<GradeEntity, String> {
    GradeEntity findBy_id(String _id);
    @Query(value = "{ 'studentId' : ?0, 'subjectName': ?1}", fields="{'grade':1, '_id': 1}")
    String findByStudentId(String student, String subjectName);

    @Query(value = "{ 'studentId' : ?0, 'subjectName': ?1}")
    List<GradeRestModel> findByStudentIdAndName(String student, String subjectName);

    @Query(value = "{ 'subjectName' : ?0 }")
    List<GradeRestModel> findBySubjectName(String subjectName);
}
