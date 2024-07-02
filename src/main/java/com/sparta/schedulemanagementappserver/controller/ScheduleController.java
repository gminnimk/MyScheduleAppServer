package com.sparta.schedulemanagementappserver.controller;


import com.sparta.schedulemanagementappserver.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementappserver.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api")
@RestController
@AllArgsConstructor
public class ScheduleController {


    public final ScheduleService scheduleService;


    // api 요청을 할  떄 쿼리 파라미터, path variable, request body를 사용
    // 보통 생성은 request body를 사용하고 request body를 사용하기 위헤서는 dto를 생성 및 사용

    @PostMapping("/schedule")
    public ResponseEntity postSchedule(@RequestBody ScheduleRequestDto dto) {
        // TODO 일정 작성 기능
        scheduleService.createSchedule(dto);
        return ResponseEntity.ok().build();
    }
}
