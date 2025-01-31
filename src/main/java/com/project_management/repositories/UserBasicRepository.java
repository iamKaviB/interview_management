package com.project_management.repositories;

import com.project_management.models.UserBasics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBasicRepository extends JpaRepository<UserBasics,Long> {
    UserBasics findByUserId(Long id);
}
