package com.project.module.bo.cm.cmc.controller;

import com.project.module.bo.cm.cmc.domain.so.CmmnCdSO;
import com.project.module.bo.cm.cmc.domain.so.GrpCdSO;
import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;
import com.project.module.bo.cm.cmc.domain.vo.GrpCdVO;
import com.project.module.bo.cm.cmc.service.CmmnCdService;
import com.project.module.common.core.model.CmmnResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *packageName    : com.project.module.bo.cm.cmc.controller
 * fileName       : CmmnCdController
 * author         : 32339
 * date           : 2025-02-18
 * description    : 공통코드 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-18        32339       최초 생성
 */
@RestController
@RequestMapping("/api/cm/cmc")
@Tag(name = "CmmnCd-Controller", description = "공통 코드 관리 API")
public class CmmnCdController {
    @Autowired
    private CmmnCdService cmmnCdService;

    @Operation(summary = "그룹 코드 기본 목록", description = "그룹 코드 기본 조회")
    @PostMapping("/grpCdList")
    public ResponseEntity<CmmnResponseModel<List<GrpCdVO>>> selGrpCdList(@RequestBody GrpCdSO grpCdSO) {
        // 공통 코드 기본 목록 조회
        return new ResponseEntity<>(new CmmnResponseModel<>(cmmnCdService.selGrpCdList(grpCdSO)), HttpStatus.OK);
    }

    @Operation(summary = "공통 코드 기본 목록", description = "공통 코드 기본 조회")
    @PostMapping("/cmmnCdList")
    public ResponseEntity<CmmnResponseModel<List<CmmnCdVO>>> selCmmnCdList(@RequestBody CmmnCdSO cmmnCdSO) {
        // 공통 코드 기본 목록 조회
        return new ResponseEntity<>(new CmmnResponseModel<>(cmmnCdService.selCmmnCdList(cmmnCdSO)), HttpStatus.OK);
    }

}
