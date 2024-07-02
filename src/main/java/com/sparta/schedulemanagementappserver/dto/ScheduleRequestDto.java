package com.sparta.schedulemanagementappserver.dto;


import com.sparta.schedulemanagementappserver.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScheduleRequestDto {

    private String title; // 할일 제목의 필드

    private String contents; // 할일 내용의 필드

    private String writer; // 담당자의 필드

    private String password;


    // 값들을 나열형식으로 넣어주는 것 => builder 패턴
    public Schedule toEntity() {
        return Schedule.builder()
                .title(title)
                .contents(contents)
                .writer(writer)
                .password(password)
                .build();
    }

}
