package com.project.module.bo.cm.cmc.domain.dto;

import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;
import com.project.module.common.core.annotation.MaskingField;
import com.project.module.common.core.model.CmmnPagingModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.project.module.bo.cm.cmc.domain.dto
 * fileName       : CmmnCdDto
 * author         : 32339
 * date           : 2025-02-18
 * description    : DTO 는 VO와 달리 변형 가능하며, equals, hashCode를 재정의하지 않는다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-18        32339       최초 생성
 */
@Getter
@Setter
@Builder
@Schema(description = "공통 코드 DTO", hidden = true)
public class CmmnCdDto extends CmmnPagingModel {

    @Schema(description = "그룹코드", requiredMode = Schema.RequiredMode.REQUIRED)
    private String grpCd;

    @Schema(description = "업무대분류코드", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jobLclCd;

    @Schema(description = "그룹코드명")
    private String grpCdNm;

    @Schema(description = "그룹코드설명")
    private String grpCdDc;

    @Schema(description = "사용여부", requiredMode = Schema.RequiredMode.REQUIRED)
    private String useYn;

    @Schema(description = "첫번째사용자필드")
    private String fstUserFd;

    @Schema(description = "두번째사용자필드")
    private String scdUserFd;

    @Schema(description = "세번째사용자필드")
    private String thdUserFd;

    @Schema(description = "네번째사용자필드")
    private String fourUserFd;

    @Schema(description = "다섯번째사용자필드")
    private String ffthUserFd;

    @Schema(description = "여섯번째사용자필드")
    private String sixUserFd;

    @Schema(description = "공통코드")
    private String cmmnCd;

    @Schema(description = "공통코드명")
    private String cmmnCdNm;

    @Schema(description = "공통코드설명")
    private String cmmnCdDc;

    @Schema(description = "하위공통코드")
    private String lowCmmnCd;

    @Schema(description = "정렬순서")
    private int sortOrdr;

    @Schema(description = "최초등록자명")
    @MaskingField(MaskingField.MaskingType.NAME)
    private String frRgerNm;                /* 최초등록자명 */

    @Schema(description = "최종등록자명")
    @MaskingField(MaskingField.MaskingType.NAME)
    private String lsUpdrNm;                /* 최종등록자명 */


    public static CmmnCdDto of(CmmnCdVO vo) {
        return CmmnCdDto.builder()
                .grpCd(vo.getGrpCd())
                .jobLclCd(vo.getJobLclCd())
                .grpCdNm(vo.getGrpCdNm())
                .grpCdDc(vo.getGrpCdDc())
                .useYn(vo.getUseYn())
                .fstUserFd(vo.getFstUserFd())
                .scdUserFd(vo.getScdUserFd())
                .thdUserFd(vo.getThdUserFd())
                .fourUserFd(vo.getFourUserFd())
                .ffthUserFd(vo.getFfthUserFd())
                .sixUserFd(vo.getSixUserFd())
                .cmmnCd(vo.getCmmnCd())
                .cmmnCdNm(vo.getCmmnCdNm())
                .cmmnCdDc(vo.getCmmnCdDc())
                .lowCmmnCd(vo.getLowCmmnCd())
                .sortOrdr(vo.getSortOrdr())
                .frRgerNm(vo.getFrRgerNm())
                .lsUpdrNm(vo.getLsUpdrNm())
                .build();
    }

}