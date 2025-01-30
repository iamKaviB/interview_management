package com.project_management.servicesImpl;

import com.project_management.dto.UserBasicsDTO;
import com.project_management.models.UserBasics;
import com.project_management.repositories.UserBasicRepository;
import com.project_management.services.UserBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBasicImpl implements UserBasicService {

    @Autowired
    private UserBasicRepository userBasicRepository;

    @Override
    public UserBasics save(UserBasics userBasics) {
        return userBasicRepository.save(userBasics);
    }

    @Override
    public UserBasics getUserBasicsByUserId(long id) {
        return userBasicRepository.findByUserId(id);
    }
}
