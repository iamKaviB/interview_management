package com.project_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CVParseResponseDTO {

    @JsonProperty("inferred_role")
    private String inferredRole;
    @JsonProperty("skills_in_cv")
    private List<String> skillsInCv;
    @JsonProperty("missing_skills")
    private List<String> missingSkills;
    @JsonProperty("resources_to_improve")
    private Map<String, SkillResources> resourcesToImprove;

    @Data
    public static class SkillResources {
        @JsonProperty("ebooks")
        private List<String> ebooks;
        @JsonProperty("videos")
        private List<String> videos;
    }
}
