package org.kakaoTechCampus.scheduleApp.lv3.user.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv3.common.TimeBaseEntity;
import org.kakaoTechCampus.scheduleApp.lv3.user.domain.model.User;

@Entity
@Table(name = "scheduleapp_user")
@Getter
@NoArgsConstructor
public class UserEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @Builder
    public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static UserEntity toUserEntity(User user) {
        return UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
