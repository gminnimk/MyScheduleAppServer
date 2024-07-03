package com.sparta.schedulemanagementappserver.dto;


// 반환 객체 생성
// 비밀번호 노출이 되면 안 되니까 빼고 작성

import com.sparta.schedulemanagementappserver.entity.Schedule;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleResponseDto {

    private Long scheduleId;

    private String title;

    private String contents;

    private String writer;

    private LocalDateTime createdAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.writer = schedule.getWriter();
        this.createdAt = schedule.getCreatedAt();
    }
}
