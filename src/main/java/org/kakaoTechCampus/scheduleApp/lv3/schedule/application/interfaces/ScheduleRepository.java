package org.kakaoTechCampus.scheduleApp.lv3.schedule.application.interfaces;

import org.kakaoTechCampus.scheduleApp.lv3.schedule.domain.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Long save(Schedule schedule);
    Optional<Schedule> findById(Long id);
    List<Schedule> findAllByWriterNameAndUpdatedAt(String writerName, LocalDateTime updatedAt);
    void updateSchedule(Long id, String content, Long newUserId, String password);
    void deleteSchedule(Long id, String password);
}
