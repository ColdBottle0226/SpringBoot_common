package com.project.module.bo.cm.cmc.service;

import com.project.module.bo.cm.cmc.domain.po.GrpCdPO;
import com.project.module.bo.cm.cmc.domain.so.CmmnCdSO;
import com.project.module.bo.cm.cmc.domain.so.GrpCdSO;
import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;
import com.project.module.bo.cm.cmc.domain.vo.GrpCdVO;
import com.project.module.bo.mb.domain.dto.UserDto;

import java.util.List;

/**
 * packageName    : com.project.module.bo.cm.cmc.service
 * fileName       : CmmnCdService
 * author         : 32339
 * date           : 2025-02-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-18        32339       최초 생성
 */
public interface CmmnCdService {
    // 그룹 코드 기본 목록 조회
    List<GrpCdVO> selGrpCdList(GrpCdSO grpCdSO);

    // 그룹 코드 등록
    void insGrpCd(GrpCdPO grpCdPO, UserDto userDto);

    // 공통 코드 기본 목록 조회
    List<CmmnCdVO> selCmmnCdList(CmmnCdSO cmmnCdSO);

    // 공통 코드 상세 목록 조회
    List<CmmnCdVO> selCmmnCdDetailList(CmmnCdSO cmmnCdSO);

}
