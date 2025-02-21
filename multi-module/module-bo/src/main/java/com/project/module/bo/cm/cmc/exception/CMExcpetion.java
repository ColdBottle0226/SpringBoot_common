package com.project.module.bo.cm.cmc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.project.module.bo.cm.cmc.exception
 * fileName       : CMExcpetion
 * author         : 32339
 * date           : 2025-02-21
 * description    : 공통 업무 Exception
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-21        32339       최초 생성
 */
@Getter
public class CMExcpetion extends RuntimeException {

    private CmErrorCode errorCode;
    private HttpStatus status;
    private String message;

    /**
     * 생성자 1 : 예외 메시지 (param1), Http 상태 코드(param2)
     * @param message
     * @param status
     */
    public CMExcpetion(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
    }

    /**
     * 생성자 2 : 정의된 ErrorCode
     * ex) throw new CMExcpetion(ErrorCode.INVALID_REFRESH_TOKEN);
     * @param errorCode
     */
    public CMExcpetion(CmErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
