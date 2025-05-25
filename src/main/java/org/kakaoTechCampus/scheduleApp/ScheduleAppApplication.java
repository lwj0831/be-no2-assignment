package org.kakaoTechCampus.scheduleApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"org.kakaoTechCampus.scheduleApp.lv3"})
public class ScheduleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleAppApplication.class, args);
	}

}
