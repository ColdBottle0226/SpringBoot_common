package com.project.single.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends RuntimeException {
  private ErrorCode errorCode;
  private HttpStatus status;
  private String message;

  /**
   * 생성자 1 : 예외 메시지 (param1), Http 상태 코드(param2)
   * ex) throw new OrCxrCommException("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
   * @param message
   * @param status
   */
  public CommonException(String message, HttpStatus status) {
    super(message);
    this.status = status;
    this.message = message;
  }

  /**
   * 생성자 2 : 정의된 ErrorCode
   * ex) throw new CommonException(ErrorCode.INVALID_REFRESH_TOKEN);
   * @param errorCode
   */
  public CommonException(ErrorCode errorCode){
    this.errorCode = errorCode;
  }

}
