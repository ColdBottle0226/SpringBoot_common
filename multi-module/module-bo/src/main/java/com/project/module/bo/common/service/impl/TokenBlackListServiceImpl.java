package com.project.module.bo.common.service.impl;

import com.project.module.bo.common.service.TokenBlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenBlackListServiceImpl implements TokenBlackListService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final String REDIS_BLACK_LIST_KEY = "tokenBlackList";

    /**
     * BlackList 내에 토큰 추가
     * @param value
     */
    @Override
    public void addTokenToList(String value) {
        redisTemplate.opsForList().rightPush(REDIS_BLACK_LIST_KEY, value);
    }

    /**
     * BlackList 내에 토큰이 존재하는지 여부 확인
     * @param value
     * @return
     */
    @Override
    public boolean isContainToken(String value) {
        List<Object> allItems = redisTemplate.opsForList().range(REDIS_BLACK_LIST_KEY, 0, -1);
        return allItems.stream()
                .anyMatch(item -> item.equals(value));
    }

    /**
     * BlackList 항목을 모두 조회
     * @return
     */
    @Override
    public List<Object> getTokenBlackList() {
        return redisTemplate.opsForList().range(REDIS_BLACK_LIST_KEY, 0, -1);
    }

    /**
     * BlackList 내에서 항목을 제거
     * @param value
     */
    @Override
    public void removeToken(String value) {
        redisTemplate.opsForList().remove(REDIS_BLACK_LIST_KEY, 0, value);
    }
}
