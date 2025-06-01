package org.kakaoTechCampus.scheduleApp.lv1.dto;

import org.kakaoTechCampus.scheduleApp.lv1.domain.Schedule;

import java.time.LocalDateTime;

public record GetScheduleResDto(
        Long id,
        String title,
        String content,
        String writerName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public GetScheduleResDto(Schedule schedule) {
        this(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getWriterName(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }
}
