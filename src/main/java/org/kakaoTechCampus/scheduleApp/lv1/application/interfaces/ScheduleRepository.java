package org.kakaoTechCampus.scheduleApp.lv1.application.interfaces;

import org.kakaoTechCampus.scheduleApp.lv1.application.dto.GetScheduleResDto;
import org.kakaoTechCampus.scheduleApp.lv1.domain.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Long save(Schedule schedule);
    Optional<Schedule> findById(Long id);
    List<Schedule> findAllByWriterNameAndUpdatedAt(String writerName, LocalDateTime updatedAt);
}
