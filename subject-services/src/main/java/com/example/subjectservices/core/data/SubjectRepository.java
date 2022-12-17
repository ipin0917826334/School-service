package com.example.subjectservices.core.data;

import com.example.subjectservices.core.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, String> {
    SubjectEntity findBySubjectId(String subjectId);
    SubjectEntity findBySubjectIdOrSubjectName(String subjectId, String subjectName);
}
