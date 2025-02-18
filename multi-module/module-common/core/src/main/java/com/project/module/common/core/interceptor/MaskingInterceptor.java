package com.project.module.common.core.interceptor;

import com.project.module.common.core.annotation.MaskingClass;
import com.project.module.common.core.util.MaskingUtil;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mybatis 인터셉터
 */
@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class MaskingInterceptor implements Interceptor {

    /**
     * methodName : intercept
     * author :
     * description :  Mybatis에서 ResultSet 처리시 인터셉트하여 Masking 처리한다.
     *
     * @param invocation
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object resultObject = invocation.proceed();
        if (Objects.isNull(resultObject)) {
            return null;
        }
        if (resultObject instanceof List) {
            ArrayList resultList = (ArrayList) resultObject;
            if (!CollectionUtils.isEmpty(resultList) ) { //&& needToMasking(resultList.get(0)) -> BaseModel이 필요하여 전체 기준으로 변경
                for (Object result : resultList) {
                    //masking Each record
                    MaskingUtil.maskingFieldProcess(result);
                }
            }
        } else {
            if (needToMasking(resultObject)) {
                MaskingUtil.maskingFieldProcess(resultObject);
            }
        }
        return resultObject;
    }

    /**
     * methodName : needToMasking
     * author :
     * description :  Masking 대상 Class 여부 확인
     *
     * @param object
     */
    private boolean needToMasking(Object object) {
        Class objectClass = object.getClass();
        MaskingClass maskingData = AnnotationUtils.findAnnotation(objectClass, MaskingClass.class);
        return Objects.nonNull(maskingData);
    }
}