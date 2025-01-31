package com.project_management.repositories;

import com.project_management.models.CalibrationTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalibrationTestRepository extends JpaRepository<CalibrationTest,Long> {
    List<CalibrationTest> findAllByTestId(long id);
}
