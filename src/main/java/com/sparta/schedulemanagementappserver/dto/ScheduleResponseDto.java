package com.sparta.schedulemanagementappserver.dto;


/*

✅ Schedule 엔티티를 DTO로 변환하여 반환하기 위한 클래스

    ➡️ Schedule 엔티티에서 클라이언트에게 필요한 데이터만을 추출하여 전송.

    ➡️ 민감한 정보를 제외하여 보안성을 높임.

    ➡️ 클라이언트와 서버 간의 데이터 전송을 단순화하고 명확하게 만듬.

    ➕ 비밀번호 노출을 방지하기 위해 비밀번호 필드는 제외함

 */


import com.sparta.schedulemanagementappserver.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleResponseDto {

    private Long scheduleId; // 일정 ID를 저장하는 필드

    private String title; // 일정 제목을 저장하는 필드

    private String contents; // 일정 내용을 저장하는 필드

    private String writer; // 작성자를 저장하는 필드

    private LocalDateTime createdAt; // 생성 시간을 저장하는 필드

    // Schedule 엔티티를 받아서 ScheduleResponseDto 객체로 변환하는 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId(); // 일정 ID를 설정합니다.
        this.title = schedule.getTitle(); // 일정 제목을 설정합니다.
        this.contents = schedule.getContents(); // 일정 내용을 설정합니다.
        this.writer = schedule.getWriter(); // 작성자를 설정합니다.
        this.createdAt = schedule.getCreatedAt(); // 생성 시간을 설정합니다.
    }
}

