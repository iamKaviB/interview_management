package com.project_management.controllers;

import com.project_management.dto.UserBasicsDTO;
import com.project_management.models.UserBasics;
import com.project_management.services.UserBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-basic")
public class UserBasicController {

    @Autowired
    UserBasicService userBasicService;

    @PostMapping("/{UserId}")
    public ResponseEntity<UserBasics> save(@RequestBody  UserBasicsDTO dto , @PathVariable(name = "UserId") Long userId){
        UserBasics userBasics = new UserBasics();
        userBasics.setUserId(userId);
        userBasics.setFullName(dto.getFullName());
        userBasics.setJobRole(dto.getJobRole());
        userBasics.setExperience(dto.getExperience());
        userBasics.setTargetRole(dto.getTargetRole());
        userBasics.setPrimarySkill(dto.getPrimarySkill());
        userBasics.setEnglishLevel(dto.getEnglishLevel());

        return ResponseEntity.status(201).body(userBasicService.save(userBasics));
    }

    @GetMapping("/{UserId}")
    public ResponseEntity<UserBasics> getUserBasic(@PathVariable(name = "UserId") Long userId){
        return ResponseEntity.status(201).body(userBasicService.getUserBasicsByUserId(userId));
    }


}
