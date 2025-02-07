package com.project_management.servicesImpl;

import com.project_management.models.UserVideoHistory;
import com.project_management.models.Video;
import com.project_management.repositories.UserVideoHistoryRepository;
import com.project_management.repositories.VideoRepository;
import com.project_management.services.UserVideoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserVideoHistoryImpl implements UserVideoHistoryService {
    @Autowired
    private UserVideoHistoryRepository userVideoHistoryRepository;
    @Autowired
    private VideoRepository videoRepository;


    @Override
    public UserVideoHistory addHistory(Long userId, Long videoId) {
        UserVideoHistory temp = new UserVideoHistory();
        temp.setVideoId(videoId);
        temp.setUserId(userId);
        return userVideoHistoryRepository.save(temp);
    }

    @Override
    public List<Video> getHistoryOfUser(Long userId, Integer offset) {
        // Get the list of videoIds ordered by id DESC
        List<UserVideoHistory> videoIdList = userVideoHistoryRepository.findAllByUserId(userId);

        // Limit the videoIdList according to the offset (top N elements)
        List<UserVideoHistory> limitedVideoIdList = videoIdList.stream()
                .sorted((userVideoHistory, userVideoHistory2) -> Long.compare(userVideoHistory2.getVideoId(), userVideoHistory.getVideoId())) // Sort by id DESC
                .limit(offset) // Limit the size to the offset value
                .toList();

        // Fetch videos corresponding to the limited videoIds and return
        return limitedVideoIdList.stream()
                .map(id -> videoRepository.findById(id.getVideoId()).orElseThrow())
                .collect(Collectors.toList());
    }

}
