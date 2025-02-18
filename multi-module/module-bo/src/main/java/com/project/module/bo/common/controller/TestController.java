package com.project.module.bo.common.controller;

import com.project.module.bo.common.auth.util.TokenUtil;
import com.project.module.common.core.model.CmmnResponseModel;
import com.project.module.bo.common.model.TestModel;
import com.project.module.bo.common.service.TestService;
import com.project.module.bo.mb.domain.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

@Tag(name="Test-Controller", description = "테스트 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    @PostMapping("/xss")
    @Operation(summary = "XSS 테스트 api", description = "XSS 테스트 api, 실행하면 치환된 문자 반환")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(schema = @Schema(implementation = TestModel.class))
    )
    public ResponseEntity<CmmnResponseModel<TestModel>> xssTest(@RequestBody TestModel testModel) {
        return new ResponseEntity<>(new CmmnResponseModel<>(testModel),HttpStatus.OK);
    }

    @PostMapping("/sampleList")
    @Operation(summary = "테스트 api", description = "zone 리스트 api")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(schema = @Schema(implementation = TestModel.class))
    )
    public ResponseEntity<CmmnResponseModel<List<TestModel>>> selectZone() {
        return new ResponseEntity<>(new CmmnResponseModel<>(testService.selectZone()), HttpStatus.OK);
    }


    @PostMapping("/token")
    @Operation(summary = "토큰 발급 api", description = "토큰 발급 api")
    public ResponseEntity<CmmnResponseModel<String>> generateToken(@RequestBody UserDto userDto) {
        String resultToken = TokenUtil.generateJwt(userDto);
        return new ResponseEntity<>(new CmmnResponseModel<>(resultToken), HttpStatus.OK);
    }

}
