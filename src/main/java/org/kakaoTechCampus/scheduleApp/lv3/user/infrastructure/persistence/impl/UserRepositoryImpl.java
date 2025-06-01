package org.kakaoTechCampus.scheduleApp.lv3.user.infrastructure.persistence.impl;

import lombok.RequiredArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv3.user.domain.repository.UserRepository;
import org.kakaoTechCampus.scheduleApp.lv3.user.domain.model.User;
import org.kakaoTechCampus.scheduleApp.lv3.user.infrastructure.persistence.entity.UserEntity;
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
