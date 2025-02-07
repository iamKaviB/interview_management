package com.project_management.repositories;

import com.project_management.models.UserVideoHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVideoHistoryRepository extends JpaRepository<UserVideoHistory,Long> {
    List<UserVideoHistory> findAllByUserId(Long userId);
}
