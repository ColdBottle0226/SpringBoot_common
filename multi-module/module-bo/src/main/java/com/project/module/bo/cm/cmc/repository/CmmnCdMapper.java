package com.project.module.bo.cm.cmc.repository;

import com.project.module.bo.cm.cmc.domain.dto.CmmnCdDto;
import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName    : com.project.module.bo.cm.cmc.repository
 * fileName       : CmmnCdMapper
 * author         : 32339
 * date           : 2025-02-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-18        32339       최초 생성
 */
@Mapper
public interface CmmnCdMapper {
    // 공통 코드 기본 목록 조회
    List<CmmnCdVO> selCmmnCdList(CmmnCdDto cmmnCdDto);

}
