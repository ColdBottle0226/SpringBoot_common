package com.project.module.bo.cm.cmc.domain.po;

import com.project.module.bo.mb.domain.dto.UserDto;
import com.project.module.common.core.annotation.MaskingField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.project.module.bo.cm.cmc.domain.po
 * fileName       : CmmnCdPO
 * author         : 32339
 * date           : 2025-02-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-20        32339       최초 생성
 */
@Getter
@Builder
@Schema(description = "공통 코드 PO")
public class CmmnCdPO {
    @Schema(description = "그룹코드", requiredMode = Schema.RequiredMode.REQUIRED)
    private String grpCd;

    @Schema(description = "공통코드", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cmmnCd;

    @Schema(description = "공통코드명")
    private String cmmnCdNm;

    @Schema(description = "공통코드설명")
    private String cmmnCdDc;

    @Schema(description = "사용순서")
    private String sortOrdr;

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

    @Schema(description = "최초등록자아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private String frRgerId;

    @Schema(description = "최초등록일시", requiredMode = Schema.RequiredMode.REQUIRED)
    private String frRgDtm;

    @Schema(description = "최종수정자아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lsUpdrId;

    @Schema(description = "최종수정일시", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lsUpdtDtm;

    /**
     * UserDto 기반으로 CmmnCdPO 생성
     */
    public static CmmnCdPO applyAuth(CmmnCdPO po, UserDto userDto) {
        return CmmnCdPO.builder()
                .grpCd(po.getGrpCd())
                .cmmnCd(po.getCmmnCd())
                .cmmnCdNm(po.getCmmnCdNm())
                .cmmnCdDc(po.getCmmnCdDc())
                .sortOrdr(po.getSortOrdr())
                .useYn(po.getUseYn())
                .fstUserFd(po.getFstUserFd())
                .scdUserFd(po.getScdUserFd())
                .thdUserFd(po.getThdUserFd())
                .fourUserFd(po.getFourUserFd())
                .ffthUserFd(po.getFfthUserFd())
                .sixUserFd(po.getSixUserFd())
                .frRgerId(userDto.getUserId()) // 최초 등록자 자동 설정
                .lsUpdrId(userDto.getUserId()) // 최종 수정자 자동 설정
                .build();
    }
}
