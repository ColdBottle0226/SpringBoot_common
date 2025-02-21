package com.project.module.bo.cm.cmc.domain.vo;

import com.project.module.bo.cm.cmc.domain.so.GrpCdSO;
import com.project.module.common.core.model.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * packageName    : com.project.module.bo.cm.cmc.domain.vo
 * fileName       : GrpCdVO
 * author         : 32339
 * date           : 2025-02-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-20        32339       최초 생성
 */
@Getter
@EqualsAndHashCode(callSuper = false)
@Schema(description = "그룹 코드 VO")
public class GrpCdVO extends BaseModel {
    @Schema(description = "그룹코드")
    private String grpCd;

    @Schema(description = "업무대분류코드")
    private String jobLclCd;

    @Schema(description = "그룹코드명")
    private String grpCdNm;

    @Schema(description = "그룹코드설명")
    private String grpCdDc;

    @Schema(description = "사용여부")
    private String useYn;

    @Schema(description = "첫번째사용자필드")
    private String fstUserFd;

    @Schema(description = "두번째사용자필드" )
    private String scdUserFd;

    @Schema(description = "세번째사용자필드" )
    private String thdUserFd;

    @Schema(description = "네번째사용자필드" )
    private String fourUserFd;

    @Schema(description = "다섯번째사용자필드" )
    private String ffthUserFd;

    @Schema(description = "여섯번째사용자필드" )
    private String sixUserFd;

    private String frRgerNm;                /* 최초등록자명 */

    private String lsUpdrNm;                /* 최종등록자명 */

}
