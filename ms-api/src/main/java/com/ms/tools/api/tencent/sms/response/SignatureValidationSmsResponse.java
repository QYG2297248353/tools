/*
 * @MS 2022-12-13
 * Copyright (c) 2001-2023 萌森
 * 保留所有权利
 * 本软件为萌森工作室所有及包含机密信息，须遵守其相关许可证条款进行使用。
 * Copyright (c) 2001-2023 Meng Sen
 * All rights reserved
 * This software is owned by Mengsen Studio and contains confidential information, and must be used in accordance with its relevant license terms.
 * Website：https://qyg2297248353.top
 */

package com.ms.tools.api.tencent.sms.response;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SignatureValidationSmsResponse {
    /**
     * 请求状态
     * 是否完成向服务商发送请求，具体是否下发成功调用详情获取
     */
    private Boolean status;

    @JSONField(name = "RequestId")
    private String requestId;

    @JSONField(name = "DescribeSignListStatusSet")
    private List<DescribeSignListStatusSetRB> describeSignListStatusSet;

    @JSONField(name = "Error")
    private ErrorRB error;

    private Map<Integer, DescribeSignListStatusSetRB> describeSignListStatus;

    public SignatureValidationSmsResponse() {
        status = true;
    }

    public SignatureValidationSmsResponse(Boolean status, String requestId, List<DescribeSignListStatusSetRB> describeSignListStatusSet, ErrorRB error, Map<Integer, DescribeSignListStatusSetRB> describeSignListStatus) {
        this.status = status;
        this.requestId = requestId;
        this.describeSignListStatusSet = describeSignListStatusSet;
        this.error = error;
        this.describeSignListStatus = describeSignListStatus;
    }

    public static SignatureValidationSmsResponse errorResponse() {
        SignatureValidationSmsResponse response = new SignatureValidationSmsResponse();
        response.setStatus(false);
        response.setError(ErrorRB.error());
        return response;
    }

    public void finishing() {
        if (error != null) {
            status = false;
        } else {
            if (describeSignListStatusSet.isEmpty()) {
                status = false;
                error = new ErrorRB();
                error.setCode("Data is empty");
                error.setMessage("Template data not queried");
            } else {
                describeSignListStatus = new HashMap<>(describeSignListStatusSet.size());
                describeSignListStatusSet.forEach(d -> describeSignListStatus.put(d.signId, d.finishing()));
            }
        }
    }

    public DescribeSignListStatusSetRB getDescribeSignResponse(Integer signId) {
        return describeSignListStatus.get(signId);
    }

    public Boolean getDescribeSignStatus(Integer signId) {
        return describeSignListStatus.get(signId).isAvailable;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<DescribeSignListStatusSetRB> getDescribeSignListStatusSet() {
        return describeSignListStatusSet;
    }

    public void setDescribeSignListStatusSet(List<DescribeSignListStatusSetRB> describeSignListStatusSet) {
        this.describeSignListStatusSet = describeSignListStatusSet;
    }

    public ErrorRB getError() {
        return error;
    }

    public void setError(ErrorRB error) {
        this.error = error;
    }

    public Map<Integer, DescribeSignListStatusSetRB> getDescribeSignListStatus() {
        return describeSignListStatus;
    }

    public void setDescribeSignListStatus(Map<Integer, DescribeSignListStatusSetRB> describeSignListStatus) {
        this.describeSignListStatus = describeSignListStatus;
    }

    @Data
    public static class DescribeSignListStatusSetRB {
        /**
         * 签名名称
         */
        @JSONField(name = "SignName")
        private String signName;
        /**
         * 是否国际/港澳台短信，其中
         * 0表示国内短信
         * 1表示国际/港澳台短信
         */
        @JSONField(name = "International")
        private Integer international;
        /**
         * 签名ID
         */
        @JSONField(name = "SignId")
        private Integer signId;
        /**
         * 审核回复，审核人员审核后给出的回复，通常是审核未通过的原因。
         */
        @JSONField(name = "ReviewReply")
        private String reviewReply;
        /**
         * 提交审核时间，UNIX 时间戳
         */
        @JSONField(name = "CreateTime")
        private Integer createTime;
        /**
         * 申请签名状态，其中:
         * 0表示审核通过
         * 1表示审核中
         * -1：表示审核未通过或审核失败。
         */
        @JSONField(name = "StatusCode")
        private Integer statusCode;

        /**
         * 签名是否可用
         */
        private Boolean isAvailable;

        public DescribeSignListStatusSetRB() {
            isAvailable = false;
        }

        public DescribeSignListStatusSetRB finishing() {
            if (statusCode == 0) {
                isAvailable = true;
            }
            return this;
        }
    }
}
