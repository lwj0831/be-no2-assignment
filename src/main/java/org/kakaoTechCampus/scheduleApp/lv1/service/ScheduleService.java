package org.kakaoTechCampus.scheduleApp.lv1.service;

import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv1.dto.CreateScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv1.dto.GetScheduleResDto;
import org.kakaoTechCampus.scheduleApp.lv1.repository.interfaces.ScheduleRepository;
import org.kakaoTechCampus.scheduleApp.lv1.domain.Schedule;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public Long save(CreateScheduleReqDto dto) {
        Schedule schedule = new Schedule(null, dto.title(), dto.password(), dto.content(), dto.writerName());
        return scheduleRepository.save(schedule);
    }

    public GetScheduleResDto findById(Long id) {
        return scheduleRepository.findById(id)
                .map(GetScheduleResDto::new)
                .orElseThrow(()->new IllegalArgumentException("일정이 존재하지 않습니다."));
    }

    public List<GetScheduleResDto> findAllByWriterNameAndUpdatedAt(String writerName, LocalDateTime updatedAt) {
        return scheduleRepository.findAllByWriterNameAndUpdatedAt(writerName, updatedAt)
                .stream()
                .map(GetScheduleResDto::new)
                .toList();
    }
}
