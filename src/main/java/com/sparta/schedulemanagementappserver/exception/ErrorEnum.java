package com.sparta.schedulemanagementappserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*

✅ Enum 클래스로 사용할 에러들을 작성, 프로젝트에서 발생할 수 있는 여러 예외 상황을 정의.

    ➡️ status 값과 error message 만 프론트에 넘겨줄 예정으로 두 개만 작성.

 */

@NoArgsConstructor
@AllArgsConstructor
@Getter

// 열거형 클래스로, 프로젝트에서 발생할 수 있는 예외 상황을 정의.
public enum ErrorEnum {

    // post
    BAD_POSTID(400, "일정 ID를 찾을 수 없습니다."),
    BAD_PASSWORD(400, "비밀번호를 확인해주세요");

    int statusCode; // HTTP 상태 코드
    String msg; // 클라이언트에게 전달할 메시지
}