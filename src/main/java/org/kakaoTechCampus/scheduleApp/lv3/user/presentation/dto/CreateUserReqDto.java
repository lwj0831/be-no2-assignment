package org.kakaoTechCampus.scheduleApp.lv3.user.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserReqDto (
        @NotBlank(message = "이름은 필수값입니다.")
        String name,
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email
){}
