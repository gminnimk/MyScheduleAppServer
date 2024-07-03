package com.sparta.schedulemanagementappserver.exception;

import com.sparta.schedulemanagementappserver.dto.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*

✅ 각 예외 상황에 따라 적절한 HTTP 상태 코드와 메시지를 클라이언트에게 반환하여 사용자 경험을 개선 &
    서버에서 발생할 수 있는 다양한 예외를 관리하는 클래스.

    즉, 전역 예외 처리를 담당하는 클래스.



    ➡️ Logger 를 사용하여 예외 발생 시 로그를 남기도록 구현 => 문제 해결과 디버깅에 유용.

 */


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
     CustomException을 처리하는 핸들러 메서드입니다.

     발생한 CustomException 객체
     CommonResponse를 ResponseEntity로 포장하여 반환
     */
    @ExceptionHandler({CustomException.class})
    protected ResponseEntity handleCustomException(CustomException ex) {
        log.error("CustomException occurred: {}", ex.getMessage());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(ex.getStatusEnum().getStatusCode())
                .msg(ex.getStatusEnum().getMsg())
                .build());
    }

    /*
     MethodArgumentNotValidException을 처리하는 핸들러 메서드입니다.

     발생한 MethodArgumentNotValidException 객체
     CommonResponse를 ResponseEntity로 포장하여 반환
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CommonResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException occurred: {}", ex.getMessage());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .msg(ex.getBindingResult().getFieldError().getDefaultMessage())
                .build());
    }

    /*
     IllegalArgumentException을 처리하는 핸들러 메서드입니다.

     발생한 IllegalArgumentException 객체
     CommonResponse를 ResponseEntity로 포장하여 반환
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CommonResponse> handleValidationExceptions(IllegalArgumentException ex) {
        log.error("IllegalArgumentException occurred: {}", ex.getMessage());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .msg(ex.getMessage())
                .build());
    }

    /*
     기타 모든 예외를 처리하는 핸들러 메서드입니다.

     발생한 Exception 객체
     CommonResponse를 ResponseEntity로 포장하여 반환
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse> handleException(Exception ex) {
        log.error("Exception occurred: {}", ex.getMessage());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg("서버에서 오류가 발생했습니다.")
                .build());
    }
}