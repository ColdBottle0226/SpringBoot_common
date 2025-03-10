package com.project.single.mb.service.impl;

import com.project.single.mb.domain.dto.UserDto;
import com.project.single.common.exception.CustomException;
import com.project.single.mb.domain.vo.UserVO;
import com.project.single.mb.exception.MemberException;
import com.project.single.mb.exception.MemberFailure;
import com.project.single.mb.repository.UserMapper;
import com.project.single.common.model.CmmnResponseModel;
import com.project.single.mb.service.UserService;
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
    public Optional<UserDto> login(UserDto userVo) {
        return userMapper.login(userVo);
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
