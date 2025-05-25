package org.kakaoTechCampus.scheduleApp.lv2.application.interfaces;

import org.kakaoTechCampus.scheduleApp.lv2.domain.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Long save(Schedule schedule);
    Optional<Schedule> findById(Long id);
    List<Schedule> findAllByWriterNameAndUpdatedAt(String writerName, LocalDateTime updatedAt);
    void updateSchedule(Long id, String writerName, String content, String password);
    void deleteSchedule(Long id, String password);
}
