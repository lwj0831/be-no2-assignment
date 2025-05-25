package org.kakaoTechCampus.scheduleApp.lv3.schedule.application;

import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv3.common.domain.exception.ErrorCode;
import org.kakaoTechCampus.scheduleApp.lv3.common.domain.exception.NotFoundException;
import org.kakaoTechCampus.scheduleApp.lv3.common.domain.exception.PasswordMismatchException;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.application.dto.*;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.application.interfaces.ScheduleRepository;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.domain.Schedule;
import org.kakaoTechCampus.scheduleApp.lv3.user.application.interfaces.UserRepository;
import org.kakaoTechCampus.scheduleApp.lv3.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    public Page<GetScheduleResDto> findAllByWriterNameAndUpdatedAt(String writerName, LocalDateTime updatedAt, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updDt").descending());
        return scheduleRepository.findAllByWriterNameAndUpdatedAt(writerName, updatedAt, pageable)
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
