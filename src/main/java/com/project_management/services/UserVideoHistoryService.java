package com.project_management.services;

import com.project_management.models.UserVideoHistory;
import com.project_management.models.Video;

import java.util.List;

public interface UserVideoHistoryService {
    UserVideoHistory addHistory(Long userId, Long videoId);
    List<Video> getHistoryOfUser(Long userId,Integer offset);
}
