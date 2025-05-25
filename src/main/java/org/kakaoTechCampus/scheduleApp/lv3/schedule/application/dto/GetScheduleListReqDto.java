package org.kakaoTechCampus.scheduleApp.lv3.schedule.application.dto;

import java.time.LocalDateTime;

public record GetScheduleListReqDto(
    String writerName,
    LocalDateTime updatedAt
){}
