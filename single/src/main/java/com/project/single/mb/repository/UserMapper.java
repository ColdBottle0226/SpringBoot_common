package com.project.single.mb.repository;

import com.project.single.mb.domain.dto.UserDto;
import com.project.single.mb.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    List<UserDto> checkDuplicated(String userId);

    Optional<UserDto> login(UserDto userDto);

    void join(UserVO userVO);
}
