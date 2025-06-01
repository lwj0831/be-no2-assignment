package org.kakaoTechCampus.scheduleApp.lv1.dto;

public record CreateScheduleReqDto(
        String title,
        String password,
        String content,
        String writerName
) {}
