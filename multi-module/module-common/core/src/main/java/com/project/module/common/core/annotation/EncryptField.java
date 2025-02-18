package com.project.module.common.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)          // 필드 (인스턴스 변수) 에만 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptField {
}
