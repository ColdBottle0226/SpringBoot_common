package com.project.module.bo.cm.cmc.service.impl;

import com.project.module.bo.cm.cmc.domain.po.CmmnCdPO;
import com.project.module.bo.cm.cmc.domain.po.GrpCdPO;
import com.project.module.bo.cm.cmc.domain.so.CmmnCdSO;
import com.project.module.bo.cm.cmc.domain.so.GrpCdSO;
import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;
import com.project.module.bo.cm.cmc.domain.vo.GrpCdVO;
import com.project.module.bo.cm.cmc.exception.CMExcpetion;
import com.project.module.bo.cm.cmc.exception.CmErrorCode;
import com.project.module.bo.cm.cmc.repository.CmmnCdMapper;
import com.project.module.bo.cm.cmc.service.CmmnCdService;
import com.project.module.bo.mb.domain.dto.UserDto;
import com.project.module.common.core.exception.CommonException;
import com.project.module.common.core.exception.CustomException;
import com.project.module.common.core.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * packageName    : com.project.module.bo.cm.cmc.service.impl
 * fileName       : CmmnCdServiceImpl
 * author         : 32339
 * date           : 2025-02-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-18        32339       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CmmnCdServiceImpl implements CmmnCdService {

    private final CmmnCdMapper cmmnCdMapper;

    @Override
    public List<GrpCdVO> selGrpCdList(GrpCdSO grpCdSO) {
        return cmmnCdMapper.selGrpCdList(grpCdSO);
    }

    @Override
    @Transactional(rollbackOn = CMExcpetion.class)
    public void insCmmnCd(CmmnCdPO cmmnCdPO, UserDto userDto) {
        // 1. 그룹코드 존재 여부 확인
        GrpCdVO grpCdVO = cmmnCdMapper.checkGrpCodeAndUseYn(cmmnCdPO.getGrpCd());
        if (grpCdVO.getTotalCnt() == 0) {
            throw new CMExcpetion(CmErrorCode.NOT_EXIST_GROUP_CODE);
        }

        // 2. useYn이 'N'인지 확인
        if ("N".equalsIgnoreCase(grpCdVO.getUseYn())) {
            throw new CMExcpetion(CmErrorCode.NOT_USE_GROUP_CODE);
        }

        CmmnCdPO updateAuthPO = CmmnCdPO.applyAuth(cmmnCdPO, userDto);

        // 3. 공통코드 등록
        cmmnCdMapper.insCmmnCd(updateAuthPO);
    }

    @Override
    public void insGrpCd(GrpCdPO grpCdPO, UserDto userDto) {

        GrpCdPO updateAuthPO = GrpCdPO.applyAuth(grpCdPO, userDto);
        cmmnCdMapper.insGrpCd(updateAuthPO);
    }

    @Override
    public List<CmmnCdVO> selCmmnCdList(CmmnCdSO cmmnCdSO) {

        return cmmnCdMapper.selCmmnCdList(cmmnCdSO);
    }

    @Override
    public List<CmmnCdVO> selCmmnCdDetailList(CmmnCdSO cmmnCdSO) {
        return cmmnCdMapper.selCmmnCdDetailList(cmmnCdSO);
    }


}

