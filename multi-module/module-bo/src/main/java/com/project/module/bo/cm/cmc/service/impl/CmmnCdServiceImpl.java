package com.project.module.bo.cm.cmc.service.impl;

import com.project.module.bo.cm.cmc.domain.po.GrpCdPO;
import com.project.module.bo.cm.cmc.domain.so.CmmnCdSO;
import com.project.module.bo.cm.cmc.domain.so.GrpCdSO;
import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;
import com.project.module.bo.cm.cmc.domain.vo.GrpCdVO;
import com.project.module.bo.cm.cmc.repository.CmmnCdMapper;
import com.project.module.bo.cm.cmc.service.CmmnCdService;
import com.project.module.bo.mb.domain.dto.UserDto;
import com.project.module.common.core.exception.CommonException;
import com.project.module.common.core.exception.CustomException;
import com.project.module.common.core.exception.ErrorCode;
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
