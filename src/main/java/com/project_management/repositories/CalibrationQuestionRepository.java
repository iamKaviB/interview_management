package com.project_management.repositories;

import com.project_management.models.CalibrationQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalibrationQuestionRepository extends JpaRepository<CalibrationQuestions,Long> {
    List<CalibrationQuestions> findAllByJobRoleAndExperienceAndPrimarySkill(String jobRole,String experience,String primarySkill);
}
