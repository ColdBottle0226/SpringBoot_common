package com.project.single.common.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 응답 데이터(REST 결과)를 일반화하기 위해 사용
 * - isSuccess : true / false
 * - message : 응답 메시지
 * - data : 실제 응답 데이터
 * @param <T>
 */
@Data
@ToString
public class CmmnResponseModel<T> {
    private boolean isSuccess;
    private String message;
    private T data;

    private Map<String, Object> extraData;
    private String extMessage;
    private String returnCode;

    /**
     * 기본 생성자
     * - isSuccess : true
     * - message, data : null로 초기화
     * - extraData : map
     * ex)  CmmnResponseModel<String> response = new CmmnResponseModel<>();
     *      response.put("moreInfo", "Completed without errors"); // extraData map에 추가
     */
    public CmmnResponseModel() {
        this.isSuccess = true;
        this.message = null;
        this.data = null;
        this.extraData = new HashMap<>();
    }

    /**
     * Data 필드 초기화 생성자
     * - isSuccess :true
     * - message : null
     * - data : data
     * - extraData : map
     * ex) public CmmnResponseModel<String> createDataResponse(String username) {
     *     return new CmmnResponseModel<>(username); // 데이터만 포함
     * }
     */
    public CmmnResponseModel(T data) {
        this.data = data;
        this.isSuccess = true;
        this.message = null;
        this.extraData = new HashMap<>();
    }

    public CmmnResponseModel(boolean isSuccess, String msg) {
        this.isSuccess = isSuccess;
        this.message = msg;
        this.data = null;
        this.extraData = new HashMap<>();
    }

    /**
     * extraData 맵에 추가 및 값 얻기
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        extraData.put(key, value);
    }

    public Object get(String key) {
        return extraData.get(key);
    }
}
