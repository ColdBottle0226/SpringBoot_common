package com.project.single.common.auth.handler;

import com.project.single.common.auth.domain.UserDetailsDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 전달받은 사용자의 아이디와 비밀번호를 기반으로 비즈니스 로직을 처리하여 사용자의 ‘인증’에 대해서 검증을 수행하는 클래스
 * CustomAuthenticationFilter로 부터 생성한 토큰을 통하여 ‘UserDetailsService’를 통해 데이터베이스 내에서 정보를 조회한다
 */
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @NonNull
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("2.CustomAuthenticationProvider");

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        // 'AuthenticationFilter' 에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        String userId = token.getName();
        String userPw = (String) token.getCredentials();

        // Spring Security - UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        UserDetailsDto userDetailsDto = (UserDetailsDto) userDetailsService.loadUserByUsername(userId);

        // DB에서 조회한 정보(UserDetailDto)와 제출한 비밀번호 비교
        if (!(userDetailsDto.getUserPw().equalsIgnoreCase(userPw))) {
            throw new BadCredentialsException(userDetailsDto.getUserName() + "Invalid password");
        }
//        if (!passwordEncoder.matches(userPw, userDetailsDto.getUserPw())) {
//            throw new BadCredentialsException("Invalid password for user: " + userDetailsDto.getUserName());
//        }
        // 인증이 성공하면 인증된 사용자의 정보와 권한을 담은 새로운 UsernamePasswordAuthenticationToken을 반환
        return new UsernamePasswordAuthenticationToken(userDetailsDto, userPw, userDetailsDto.getAuthorities());
    }

    // UsernamePasswordAuthenticationToken 클래스를 지원한다는 여부를 반환 -> CustomAuthenticationFilter에서 provider 찾을 수 있게 함
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
