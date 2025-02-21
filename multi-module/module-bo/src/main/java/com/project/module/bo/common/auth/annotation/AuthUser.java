package com.project.module.bo.common.auth.annotation;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * packageName    : com.project.module.bo.common.auth.annotation
 * fileName       : AuthUser
 * author         : 32339
 * date           : 2025-02-21
 * description    : 로그인한 사용자의 정보 어노테이션
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-21        32339       최초 생성
 */
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(hidden = true)
/* 인증 객체 정보를 가지고 다니는 커스텀 어노테이션, AuthDetail의 필드인 member를 가져온다. */
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : userDto")
public @interface AuthUser {
}