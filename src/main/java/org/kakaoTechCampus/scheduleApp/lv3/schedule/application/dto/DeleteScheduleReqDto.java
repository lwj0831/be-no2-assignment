package org.kakaoTechCampus.scheduleApp.lv3.schedule.application.dto;

import jakarta.validation.constraints.NotBlank;

public record DeleteScheduleReqDto(
        @NotBlank
        String password
) {}
