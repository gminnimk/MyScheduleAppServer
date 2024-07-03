package com.sparta.schedulemanagementappserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


    /*

    ✅ CommonResponse 클래스는 API 응답의 공통 포맷을 정의하는 DTO입니다.

        ➡️ 이 클래스는 응답 상태 코드, 메시지, 데이터 세 가지 요소를 포함하며, 제네릭을 사용하여 다양한 타입의 데이터를 처리할 수 있습니다.

    */


@Getter // Lombok 라이브러리를 사용하여 getter 메서드를 자동으로 생성합니다.
@Setter // Lombok 라이브러리를 사용하여 setter 메서드를 자동으로 생성합니다.
@Builder // Lombok 라이브러리를 사용하여 빌더 패턴을 자동으로 생성합니다.
public class CommonResponse<T> {

    private Integer statusCode; // HTTP 상태 코드를 저장하는 필드 (성공 & 실패 여부 확인 )
    private String msg; // 응답 메시지를 저장하는 필드
    private T data; // 응답 데이터를 저장하는 제네릭 타입 필드

}