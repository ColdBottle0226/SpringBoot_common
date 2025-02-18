package com.project.module.common.core.model;

import java.io.Serializable;

/**
 * BO 공통 데이터 모델
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = -5767508192561569865L;

    private String rgpsId;
    private String regDttm;

    private String mdpsId;
    private String modDttm;

    private int totalCnt;                   /* 총개수 */
    private int dataAllTotal;               /* 데이터 전체 총개수(조회조건X) */
    private int currentPageCnt;             /* 현재페이지건수 */
    private int rnum;                       /* NO */
    private String status;                     /* 행상태 */

    private String frRgerId;                /* 최초등록자아이디 */
    private String frRgDtm;                 /* 최초등록일시 */
    private String lsUpdrId;                /* 최종수정자아이디 */
    private String lsUpdtDtm;               /* 최종수정일시 */
}
