package com.project.single.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 1. CORS 허용
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**") // 모든 API 경로 CORS 허용
                .allowedOrigins("http://localhost:3000") // 허용할 도메인
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 쿠키, 인증 정보 포함 허용
    }
    // 2. interceptor(로그인 체크, JWT 인증..)
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(customInterceptor)
//                .addPathPatterns("/api/**") // 적용할 URL 패턴 지정
//                .excludePathPatterns("/api/auth/**"); // 예외 URL 설정 (ex: 로그인, 회원가입 API 제외)
    }
    // 3. resourceHandler(정적 파일 별도의 디렉터리)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") // 클라이언트에서 접근할 경로
                .addResourceLocations("file:/Users/myapp/uploads/") // 실제 파일이 저장된 로컬 경로
                .setCachePeriod(3600); // 캐시 유지 시간 (1시간)
    }

    /**
     * ============================================
     * XSS 방어 설정
     * ============================================
     *
     */
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());  // 기존 HTMLCharacterEscapes 적용
//        return objectMapper;
//    }

    /**
     * 모든 JSON 요청(@RequestBody) XSS 방어 적용
     */
    @Bean
    public MappingJackson2HttpMessageConverter jsonEscapeConverter(ObjectMapper objectMapper) {
        ObjectMapper copy = objectMapper.copy();
        copy.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
        return new MappingJackson2HttpMessageConverter(copy);
    }

}
