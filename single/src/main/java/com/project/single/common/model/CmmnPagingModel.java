package com.project.single.common.model;

import lombok.Data;

/**
 * 공통 페이징 모델
 * - SO에서 extends 하여, 페이징 정보 사용
 */
@Data
public class CmmnPagingModel {
    private static final long serialVersionUID = 5751129366412456715L;

    private int currentPage = 0;            /* 현재 페이지 번호 */
    private int pageSize = 50;              /* 한페이지에 보여줄 개수 */
    private int firstIndex;                 /* 리스트 첫번째 인덱스 */
    private int lastIndex;                  /* 리스트 마지막 인덱스 */

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if(this.currentPage != 0) {
            this.firstIndex = this.pageSize * (this.currentPage-1) + 1;
            this.lastIndex = this.pageSize * this.currentPage;
        }
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getFirstIndex() {
        if(this.currentPage != 0) {
            if(this.firstIndex != 0) {
                return this.firstIndex;
            }
            this.firstIndex = this.pageSize * (this.currentPage-1) + 1;
            return this.firstIndex;
        }
        return 1;
    }

    public int getLastIndex() {
        if(this.currentPage != 0) {
            if(this.lastIndex != 0) {
                return this.lastIndex;
            }
            this.lastIndex = this.pageSize * this.currentPage;
            return this.lastIndex;
        }
        return this.pageSize;
    }
}
