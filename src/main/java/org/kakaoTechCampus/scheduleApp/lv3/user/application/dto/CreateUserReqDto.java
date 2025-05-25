package org.kakaoTechCampus.scheduleApp.lv3.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserReqDto (
        @NotBlank
        String name,
        @Email
        String email
){}
