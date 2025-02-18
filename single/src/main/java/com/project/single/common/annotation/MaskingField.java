package com.project.single.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaskingField {
    public MaskingType value() default MaskingType.NONE;

    enum MaskingType{
        ID,
        NAME,
        NAME_EN,
        EMAIL,
        PHONE_NUM,
        IP,
        REGIST_NUM,
        DRIVER_LICENSE,
        PASSPORT_NUM,
        CREDIT_CARD,
        PERSON_CUST_CLR_NUM,
        ACNT_NUM,
        DTL_ADDR,
        ADDR,
        ID_NAME,
        CI_DI,
        PIN_NUM,
        TEL_AREA_NO,
        NONE;
    }
}
