package org.kakaoTechCampus.scheduleApp.lv3.schedule.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kakaoTechCampus.scheduleApp.lv3.common.TimeBaseEntity;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.domain.model.Schedule;
import org.kakaoTechCampus.scheduleApp.lv3.user.infrastructure.persistence.entity.UserEntity;

@Entity
@Table(name = "scheduleapp_schedule")
@Getter
@NoArgsConstructor
public class ScheduleEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String password;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public ScheduleEntity(String title, String password, String content, UserEntity user) {
        this.title = title;
        this.password = password;
        this.content = content;
        this.user = user;
    }

    public static ScheduleEntity toScheduleEntity(Schedule schedule) {
        return ScheduleEntity.builder()
                .title(schedule.getTitle())
                .password(schedule.getPassword())
                .content(schedule.getContent())
                .user(UserEntity.toUserEntity(schedule.getUser()))
                .build();
    }

    public void updateUser(UserEntity userEntity){
        this.user = userEntity;
    }

}
