package com.project.module.bo.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 데이터베이스와 연결 및 SQL Mapper(MyBatis)와의 연결을 담당하는 설정 클래스
 */
@Configuration
public class DataSourceConfig {

    final ApplicationContext applicationContext;

    public DataSourceConfig(ApplicationContext ac) {
        this.applicationContext = ac;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        session.setDataSource(dataSource);
        session.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
        session.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        return session.getObject();
    }
}
