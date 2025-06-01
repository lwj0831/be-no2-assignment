package org.kakaoTechCampus.scheduleApp.lv3.schedule.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateScheduleReqDto(
        @NotBlank(message = "제목은 필수값입니다.")
        String title,
        @NotBlank(message = "비밀번호는 필수값입니다.")
        String password,
        @NotBlank(message = "내용을 필수값입니다.")
        @Size(max = 200)
        String content,
        @NotNull
        Long userId
) {}
