package org.kakaoTechCampus.scheduleApp.lv3.schedule.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateScheduleReqDto(
        @NotBlank(message = "할일은 필수값입니다.")
        @Size(max = 200, message = "할일은 최대 200자로 작성할 수 있습니다.")
        String content,
        Long newUserId,
        @NotBlank(message = "비밀번호는 필수값입니다.")
        String password
) {}
