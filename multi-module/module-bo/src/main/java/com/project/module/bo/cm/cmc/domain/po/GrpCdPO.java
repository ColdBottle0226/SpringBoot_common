package com.project.module.bo.cm.cmc.domain.po;

import com.project.module.common.core.annotation.MaskingField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.project.module.bo.cm.cmc.domain.po
 * fileName       : GrpCdPO
 * author         : 32339
 * date           : 2025-02-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-20        32339       최초 생성
 */
@Getter
@Setter
@Schema(description = "그룹 코드 PO")
public class GrpCdPO {
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

    @Schema(description = "최초등록자명")
    @MaskingField(MaskingField.MaskingType.NAME)
    private String frRgerNm;                /* 최초등록자명 */

    @Schema(description = "최종등록자명")
    @MaskingField(MaskingField.MaskingType.NAME)
    private String lsUpdrNm;                /* 최종등록자명 */

}
