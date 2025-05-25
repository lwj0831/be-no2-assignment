package org.kakaoTechCampus.scheduleApp.lv1.application.dto;

public record CreateScheduleReqDto(
        String title,
        String password,
        String content,
        String writerName
) {}
