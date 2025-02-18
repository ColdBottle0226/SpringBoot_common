package com.project.module.common.core.config;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * 서로 다른 모듈에서 bean 이름 중복 방지
 */
public class BeanNameConfig implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        final String beanName;

        beanName = ((AnnotatedBeanDefinition)definition).getMetadata().getClassName();
        return beanName;
    }
}
