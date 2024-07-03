package com.sparta.schedulemanagementappserver.dto;


import com.sparta.schedulemanagementappserver.entity.Schedule;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank; // 7단계
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




/*

✅ ScheduleRequestDto 클래스는 클라이언트로부터 전달받은 일정 데이터를 담는 데이터 전송 객체(Data Transfer Object).

    ➡️ 주요 역할로

        ✔️ (1). 데이터 저장 : 클라이언트가 입력한 일정 데이터를 임시로 저장하는 용도로 사용.

        ✔️ (2). 데이터 변환 : 'toEntity' 메서드를 통해 'Schedule' 엔티티 객체로 변환하여 서비스 계층에서 사용할 수 있도록 함.

    ➡️ 일정의 제목, 내용, 작성자, 비밀번호를 필드로 가지고 있고, 클라이언트로부터 입력된 데이터를 이 플드들에 담음.
W
 */

@Setter // 해당 클래스의 모든 필드에 대해 Setter 메서드를 자동으로 생성
@Getter // Getter 메서드 자동 생성.
@NoArgsConstructor
public class ScheduleRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title; // 할일 제목의 필드
    @Size(max = 200, message = "최대 200자 이내로 작성해주세요.")

    @NotBlank(message = "내용을 입력해주세요.")
    private String contents; // 할일 내용의 필드

    @Email
    @NotBlank(message = "email은 공백일 수 없습니다.")
    private String writer; // 담당자의 필드

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    private String password; // 비밀번호 필드


    // Dto 객체를 Schedule 엔티티 객체로 변환해주는 메서드 => builder 패턴
    // 값들을 나열형식으로 넣어주는 것 => builder 패턴
    public Schedule toEntity() {
        return Schedule.builder()
                .title(title) // 제목 설정
                .contents(contents) // 내용 설정
                .writer(writer) // 담당자 설정
                .password(password) // 비밀번호 설정
                .build(); // Schedule 객체 생성
    }
}
