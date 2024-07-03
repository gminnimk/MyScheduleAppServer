package com.sparta.schedulemanagementappserver.service;

import com.sparta.schedulemanagementappserver.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementappserver.entity.Schedule;
import com.sparta.schedulemanagementappserver.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor // 모든 생성자가 주입 받을 수 있도록 해주는 에노테이션
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;


    // 외부에서 사용할 수 있도록
    // 할일 생성하는 메서드
    public Schedule createSchedule(ScheduleRequestDto dto) {
        var newSchedule = dto.toEntity();
        return scheduleRepository.save(newSchedule);
    }


    // 할일 단건 조회
    public Schedule getSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(IllegalArgumentException::new);

    }

    // 할일 전체 조회 (createdAt 기준으로 내림차순)
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll(Sort.by("createdAt").descending());
    }


    // 할일 수정
    public Schedule updateSchedule(Long scheduleId, ScheduleRequestDto dto) {
        Schedule schedule = checkPWAndGetSchedule(scheduleId, dto.getPassword());

        schedule.setTitle(dto.getTitle());
        schedule.setContents(dto.getContents());
        schedule.setWriter(dto.getWriter());

        return scheduleRepository.save(schedule);
    }

    private Schedule checkPWAndGetSchedule(Long scheduleId, String password) {
        Schedule schedule = getSchedule(scheduleId);

        // 비밀번호 체크
        if (schedule.getPassword() != null
                && !Objects.equals(schedule.getPassword(), password)) {
            throw new IllegalArgumentException();
        }
        return schedule;
    }

    public Schedule deleteSchedule(Long scheduleId, String password) {
        Schedule schedule = checkPWAndGetSchedule(scheduleId, password);

        scheduleRepository.delete(schedule);
        return schedule;
    }
}
