package com.project.module.bo.common.handler;

import com.project.module.common.core.exception.CommonException;
import com.project.module.common.core.exception.ErrorResponse;
import com.project.module.bo.mb.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * 예외 발생 캐치 핸들러 (throw 예외 및 언체크 예외)
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    // 전체 예외를 캐치
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<?> handleCommonException(Exception e) {

        // 공통 예외(CommonException) 라면
        if (e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            log.error("handleCommonException throw CommonException : {}", commonException.getErrorCode());
            log.error("handleCommonException throw CommonException : {}",
                    commonException.getErrorCode().getHttpStatus());
            log.error("handleCommonException throw CommonException : {}",
                    commonException.getErrorCode().getDetail());

            return ErrorResponse.toResponseEntity(commonException.getErrorCode().getHttpStatus(),
                    commonException.getErrorCode().getDetail());
        } else if (e instanceof NullPointerException) {
            log.error("handleException throw NullPointerException :");
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getCause().toString());
        } else if (e instanceof NumberFormatException) {
            log.error("handleException throw NumberFormatException :");
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getCause().toString());
        } else if (e instanceof IOException) {
            log.error("handleException throw IOException :");
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getCause().toString());
        } else if (e instanceof ArithmeticException) {
            log.error("handleException throw ArithmeticException :");
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getCause().toString());
        }
        // 회원 관련 exception
        else if (e instanceof MemberException) {
            log.error("handleException throw MemberException :", ((MemberException) e).getErrorCode());
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                    ((MemberException) e).getErrorCode().getCode(),
                    ((MemberException) e).getErrorCode().getMessage());
        }
        // 유효성 검증 실패 exception
        else if (e instanceof MethodArgumentNotValidException) {
            log.error("handleException throw MethodArgumentNotValidException :");
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(HttpStatus.BAD_REQUEST,
                    e.getCause().toString());
        }
        // Custom Exception
        else {

            log.error(" Another Exception : {}", e);
            log.error(" Another Exception Cause : {}", e.getCause().toString());
            StackTraceElement[] ste = e.getStackTrace();
            String className = ste[0].getClassName();
            String methodName = ste[0].getMethodName();
            int lineNumber = ste[0].getLineNumber();
            String fileName = ste[0].getFileName();
            log.error("### " + className + "." + methodName + "###");
            log.error("# FileName : " + fileName);
            log.error("# LineNumber : " + lineNumber);
            e.printStackTrace();
        }

        return ErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getCause().toString());
    }
}


