package org.kakaoTechCampus.scheduleApp.lv3.schedule.infrastructure.persistence.impl;

import jakarta.annotation.Nonnull;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.infrastructure.persistence.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaScheduleRepository extends JpaRepository<ScheduleEntity, Long>, JpaSpecificationExecutor<ScheduleEntity> {

    @EntityGraph(attributePaths = {"user"})
    @Query("SELECT s FROM ScheduleEntity s WHERE s.id = :id")
    Optional<ScheduleEntity> findByIdWithUser(@Param("id") Long id);

    @Modifying
    @Query("update ScheduleEntity se set se.content = :content, se.updDt = now() " +
            "where se.id = :id and se.password = :password")
    void updateSchedule(Long id, String content, String password);

    @Modifying
    @Query("delete from ScheduleEntity se where se.id = :id and se.password = :password")
    void deleteSchedule(Long id, String password);

}
