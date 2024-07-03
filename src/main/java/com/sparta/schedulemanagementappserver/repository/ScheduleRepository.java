package com.sparta.schedulemanagementappserver.repository;


import com.sparta.schedulemanagementappserver.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

/*

✅  스프링 데이터 JPA에서 제공하는 JpaRepository를 상속받아 Schedule 엔티티에 대한 데이터베이스 접근 기능을 제공하는 클래스.

        ➡️ JpaRepository를 상속함으로써 기본적인 CRUD 및 페이징,정렬 등의 기능을 자동으로 사용할 수 있게 됨.


📢 주요 개념

    Spring Data JPA: 스프링 프레임워크의 데이터 접근 계층을 간단히 만들기 위한 모듈. JPA (Java Persistence API)를 쉽게 사용할 수 있도록 도와줍니다.

    JpaRepository: 스프링 데이터 JPA가 제공하는 기본적인 CRUD 및 페이징, 정렬 기능을 포함하는 인터페이스. 이 인터페이스를 상속받으면,
                   별도의 구현 없이도 기본적인 데이터 접근 메서드를 사용할 수 있습니다.

 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
