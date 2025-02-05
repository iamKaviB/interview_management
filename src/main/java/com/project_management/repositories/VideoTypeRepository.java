package com.project_management.repositories;

import com.project_management.models.VideoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoTypeRepository extends JpaRepository<VideoType,Long> {
    List<VideoType> findAllByType(String type);
    List<VideoType> findAllByRoleAndCurrentLevelAndTargetLevel(String role,String currentLevel,String targetLevel);
}
