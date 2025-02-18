package com.project.single.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Async 비동기 작업 Executor 설정
 * ex) @Async("commonExecutor")
 *     - public 접근제한자 사용 필수
 *     - 반환 필요시, CompletableFuture<T> 사용
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    /**
     * 공통 Executor
     */
    @Bean(name = "commonExecutor")
    public Executor commonExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();     // ThreadPoolTaskExecutor 클래스 사용
        executor.setCorePoolSize(10);                                       // 스레드 풀이 최소한으로 유지할 스레드 수
        executor.setMaxPoolSize(20);                                        // 스레드 풀이 동시에 사용할 수 있는 최대 스레드 수
        executor.setQueueCapacity(100);                                     // 작업 큐의 용량, 큐 가득 차면 작업 거부
        executor.setThreadNamePrefix("CommonAsync-");                       // 생성되는 스레드의 이름 접두사
        executor.initialize();                                              // 설정된 값을 바탕으로 executor를 초기화
        return executor;
    }
}
