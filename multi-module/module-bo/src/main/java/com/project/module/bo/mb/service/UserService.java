package com.project.module.bo.mb.service;


import com.project.module.common.core.model.CmmnResponseModel;
import com.project.module.bo.mb.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 정보를 조회해오기 위한 인터페이스
 */
public interface UserService {

    /**
     * 로그인 비즈니스 로직
     * @param userDto
     * @return
     */
    Optional<UserDto> login(UserDto userDto);

    /**
     * 회원가입
     * @param userDto
     * @return
     */
    CmmnResponseModel memberJoin(UserDto userDto);

    List<UserDto> selectUserList(UserDto userDto);
}
