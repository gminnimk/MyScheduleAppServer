package com.sparta.schedulemanagementappserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*

✅ RuntimeException을 상속받는 CustomeException 클래스. RuntimeException을 확장하여 사용자 정의 에외 정의.

    ➡️ 프로젝트에서 발생할 수 있는 특정 상황을 나타내는데 사용.

    ➡️ ErrorCode 에서 작성한 404 에러들은 따로 잡아주어야 하기 떄문에 필요한 클래스.

    ➡️ 이 예외 클래스는 ErrorEnum 클래스에서 정의한 상태 코드와 관련된 예외 상황을 처리하고 전달하는 데 사용.
 */
@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private final ErrorEnum statusEnum; // 예외 객체가 가지는 필드로, ErrorEnum 타입의 상태 코드를 나타냄.
    // statusEnum 필드는 ErrorEnum 타입으로, 예외 상황의 세부적인 상태를 나타내는 역할.
}