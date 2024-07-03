package com.sparta.schedulemanagementappserver.entity;

// DB 테이블과 매핑하는 Entity 클래스 생성

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter // lombok getter 메서드를 자동으로 생성
@Table(name = "Schedule") // 테이블 이름을 "Schedule"로 설정
@NoArgsConstructor // 기본 생성자를 자동으로 생성해주는 에노테이션
public class Schedule {

    @Id // PK 임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 생성하는 값으로 설정
    @Column(name = "schedule_id", nullable = false) // DB의 열의 이름과 제약 조건 설정.

    private Long scheduleid; // 일정의 고유 식별자

    private String title; // 할일 제목의 필드

    private String contents; // 할일 내용의 필드

    private String writer; // 담당자의 필드

    private String password; // 비밀번호의 필드

    private LocalDateTime createdAt; // 작성일의 필드


    // service에서 사용할 수 있게 생성자 생성
    @Builder
    public Schedule(String title, String contents, String writer, String password) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public Long getScheduleId() {
        return scheduleid;
    }
}
