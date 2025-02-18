package com.project.single.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
  private static final long serialVersionUID = -5691761716331805867L;

  private Object[] args;

  public CustomException() {

  }

  public CustomException(String message) {
    super(message);
  }

  public CustomException(String message, Object arg) {
    super(message);
    this.args = new Object[1];
    this.args[0] = arg;
  }

  public CustomException(String message, Object arg, Object arg2) {
    super(message);
    this.args = new Object[2];
    this.args[0] = arg;
    this.args[1] = arg2;
  }

  public CustomException(String message, Throwable cause) {
    super(message, cause);
  }

  public CustomException(Throwable cause) {
    super(cause);
  }

  public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
