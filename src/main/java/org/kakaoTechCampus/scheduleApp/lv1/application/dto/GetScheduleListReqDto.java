package org.kakaoTechCampus.scheduleApp.lv1.application.dto;

import java.time.LocalDateTime;

public record GetScheduleListReqDto(
    String writerName,
    LocalDateTime updatedAt
){}
