package com.project_management.services;

import com.project_management.dto.UserBasicsDTO;
import com.project_management.models.UserBasics;

public interface UserBasicService {
    UserBasics save(UserBasics userBasics);
    UserBasics getUserBasicsByUserId(long id);
}
