package org.kakaoTechCampus.scheduleApp.lv3.schedule.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateScheduleReqDto(
        @NotBlank
        @Size(max = 200)
        String content,
        Long newUserId,
        @NotBlank
        String password
) {}
