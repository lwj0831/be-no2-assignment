package org.kakaoTechCampus.scheduleApp.lv2.application.dto;

public record CreateScheduleReqDto(
        String title,
        String password,
        String content,
        String writerName
) {}
