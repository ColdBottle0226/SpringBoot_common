package com.project.module.bo.mb.service.impl;

import com.project.module.common.core.exception.CustomException;
import com.project.module.common.core.model.CmmnResponseModel;
import com.project.module.bo.mb.domain.dto.UserDto;
import com.project.module.bo.mb.domain.vo.UserVO;
import com.project.module.bo.mb.exception.MemberException;
import com.project.module.bo.mb.exception.MemberFailure;
import com.project.module.bo.mb.repository.UserMapper;
import com.project.module.bo.mb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<UserDto> findByUserId(UserDto userVo) {
        return userMapper.findByUserId(userVo);
    }

    @Override
    public CmmnResponseModel memberJoin(UserDto userDto) {

        try{
            List<UserDto> duplicationList = userMapper.checkDuplicated(userDto.getUserId());
            // 기존 회원인지 확인
            if (duplicationList.size() > 0) {
                throw new MemberException(MemberFailure.ALREADY_JOINED_ID); // 이미 존재하는 회원입니다.
            }else{
                String passwordEncrypt = bCryptPasswordEncoder.encode(userDto.getUserPw());
                userDto.setUserPw(passwordEncrypt);

                // VO 변환
                UserVO userVO = UserVO.of(userDto);
                userMapper.join(userVO);

                return new CmmnResponseModel(true, "회원가입이 완료되었습니다!");
            }
        }catch(MemberException memberException){
            throw memberException;
        }
        catch(Exception e){
            log.error("에러발생", e);
            throw new CustomException("예상치 못한 에러가 발생했습니다", e.getMessage());
        }
    }

    @Override
    public List<UserDto> selectUserList(UserDto userDto) {
        return List.of();
    }



}
