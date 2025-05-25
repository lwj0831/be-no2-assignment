package org.kakaoTechCampus.scheduleApp.lv1.ui;

import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv1.application.ScheduleService;
import org.kakaoTechCampus.scheduleApp.lv1.application.dto.CreateScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv1.application.dto.GetScheduleListReqDto;
import org.kakaoTechCampus.scheduleApp.lv1.application.dto.GetScheduleResDto;
import org.springframework.web.bind.annotation.*;

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
    public List<GetScheduleResDto> findScheduleList(@RequestBody GetScheduleListReqDto dto) {
        return scheduleService.findAllByWriterNameAndUpdatedAt(dto);
    }

}
