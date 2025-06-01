package org.kakaoTechCampus.scheduleApp.lv3.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.kakaoTechCampus.scheduleApp.lv3.user.infrastructure.persistence.entity.UserEntity;

@Getter
@AllArgsConstructor
@Builder
public class User {
    Long id;
    String name;
    String email;

    public static User toUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }
}
