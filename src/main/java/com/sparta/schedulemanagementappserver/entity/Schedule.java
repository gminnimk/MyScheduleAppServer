package com.sparta.schedulemanagementappserver.entity;

/*

✅ Schedule 클래스는 DB의 Schedule 테이블과 매핑되는 JPA 엔티티입니다.
    이 클래스는 일정 정보를 저장하고, 데이터베이스와 상호작용하기 위해 사용됩니다.

 */

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // 이 클래스가 JPA 엔티티임을 나타냅니다.
@Getter // Lombok 라이브러리를 사용하여 getter 메서드를 자동으로 생성합니다.
@Table(name = "Schedule") // 데이터베이스 테이블 이름을 "Schedule"로 설정합니다.
@NoArgsConstructor // 기본 생성자를 자동으로 생성해주는 Lombok 애노테이션입니다.
public class Schedule {

    @Id // 이 필드가 Primary Key임을 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key 값을 데이터베이스가 자동으로 생성하도록 설정합니다.
    @Column(name = "schedule_id", nullable = false) // 데이터베이스의 열 이름과 제약 조건을 설정합니다.
    private Long scheduleid; // 일정의 고유 식별자입니다.

    private String title; // 할일 제목을 저장하는 필드입니다.

    private String contents; // 할일 내용을 저장하는 필드입니다.

    private String writer; // 담당자를 저장하는 필드입니다.

    private String password; // 비밀번호를 저장하는 필드입니다.

    private LocalDateTime createdAt; // 작성일을 저장하는 필드입니다.


    // 서비스에서 사용할 수 있게 생성자 생성
    @Builder // 빌더 패턴을 사용하여 객체를 생성할 수 있도록 하는 Lombok 애노테이션입니다.
    public Schedule(String title, String contents, String writer, String password) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.password = password;
        this.createdAt = LocalDateTime.now(); // 객체 생성 시 현재 시간을 설정합니다.
    }

    // 제목을 설정하는 메서드입니다.
    public void setTitle(String title) {
        this.title = title;
    }

    // 내용을 설정하는 메서드입니다.
    public void setContents(String contents) {
        this.contents = contents;
    }

    // 작성자를 설정하는 메서드입니다.
    public void setWriter(String writer) {
        this.writer = writer;
    }

    // 일정 ID를 반환하는 메서드입니다.
    public Long getScheduleId() {
        return scheduleid;
    }
}
