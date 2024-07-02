package com.sparta.schedulemanagementappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
public class ScheduleManagementAppServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleManagementAppServerApplication.class, args);
    }

}
