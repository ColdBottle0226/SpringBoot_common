package com.project.module.bo.common.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.module.bo.common.auth.domain.UserDetailsDto;
import com.project.module.bo.common.auth.util.TokenUtil;
import com.project.module.bo.mb.domain.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 사용자의 '인증'에 대해 성공하였을때, 수행하여 사용자에게 사용자 정보 및 JWT에 대한 응답 값을 제공해주는 Handler
 */
@Slf4j
@Configuration
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        log.debug("3.1. CustomLoginSuccessHandler");

        // [STEP1] 사용자와 관련된 정보를 모두 조회
        UserDto userDto = ((UserDetailsDto) authentication.getPrincipal()).getUserDto();

        // [STEP2] 응답 데이터를 구성
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("userInfo", userDto);

        // [STEP3] 사용자의 상태에 따라 응답 데이터를 설정
//        if ("D".equals(userDto.getUserSt())) {
//            responseMap.put("resultCode", 9001);
//            responseMap.put("token", null);
//            responseMap.put("failMsg", "휴면 계정입니다.");
//        } else {
            responseMap.put("resultCode", 200);
            responseMap.put("failMsg", null);
            String accessToken = TokenUtil.generateJwt(userDto);
            String refreshToken = TokenUtil.generateRefreshToken(userDto);
            log.debug("생성된 토큰(접근 토큰) :: {}", accessToken);
            log.debug("생성된 토큰(리프레시 토큰) :: {}", accessToken);
            responseMap.put("accessToken", accessToken);
            responseMap.put("refreshToken", refreshToken);
            response.addHeader("Authorization", "BEARER " + accessToken);
//        }

        // [STEP4] 구성한 응답 값을 JSON 형태로 전달한다.
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(objectMapper.writeValueAsString(responseMap));
        printWriter.flush();
        printWriter.close();
    }
}