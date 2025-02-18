package com.project.module.bo.mb.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * tb_user_mst 테이블에 매핑되는 DTO 클래스
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "임시 회원 DTO")
public class UserDto {
    @Schema(description = "회원 시퀀스", example = "1")
    private int userSeq;

    @Schema(description = "회원 Id", example = "kbc1234")
    private String userId;

    @Schema(description = "회원명", example = "test")
    private String userName;

    @Schema(description = "비밀번호", example = "1234")
    private String userPw;


    @Builder(toBuilder = true)
    private UserDto(int userSeq, String userId, String userName, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userSeq = userSeq;
    }
}
