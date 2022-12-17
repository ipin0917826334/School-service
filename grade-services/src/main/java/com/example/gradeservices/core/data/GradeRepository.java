package com.example.gradeservices.core.data;

import com.example.gradeservices.core.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, String> {
    GradeEntity findByGradeId(String subjectId);
}
