package org.kakaoTechCampus.scheduleApp.lv3.schedule.application.interfaces;

import org.kakaoTechCampus.scheduleApp.lv3.schedule.domain.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ScheduleRepository {
    Long save(Schedule schedule);
    Optional<Schedule> findById(Long id);
    Page<Schedule> findAllByWriterNameAndUpdatedAt(String writerName, LocalDateTime updatedAt, Pageable pageable);
    void updateSchedule(Long id, String content, Long newUserId, String password);
    void deleteSchedule(Long id, String password);
}
