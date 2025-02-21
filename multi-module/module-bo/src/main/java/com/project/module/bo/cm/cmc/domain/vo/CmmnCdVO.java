package com.project.module.bo.cm.cmc.domain.vo;

import com.project.module.common.core.annotation.MaskingField;
import com.project.module.common.core.model.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * packageName    : com.project.module.bo.cm.cmc.domain.vo
 * fileName       : CmmnCdVO
 * author         : 32339
 * date           : 2025-02-18
 * description    : VO는 equals와 hashcode를 재정의하여 필드가 같은경우 동일한 객체로 판단한다.
 *                  불변성의 특징이 있으므로, 변경 차단이 필요한 경우에 사용한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-18        32339       최초 생성
 */
@Getter
@EqualsAndHashCode(callSuper = false)
@Schema(description = "공통 코드 VO")
public class CmmnCdVO extends BaseModel {

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
    private String frRgerNm;                /* 최초등록자명 */

    @Schema(description = "최종등록자명")
    private String lsUpdrNm;                /* 최종등록자명 */

    @Schema(description = "최초등록자아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private String frRgerId;

    @Schema(description = "최초등록일시", requiredMode = Schema.RequiredMode.REQUIRED)
    private String frRgDtm;

    @Schema(description = "최종수정자아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lsUpdrId;

    @Schema(description = "최종수정일시", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lsUpdtDtm;
}
