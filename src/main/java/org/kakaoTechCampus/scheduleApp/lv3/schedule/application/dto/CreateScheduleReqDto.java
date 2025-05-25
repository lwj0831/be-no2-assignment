package org.kakaoTechCampus.scheduleApp.lv3.schedule.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateScheduleReqDto(
        @NotBlank
        String title,
        @NotBlank
        String password,
        @NotBlank
        @Size(max = 200)
        String content,
        @NotNull
        Long userId
) {}
