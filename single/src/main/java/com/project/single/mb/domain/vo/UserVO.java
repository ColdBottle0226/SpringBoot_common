package com.project.single.mb.domain.vo;

import com.project.single.mb.domain.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor  // 기본 생성자 추가 (MyBatis가 userSeq 값을 설정할 수 있도록)
@AllArgsConstructor // 모든 필드 값을 받는 생성자 추가 (of()에서 사용)
public class UserVO {
    @Schema(description = "회원 seq", example = "1234")
    private int userSeq;

    @Schema(description = "회원 Id", example = "kbc1234")
    private String userId;

    @Schema(description = "회원명", example = "test")
    private String userName;

    @Schema(description = "비밀번호", example = "1234")
    private String userPw;

    // 시퀀스 번호에 대한 Setter 추가 (MyBatis에서 자동 증가된 값을 설정)
    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    // DTO → VO 변환을 위한 Factory Method
    public static UserVO of(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("UserDto가 null입니다.");
        }
        if (userDto.getUserId() == null || userDto.getUserName() == null || userDto.getUserPw() == null) {
            throw new IllegalArgumentException("필수 값이 누락되었습니다.");
        }

        return new UserVO(0, userDto.getUserId(), userDto.getUserName(), userDto.getUserPw());
        // userSeq는 MyBatis에서 설정되므로 기본값 0으로 초기화
    }
}