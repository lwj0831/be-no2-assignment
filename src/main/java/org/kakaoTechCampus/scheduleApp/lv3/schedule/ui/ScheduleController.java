package org.kakaoTechCampus.scheduleApp.lv3.schedule.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv3.common.ui.Response;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.application.ScheduleService;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.application.dto.CreateScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.application.dto.DeleteScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.application.dto.GetScheduleResDto;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.application.dto.UpdateScheduleReqDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.2.0/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/create")
    public Response<Long> createSchedule(@Valid @RequestBody CreateScheduleReqDto dto) {
        Long id = scheduleService.save(dto);
        return Response.ok(id,"create schedule success");
    }

    @GetMapping("/{id}")
    public Response<GetScheduleResDto> findSchedule(@PathVariable Long id) {
        GetScheduleResDto dto = scheduleService.findById(id);
        return Response.ok(dto, "find schedule success");
    }

    @GetMapping
    public Response<Page<GetScheduleResDto>> findScheduleList(
            @RequestParam String writerName,
            @RequestParam LocalDateTime updatedAt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<GetScheduleResDto> dtoPage = scheduleService.findAllByWriterNameAndUpdatedAt(writerName, updatedAt, page, size);
        return Response.ok(dtoPage, "find schedule list success");
    }

    @PutMapping("{id}")
    public Response<Void> updateSchedule(@PathVariable Long id, @Valid @RequestBody UpdateScheduleReqDto dto) {
        scheduleService.updateSchedule(id, dto);
        return Response.ok(null,"update schedule success");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteSchedule(@PathVariable Long id, @Valid @RequestBody DeleteScheduleReqDto dto) {
        scheduleService.deleteSchedule(id, dto);
        return Response.ok(null, "delete schedule success");
    }
}
