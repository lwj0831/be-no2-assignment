package org.kakaoTechCampus.scheduleApp.lv3.user.repository;

import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv3.user.application.interfaces.UserRepository;
import org.kakaoTechCampus.scheduleApp.lv3.user.domain.User;
import org.kakaoTechCampus.scheduleApp.lv3.user.repository.entity.UserEntity;
import org.kakaoTechCampus.scheduleApp.lv3.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public Long save(User user) {
        UserEntity userEntity = jpaUserRepository.save(UserEntity.toUserEntity(user));
        return userEntity.getId();
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id).map(User::toUser);
    }

    @Override
    public void updateUserName(Long id, String name) {
        jpaUserRepository.updateName(id, name);
    }
}
