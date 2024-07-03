package com.sparta.schedulemanagementappserver.controller;


import com.sparta.schedulemanagementappserver.dto.CommonResponse;
import com.sparta.schedulemanagementappserver.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementappserver.dto.ScheduleResponseDto;
import com.sparta.schedulemanagementappserver.entity.Schedule;
import com.sparta.schedulemanagementappserver.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/*

✅ ScheduleController 클래스는 RESTful API를 제공하는 컨트롤러.

    ➡️ 이 클래스는 일정 관련 요청을 처리 및 클라이언트로부터 요청을 받아 서비스를 통해 비즈니스 로직을 수행하고
        그 결과를 클라이언트에게 반환하는 역할 수행.


    📢 주요 개념

    @RequestMapping: 클래스나 메서드가 처리할 요청 경로를 지정. 클래스 레벨에서 사용하면, 해당 클래스의 모든 메서드에 공통된 기본 경로를 설정합니다.

    @RestController: 해당 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냅니다

    @AllArgsConstructor: 이 애노테이션은 롬복(Lombok) 라이브러리에서 제공하며, 클래스의 모든 필드를 인수로 받는 생성자를 자동으로 생성해줍니다.

    ResponseEntity: HTTP 응답 전체를 나타내는 객체로, 상태 코드, 헤더, 본문을 모두 포함합니다.

    DTO (Data Transfer Object): 데이터 전송을 위해 사용하는 객체로, 주로 클라이언트와 서버 간의 데이터 교환에 사용됩니다.

 */


@RequestMapping("api") // 이 클래스의 모든 메서드는 "/api" 경로 하위에서 매핑.
@RestController // 이 클래스는 RESTful 웹 서비스의 컨트롤러임을 나타냄.
@AllArgsConstructor // 이 클래스의 모든 필드를 인수로 받는 생성자를 자동으로 생성해줌.
public class ScheduleController {


    // 일정 관련 비스니즈 로직을 처리하는 서비스 객체를 주입 받음.
    public final ScheduleService scheduleService;


    // api 요청을 할떄 쿼리 파라미터, path variable, request body를 사용
    // 보통 생성은 request body를 사용하고 request body를 사용하기 위헤서는 dto를 생성 및 사용


    // 일정 생성 기능
    // 클라이언트로부터 전달받은 일정 데이터를 ScheduleRequestDto 객체로 받아옵니다.
    @PostMapping("/schedule")
    public ResponseEntity<CommonResponse<ScheduleResponseDto>> postSchedule(@RequestBody ScheduleRequestDto dto) {
        Schedule schedule = scheduleService.createSchedule(dto); // ScheduleService를 이용해 새로운 일정을 생성합니다.
        ScheduleResponseDto response = new ScheduleResponseDto(schedule); // 생성된 일정을 ScheduleResponseDto 객체로 변환합니다.
        return ResponseEntity.ok()
                .body(CommonResponse.<ScheduleResponseDto>builder()
                        .statusCode(HttpStatus.OK.value()) // 상태 코드를 설정합니다.
                        .msg("생성이 완료 되었습니다.") // 메시지를 설정합니다.
                        .data(response) // 응답 데이터를 설정합니다.
                        .build());
    }

    // 특정 일정 조회 기능
    // 경로 변수로 전달된 일정 ID를 받아옵니다.
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<CommonResponse<ScheduleResponseDto>> getSchedule(@PathVariable Long scheduleId) {
        Schedule schedule = scheduleService.getSchedule(scheduleId); // 일정 정보를 조회합니다.
        ScheduleResponseDto response = new ScheduleResponseDto(schedule); // 조회된 일정을 ScheduleResponseDto 객체로 변환합니다.
        return ResponseEntity.ok()
                .body(CommonResponse.<ScheduleResponseDto>builder()
                        .statusCode(HttpStatus.OK.value()) // 상태 코드를 설정합니다.
                        .msg("단건 조회가 완료 되었습니다.") // 메시지를 설정합니다.
                        .data(response) // 응답 데이터를 설정합니다.
                        .build());
    }

    // 전체 일정 조회 기능
    @GetMapping("/schedule")
    public ResponseEntity<CommonResponse<List<ScheduleResponseDto>>> getSchedules() {
        List<Schedule> schedules = scheduleService.getSchedules(); // 모든 일정을 조회합니다.
        List<ScheduleResponseDto> response = schedules.stream()
                .map(ScheduleResponseDto::new) // 각 일정 엔티티를 DTO로 변환합니다.
                .collect(Collectors.toList()); // 변환된 DTO를 리스트로 수집합니다.
        return ResponseEntity.ok()
                .body(CommonResponse.<List<ScheduleResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value()) // 상태 코드를 설정합니다.
                        .msg("목록 조회가 완료 되었습니다.") // 메시지를 설정합니다.
                        .data(response) // 응답 데이터를 설정합니다.
                        .build());
    }

    // 선택한 일정 수정 기능
    @PutMapping("/schedule/{scheduleId}")
    public ResponseEntity<CommonResponse<ScheduleResponseDto>> putSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto dto) {
        Schedule schedule = scheduleService.updateSchedule(scheduleId, dto); // 일정 정보를 업데이트합니다.
        ScheduleResponseDto response = new ScheduleResponseDto(schedule); // 업데이트된 일정을 ScheduleResponseDto 객체로 변환합니다.
        return ResponseEntity.ok()
                .body(CommonResponse.<ScheduleResponseDto>builder()
                        .statusCode(HttpStatus.OK.value()) // 상태 코드를 설정합니다.
                        .msg("수정이 완료 되었습니다.") // 메시지를 설정합니다.
                        .data(response) // 응답 데이터를 설정합니다.
                        .build());
    }

    // 선택한 일정 삭제 기능
    @DeleteMapping("/schedule/{scheduleId}")
    public ResponseEntity<CommonResponse> deleteSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto dto) {
        scheduleService.deleteSchedule(scheduleId, dto.getPassword()); // 일정 정보를 삭제합니다.
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value()) // 상태 코드를 설정합니다.
                .msg("삭제가 완료 되었습니다.") // 메시지를 설정합니다.
                .build());
    }
}