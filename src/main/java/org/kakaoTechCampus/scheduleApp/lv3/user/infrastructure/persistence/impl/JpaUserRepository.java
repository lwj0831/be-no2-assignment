package org.kakaoTechCampus.scheduleApp.lv3.user.infrastructure.persistence.impl;

import org.kakaoTechCampus.scheduleApp.lv3.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    @Modifying
    @Query("update UserEntity ue set ue.name = :name where ue.id = :id")
    void updateName(Long id, String name);
}
