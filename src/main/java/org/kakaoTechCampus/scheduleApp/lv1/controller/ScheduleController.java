package org.kakaoTechCampus.scheduleApp.lv1.controller;

import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv1.service.ScheduleService;
import org.kakaoTechCampus.scheduleApp.lv1.dto.CreateScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv1.dto.GetScheduleResDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0.0/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/create")
    public Long createSchedule(@RequestBody CreateScheduleReqDto dto) {
        return scheduleService.save(dto);
    }

    @GetMapping("/{id}")
    public GetScheduleResDto findSchedule(@PathVariable Long id) {
        return scheduleService.findById(id);
    }

    @GetMapping
    public List<GetScheduleResDto> findScheduleList(@RequestParam String writerName, @RequestParam LocalDateTime updatedAt) {
        return scheduleService.findAllByWriterNameAndUpdatedAt(writerName, updatedAt);
    }

}
