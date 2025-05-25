package org.kakaoTechCampus.scheduleApp.lv3.schedule.repository;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv3.common.domain.exception.ErrorCode;
import org.kakaoTechCampus.scheduleApp.lv3.common.domain.exception.NotFoundException;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.application.interfaces.ScheduleRepository;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.domain.Schedule;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.repository.entity.ScheduleEntity;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.repository.jpa.JpaScheduleRepository;
import org.kakaoTechCampus.scheduleApp.lv3.user.repository.entity.UserEntity;
import org.kakaoTechCampus.scheduleApp.lv3.user.repository.jpa.JpaUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JpaScheduleRepository jpaScheduleRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public Long save(Schedule schedule) {
        UserEntity userEntity = jpaUserRepository.findById(schedule.getUser().getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND));

        ScheduleEntity entity = ScheduleEntity.builder()
                .title(schedule.getTitle())
                .password(schedule.getPassword())
                .content(schedule.getContent())
                .user(userEntity)
                .build();

        ScheduleEntity scheduleEntity = jpaScheduleRepository.save(entity);
        return scheduleEntity.getId();
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        Optional<ScheduleEntity> scheduleEntity = jpaScheduleRepository.findById(id);
        return scheduleEntity.map(Schedule::toSchedule);
    }

    @Override
    public Page<Schedule> findAllByWriterNameAndUpdatedAt(String writerName, LocalDateTime updatedAt, Pageable pageable) {
        Specification<ScheduleEntity> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (writerName != null && !writerName.isBlank()) {
                predicates.add(cb.equal(root.get("user").get("name"), writerName));
            }

            if (updatedAt != null) {
                predicates.add(cb.equal(cb.function("DATE", LocalDate.class, root.get("updDt")), updatedAt.toLocalDate()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return jpaScheduleRepository.findAll(spec, pageable)
                .map(Schedule::toSchedule);
    }

    @Override
    public void updateSchedule(Long id, String content, Long newUserId, String password) {
        ScheduleEntity scheduleEntity = jpaScheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND));
        UserEntity userEntity = jpaUserRepository.findById(newUserId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND));
        scheduleEntity.updateUser(userEntity);
        //password 비교를 위해 dirtyChecking 방식이 아닌 직접 쿼리문 사용하는 방식 채택
        jpaScheduleRepository.updateSchedule(id, content, password);
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        //password 비교를 위해 dirtyChecking 방식이 아닌 직접 쿼리문 사용하는 방식 채택
        jpaScheduleRepository.deleteSchedule(id, password);
    }


}


