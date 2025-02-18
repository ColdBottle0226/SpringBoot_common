package com.project.module.bo.mb.repository;

import com.project.module.bo.mb.domain.dto.UserDto;
import com.project.module.bo.mb.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    List<UserDto> checkDuplicated(String userId);

    Optional<UserDto> findByUserId(UserDto userDto);

    void join(UserVO userVO);
}
