package com.project.single.common.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Redis 내에 Token 블랙리스트 관리하는 서비스
 */
@Service
public interface TokenBlackListService {

    void addTokenToList(String value);              // Redis key-value 형태로 리스트 추가

    boolean isContainToken(String value);           // Redis key 기반으로 리스트 조회

    List<Object> getTokenBlackList();               // Redis Key 기반으로 BlackList를 조회

    void removeToken(String value);                 // Redis Key 기반으로 리스트 내 요소 제거
}
