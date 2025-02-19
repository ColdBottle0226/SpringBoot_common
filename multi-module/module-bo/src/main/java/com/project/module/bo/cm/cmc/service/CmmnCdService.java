package com.project.module.bo.cm.cmc.service;

import com.project.module.bo.cm.cmc.domain.dto.CmmnCdDto;
import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;

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
    // 공통 코드 기본 목록 조회
    List<CmmnCdVO> selCmmnCdList(CmmnCdDto cmmnCdDto);

}
