package com.project.module.bo.mb.controller;

import com.project.module.common.core.model.CmmnResponseModel;
import com.project.module.bo.common.model.TestModel;
import com.project.module.bo.common.service.TestService;
import com.project.module.bo.mb.domain.dto.UserDto;
import com.project.module.bo.mb.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@Tag(name = "Code-Controller", description = "Code 관리 API 엔드포인트")
public class UserController {
    private final TestService testService;
    private final UserService userService;


    /**
     * [API] 사용자 리스트 조회
     *
     * @return ResponseEntity
     */
    @PostMapping("/user")
    @Operation(summary = "코드 단건 조회", description = "코드 단건을 조회합니다.")
    public List<TestModel> selectZone() {
        return testService.selectZone();
    }

    /**
     * 회원 가입
     */
    @PostMapping("/join")
    @Operation(summary = "회원가입", description = "회원가입 실행")
    public ResponseEntity<CmmnResponseModel> memberJoin(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.memberJoin(userDto), HttpStatus.OK);
    }
}
