package com.project.single.common.annotation;

import java.lang.annotation.*;

@Inherited                                  // 상속
@Target({ElementType.TYPE})                 // 클래스, 인터페이스, enum
@Retention(RetentionPolicy.RUNTIME)
public @interface MaskingClass {
}
