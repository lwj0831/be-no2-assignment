package org.kakaoTechCampus.scheduleApp.lv3.schedule.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record DeleteScheduleReqDto(
        @NotBlank(message = "비밀번호는 필수값입니다.")
        String password
) {}
