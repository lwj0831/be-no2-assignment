package org.kakaoTechCampus.scheduleApp.lv1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String title;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String writerName;

    public Schedule(Long id, String title, String password, String content, String writerName) {
        this.id = id;
        this.title = title;
        this.password = password;
        this.content = content;
        this.writerName = writerName;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
