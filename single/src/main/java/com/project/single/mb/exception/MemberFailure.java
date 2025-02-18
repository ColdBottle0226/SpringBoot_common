package com.project.single.mb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberFailure {
    NON_INPUT_ID("001", "아이디가 입력되지 않았습니다."),

    NON_INPUT_MOBILE("002", "휴대전화번호가 입력되지 않았습니다."),

    NON_INPUT_PASSWORD("003", "비밀번호가 입력되지 않았습니다."),

    NON_INPUT_EMAIL("004", "이메일 주소가 입력되지 않았습니다."),

    INVALILD_ACCESS("005", "잘못된 접근입니다."),

    INVALID_PASSWORD_FORMAT("006", "비밀번호 형식이 맞지 않습니다.\\n8~16자의 영문 소문자/영문 대문자/숫자/특수문자 중 3가지 이상의 조합으로 입력해주세요."),

    ALREADY_JOINED_ID("007", "이미 가입된 아이디 입니다."),

    ALREADY_JOINED_MOBILE("008", "이미 가입된 휴대전화번호 입니다."),

    INVALID_AUTH_ACCESS("009", "정상적인 접근이 아닙니다."),

    LEFT_MEMBER("010", "탈퇴한 회원입니다. 탈퇴 후 1주일동안 재가입이 불가능합니다."),

    ALREADY_EXISTS_JOINED_INFO("011", "이미 가입된 정보가 있습니다."),

    OTHERS("999", "회원가입 중 오류가 발생하였습니다. 잠시 후 다시 시도 해주세요."),

    BLACK_LIST("012", "블랙리스트회원 가입실패 메세지"), //TODO 문구변경필요

    INVALID_BIZ_NO("013", "사업자등록번호 조회 실패 시 사업자 정보를 찾을 수 없습니다.\n국세청 홈택스(www.hometax.go.kr) 또는 국번없이 126번을 통해 사업자등록번호 등록상태를 확인하실 수 있습니다."),

    ALREADY_EXISTS_JOINED_BIZ("014", "이미 사용 중인 사업자등록번호입니다.\n아이디/비밀번호 찾기를 이용해주세요.");
    ;

    private final String code;

    private final String message;
}
