package org.kakaoTechCampus.scheduleApp.lv2.application;

import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv2.application.dto.GetScheduleResDto;
import org.kakaoTechCampus.scheduleApp.lv2.application.dto.*;
import org.kakaoTechCampus.scheduleApp.lv2.domain.Schedule;
import org.kakaoTechCampus.scheduleApp.lv2.application.interfaces.ScheduleRepository;
import org.springframework.stereotype.Service;

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

    public List<GetScheduleResDto> findAllByWriterNameAndUpdatedAt(GetScheduleListReqDto dto) {
        return scheduleRepository.findAllByWriterNameAndUpdatedAt(dto.writerName(), dto.updatedAt())
                .stream()
                .map(GetScheduleResDto::new)
                .toList();
    }

    public void updateSchedule(Long id, UpdateScheduleReqDto dto) {
        scheduleRepository.updateSchedule(id, dto.writerName(), dto.content(), dto.password());
    }

    public void deleteSchedule(Long id, DeleteScheduleReqDto dto) {
        scheduleRepository.deleteSchedule(id, dto.password());
    }
}
