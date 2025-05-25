package org.kakaoTechCampus.scheduleApp.lv3.user.application.interfaces;

import org.kakaoTechCampus.scheduleApp.lv3.user.domain.User;
import org.kakaoTechCampus.scheduleApp.lv3.user.repository.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Long save(User user);
    Optional<User> findById(Long id);

    void updateUserName(Long id, String name);
}
