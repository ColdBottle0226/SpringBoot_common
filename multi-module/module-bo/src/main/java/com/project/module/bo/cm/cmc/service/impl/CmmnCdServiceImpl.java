package com.project.module.bo.cm.cmc.service.impl;

import com.project.module.bo.cm.cmc.domain.dto.CmmnCdDto;
import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;
import com.project.module.bo.cm.cmc.repository.CmmnCdMapper;
import com.project.module.bo.cm.cmc.service.CmmnCdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CmmnCdVO> selCmmnCdList(CmmnCdDto cmmnCdDto) {

        return cmmnCdMapper.selCmmnCdList(cmmnCdDto);
    }
}
