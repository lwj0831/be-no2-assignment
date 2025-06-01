package org.kakaoTechCampus.scheduleApp.lv3.schedule.application.service;

import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv3.common.exception.ErrorCode;
import org.kakaoTechCampus.scheduleApp.lv3.common.exception.NotFoundException;
import org.kakaoTechCampus.scheduleApp.lv3.common.exception.PasswordMismatchException;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.application.dto.*;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.domain.repository.ScheduleRepository;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.domain.model.Schedule;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.presentation.dto.CreateScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.presentation.dto.DeleteScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.presentation.dto.GetScheduleResDto;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.presentation.dto.UpdateScheduleReqDto;
import org.kakaoTechCampus.scheduleApp.lv3.user.domain.repository.UserRepository;
import org.kakaoTechCampus.scheduleApp.lv3.user.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(CreateScheduleReqDto dto) throws NotFoundException{
        User user = userRepository.findById(dto.userId())
                .orElseThrow(()->new NotFoundException(ErrorCode.NOT_FOUND));
        Schedule schedule = new Schedule(null, dto.title(), dto.password(), dto.content(), user);
        return scheduleRepository.save(schedule);
    }

    public GetScheduleResDto findById(Long id) throws NotFoundException {
        return scheduleRepository.findById(id)
                .map(GetScheduleResDto::new)
                .orElseThrow(()->new NotFoundException(ErrorCode.NOT_FOUND));
    }

    public Page<GetScheduleResDto> findAllByWriterIdAndUpdatedAt(Long writerId, LocalDateTime updatedAt, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updDt").descending());
        return scheduleRepository.findAllByWriterIdAndUpdatedAt(writerId, updatedAt, pageable)
                .map(GetScheduleResDto::new);
    }

    @Transactional
    public void updateSchedule(Long id, UpdateScheduleReqDto dto) throws PasswordMismatchException {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND));

        if(schedule.checkPassword(dto.password())){
            scheduleRepository.updateSchedule(id, dto.content(), dto.newUserId(), dto.password());
        }
        else{
            throw new PasswordMismatchException(ErrorCode.ACCESS_DENIED_ERROR);
        }
    }

    @Transactional
    public void deleteSchedule(Long id, DeleteScheduleReqDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND));

        if(schedule.checkPassword(dto.password())){
            scheduleRepository.deleteSchedule(id, dto.password());
        }
        else{
            throw new PasswordMismatchException(ErrorCode.ACCESS_DENIED_ERROR);
        }
    }
}
