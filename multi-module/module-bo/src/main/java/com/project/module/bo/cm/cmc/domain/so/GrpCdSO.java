package com.project.module.bo.cm.cmc.domain.so;

import com.project.module.common.core.annotation.MaskingField;
import com.project.module.common.core.model.CmmnPagingModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.project.module.bo.cm.cmc.domain.SO
 * fileName       : GrpCdSO
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
@Schema(description = "그룹 코드 SO", hidden = true)
public class GrpCdSO extends CmmnPagingModel {
    @Schema(description = "그룹코드", requiredMode = Schema.RequiredMode.REQUIRED)
    private String grpCd;

    @Schema(description = "업무대분류코드", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jobLclCd;

    @Schema(description = "그룹코드명")
    private String grpCdNm;

    @Schema(description = "사용여부", requiredMode = Schema.RequiredMode.REQUIRED)
    private String useYn;

}
