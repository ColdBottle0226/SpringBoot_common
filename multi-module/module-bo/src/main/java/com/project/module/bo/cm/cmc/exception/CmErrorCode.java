package com.project.module.bo.cm.cmc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.project.module.bo.cm.cmc.exception
 * fileName       : CmErrorCode
 * author         : 32339
 * date           : 2025-02-21
 * description    : 공통 업무 에러코드
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-21        32339       최초 생성
 */
@Getter
@AllArgsConstructor
public enum CmErrorCode {

    ALREADY_EXIST_CODE("이미 존재하는 코드입니다"),
    NOT_EXIST_GROUP_CODE("그룹 코드가 존재하지 않습니다."),
    NOT_USE_GROUP_CODE("사용 불가능한 그룹코드입니다.");



    private final String code;
}
