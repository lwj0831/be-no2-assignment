package org.kakaoTechCampus.scheduleApp.lv3.schedule.domain.repository;

import org.kakaoTechCampus.scheduleApp.lv3.schedule.domain.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ScheduleRepository {
    Long save(Schedule schedule);
    Optional<Schedule> findById(Long id);
    Page<Schedule> findAllByWriterIdAndUpdatedAt(Long writerId, LocalDateTime updatedAt, Pageable pageable);
    void updateSchedule(Long id, String content, Long newUserId, String password);
    void deleteSchedule(Long id, String password);
}
