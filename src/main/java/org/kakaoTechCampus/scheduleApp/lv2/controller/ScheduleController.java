package org.kakaoTechCampus.scheduleApp.lv2.controller;

import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv2.dto.CreateScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv2.dto.DeleteScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv2.dto.GetScheduleResDto;
import org.kakaoTechCampus.scheduleApp.lv2.dto.UpdateScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv2.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.1.0/schedules")
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

    @PutMapping("{id}")
    public void updateSchedule(@PathVariable Long id, @RequestBody UpdateScheduleReqDto dto) {
        scheduleService.updateSchedule(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestBody DeleteScheduleReqDto dto) {
        scheduleService.deleteSchedule(id, dto);
    }
}
