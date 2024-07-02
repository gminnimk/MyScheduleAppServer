package com.sparta.schedulemanagementappserver.service;

import com.sparta.schedulemanagementappserver.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementappserver.entity.Schedule;
import com.sparta.schedulemanagementappserver.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;




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
}
