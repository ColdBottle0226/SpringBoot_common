package com.project.module.bo.cm.cmc.domain.so;

import com.project.module.bo.cm.cmc.domain.vo.CmmnCdVO;
import com.project.module.common.core.annotation.MaskingField;
import com.project.module.common.core.model.CmmnPagingModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.project.module.bo.cm.cmc.domain.so
 * fileName       : CmmnCdSO
 * author         : 32339
 * date           : 2025-02-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-18        32339       최초 생성
 */
@Getter
@Setter
@Builder
@Schema(description = "공통 코드 SO", hidden = true)
public class CmmnCdSO extends CmmnPagingModel {

    @Schema(description = "그룹코드", requiredMode = Schema.RequiredMode.REQUIRED)
    private String grpCd;

    @Schema(description = "공통코드")
    private String cmmnCd;

    @Schema(description = "공통코드명")
    private String cmmnCdNm;

    @Schema(description = "공통코드설명")
    private String cmmnCdDc;

    @Schema(description = "정렬순서")
    private int sortOrdr;

    @Schema(description = "사용여부", requiredMode = Schema.RequiredMode.REQUIRED)
    private String useYn;

    @Schema(description = "최초등록자명")
    @MaskingField(MaskingField.MaskingType.NAME)
    private String frRgerNm;                /* 최초등록자명 */

    @Schema(description = "최종등록자명")
    @MaskingField(MaskingField.MaskingType.NAME)
    private String lsUpdrNm;                /* 최종등록자명 */


}