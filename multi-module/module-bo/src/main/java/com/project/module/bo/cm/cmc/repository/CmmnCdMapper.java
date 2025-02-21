package com.project.module.bo.cm.cmc.repository;

import com.project.module.bo.cm.cmc.domain.po.GrpCdPO;
import com.project.module.bo.cm.cmc.domain.so.CmmnCdSO;
import com.project.module.bo.cm.cmc.domain.so.GrpCdSO;
import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;
import com.project.module.bo.cm.cmc.domain.vo.GrpCdVO;
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
    // 그룹 코드 기본 목록 조회
    List<GrpCdVO> selGrpCdList(GrpCdSO grpCdSO);

    // 그룹 코드 등록
    void insGrpCd(GrpCdPO grpCdPO);

    // 공통 코드 기본 목록 조회
    List<CmmnCdVO> selCmmnCdList(CmmnCdSO cmmnCdSO);

    // 공통 코드 상세 목록 조회
    List<CmmnCdVO> selCmmnCdDetailList(CmmnCdSO cmmnCdSO);

}
