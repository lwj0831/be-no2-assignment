package org.kakaoTechCampus.scheduleApp.lv3.schedule.repository.jpa;

import org.kakaoTechCampus.scheduleApp.lv3.schedule.repository.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface JpaScheduleRepository extends JpaRepository<ScheduleEntity, Long>, JpaSpecificationExecutor<ScheduleEntity> {

    @Modifying
    @Query("update ScheduleEntity se set se.content = :content, se.updDt = now() " +
            "where se.id = :id and se.password = :password")
    void updateSchedule(Long id, String content, String password);

    @Modifying
    @Query("delete from ScheduleEntity se where se.id = :id and se.password = :password")
    void deleteSchedule(Long id, String password);

}
