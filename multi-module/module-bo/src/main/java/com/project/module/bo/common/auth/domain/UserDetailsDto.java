package com.project.module.bo.common.auth.domain;

import com.project.module.bo.mb.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Slf4j
@Getter
@AllArgsConstructor
public class UserDetailsDto implements UserDetails {

    @Delegate
    private UserDto userDto;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 사용자의 권한 목록을 반환
     *
     * @return Collection<? extends GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 사용자의 비밀번호를 반환
     *
     * @return String
     */
    @Override
    public String getPassword() {
        return userDto.getUserPw();
    }

    /**
     * 사용자의 이름을 반환
     *
     * @return String
     */
    @Override
    public String getUsername() {
        return userDto.getUserName();
    }

    /**
     * 계정이 만료되지 않았는지 여부를 반환
     * 현재 항상 false를 반환하므로, 모든 계정이 만료된 것으로 처리
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 계정이 잠기지 않았는지 여부를 반환
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }


    /**
     * 자격 증명(비밀번호)이 만료되지 않았는지 여부를 반환
     *
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 계정이 활성화되어 있는지 여부를 반환
     *
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
