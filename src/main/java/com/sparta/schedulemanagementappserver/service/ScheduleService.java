package com.sparta.schedulemanagementappserver.service;

import com.sparta.schedulemanagementappserver.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementappserver.entity.Schedule;
import com.sparta.schedulemanagementappserver.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/*

✅ 일정과 관련된 비즈니스 로직을 처리하는 서비스 레이어입니다. 컨트롤러와 리포지토리 사이에서 중간 역할, 데이터 처리 및 비즈니즈 로직을 적용.

    ➡️ 일정 데이터를 처리하고 비즈니스 로직을 적용합니다.

    ➡️ 리포지토리와 상호작용하여 데이터베이스 접근을 처리합니다.

    ➡️ 클라이언트로부터 받은 요청을 처리하고 필요한 데이터를 반환합니다.


📢 주요 개념

Service 레이어: 애플리케이션의 비즈니스 로직을 처리하는 계층. 데이터 접근 및 조작, 비즈니스 규칙 적용 등의 기능을 수행.

 */

@Service // 이 클래스가 서비스 레이어의 컴포넌트임을 나타냅니다.
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자를 자동으로 생성해주는 Lombok 애노테이션입니다.
public class ScheduleService {

    private final ScheduleRepository scheduleRepository; // 일정 데이터를 처리하는 리포지토리입니다.

    // 할일을 생성하는 메서드
    public Schedule createSchedule(ScheduleRequestDto dto) { // 새로운 일정을 생성합니다. ScheduleRequestDto 객체를 받아 Schedule 엔티티로 변환한 후, 데이터베이스에 저장합니다.
        var newSchedule = dto.toEntity(); // ScheduleRequestDto를 Schedule 엔티티로 변환합니다.
        return scheduleRepository.save(newSchedule); // 새 일정을 데이터베이스에 저장하고 반환합니다.
    }

    // 특정 할일을 조회하는 메서드
    public Schedule getSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId) // ID로 일정 데이터를 조회합니다.
                .orElseThrow(IllegalArgumentException::new); // 일정이 존재하지 않으면 예외를 던집니다.
    }

    // 모든 할일을 조회하는 메서드, 작성일 기준으로 내림차순 정렬합니다.
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll(Sort.by("createdAt").descending()); // 작성일 기준으로 내림차순 정렬하여 모든 일정을 조회합니다.
    }

    // 특정 할일을 수정하는 메서드
    public Schedule updateSchedule(Long scheduleId, ScheduleRequestDto dto) {
        Schedule schedule = checkPWAndGetSchedule(scheduleId, dto.getPassword()); // 비밀번호를 확인하고 일정을 조회합니다.

        schedule.setTitle(dto.getTitle()); // 제목을 수정합니다.
        schedule.setContents(dto.getContents()); // 내용을 수정합니다.
        schedule.setWriter(dto.getWriter()); // 작성자를 수정합니다.

        return scheduleRepository.save(schedule); // 수정된 일정을 데이터베이스에 저장하고 반환합니다.
    }

    // 비밀번호를 확인하고 일정을 조회하는 메서드
    private Schedule checkPWAndGetSchedule(Long scheduleId, String password) {
        Schedule schedule = getSchedule(scheduleId); // 일정 ID로 일정을 조회합니다.

        // 비밀번호가 일치하는지 확인합니다.
        if (schedule.getPassword() != null && !Objects.equals(schedule.getPassword(), password)) {
            throw new IllegalArgumentException(); // 비밀번호가 일치하지 않으면 예외를 던집니다.
        }
        return schedule; // 일치하면 일정을 반환합니다.
    }

    // 특정 할일을 삭제하는 메서드
    public Schedule deleteSchedule(Long scheduleId, String password) {
        Schedule schedule = checkPWAndGetSchedule(scheduleId, password); // 비밀번호를 확인하고 일정을 조회합니다.

        scheduleRepository.delete(schedule); // 일정을 데이터베이스에서 삭제합니다.
        return schedule; // 삭제된 일정을 반환합니다.
    }
}
