package com.example.gradeservices.core.data;

import com.example.gradeservices.core.GradeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends MongoRepository<GradeEntity, String> {
    GradeEntity findBy_id(String _id);
}
