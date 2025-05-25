package org.kakaoTechCampus.scheduleApp.lv3.schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.kakaoTechCampus.scheduleApp.lv3.schedule.repository.entity.ScheduleEntity;
import org.kakaoTechCampus.scheduleApp.lv3.user.domain.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class Schedule {

    private Long id;
    private String title;
    private String password;
    private String content;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Schedule(Long id, String title, String password, String content, User user) {
        this.id = id;
        this.title = title;
        this.password = password;
        this.content = content;
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static Schedule toSchedule(ScheduleEntity scheduleEntity) {
        return Schedule.builder()
                .id(scheduleEntity.getId())
                .title(scheduleEntity.getTitle())
                .password(scheduleEntity.getPassword())
                .content(scheduleEntity.getContent())
                .user(User.toUser(scheduleEntity.getUser()))
                .createdAt(scheduleEntity.getRegDt())
                .updatedAt(scheduleEntity.getUpdDt())
                .build();
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

}
