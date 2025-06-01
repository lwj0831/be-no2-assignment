package org.kakaoTechCampus.scheduleApp.lv3.user.domain.repository;

import org.kakaoTechCampus.scheduleApp.lv3.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Long save(User user);
    Optional<User> findById(Long id);

    void updateUserName(Long id, String name);
}
