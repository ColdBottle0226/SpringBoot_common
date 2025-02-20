package com.project.module.bo.cm.cmc.service.impl;

import com.project.module.bo.cm.cmc.domain.so.CmmnCdSO;
import com.project.module.bo.cm.cmc.domain.so.GrpCdSO;
import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;
import com.project.module.bo.cm.cmc.domain.vo.GrpCdVO;
import com.project.module.bo.cm.cmc.repository.CmmnCdMapper;
import com.project.module.bo.cm.cmc.service.CmmnCdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public List<CmmnCdVO> selCmmnCdList(CmmnCdSO cmmnCdSO) {

        return cmmnCdMapper.selCmmnCdList(cmmnCdSO);
    }
}
