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

package com.ms.api.tencent.sms.response;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.List;

/**
 * @author ms2297248353
 */

public class SmsSendStatusResponse {

    /**
     * 请求状态
     * 是否完成向服务商发送请求，具体是否下发成功调用详情获取
     */
    private Boolean status;

    /**
     * 数据列表
     */
    @JSONField(name = "PullSmsSendStatusSet")
    private List<PullSmsSendStatusSetDTO> pullSmsSendStatusSet;

    /**
     * 请求唯一标识
     */
    @JSONField(name = "RequestId")
    private String requestId;

    /**
     * 错误信息
     */
    @JSONField(name = "Error")
    private ErrorRB error;

    /**
     * 数量
     */
    private Integer total;

    /**
     * 成功数量
     */
    private Integer success;

    /**
     * 失败数量
     */
    private Integer fail;

    public SmsSendStatusResponse() {
        status = true;
        total = success = fail = 0;
    }

    public static SmsSendStatusResponse errorResponse() {
        SmsSendStatusResponse response = new SmsSendStatusResponse();
        response.setStatus(false);
        response.setError(ErrorRB.error());
        return response;
    }

    public void finishing() {
        if (error != null) {
            status = false;
        }
        if (pullSmsSendStatusSet != null) {
            total = pullSmsSendStatusSet.size();
            for (PullSmsSendStatusSetDTO dto : pullSmsSendStatusSet) {
                if (dto.getReportStatus() != null && "SUCCESS".equals(dto.getReportStatus())) {
                    success++;
                } else {
                    fail++;
                }
            }
        }
    }

    public List<PullSmsSendStatusSetDTO> getPullSmsSendStatusSet() {
        return pullSmsSendStatusSet;
    }

    public void setPullSmsSendStatusSet(List<PullSmsSendStatusSetDTO> pullSmsSendStatusSet) {
        this.pullSmsSendStatusSet = pullSmsSendStatusSet;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ErrorRB getError() {
        return error;
    }

    public void setError(ErrorRB error) {
        this.error = error;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }

    public static class PullSmsSendStatusSetDTO {
        /**
         * 用户接收短信状态描述
         */
        @JSONField(name = "Description")
        private String description;

        /**
         * 国家（或地区）码
         */
        @JSONField(name = "CountryCode")
        private String countryCode;

        /**
         * 手机号码
         */
        @JSONField(name = "SubscriberNumber")
        private String subscriberNumber;
        /**
         * 用户接收短信状态
         * SUCCESS（成功）、FAIL（失败）
         */
        @JSONField(name = "ReportStatus")
        private String reportStatus;
        /**
         * 手机号码，E.164标准，+[国家或地区码][手机号]
         * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号
         */
        @JSONField(name = "PhoneNumber")
        private String phoneNumber;
        /**
         * 本次发送标识 ID，标识一次短信下发记录
         */
        @JSONField(name = "SerialNo")
        private String serialNo;
        /**
         * 用户接收时间，UNIX 时间戳（单位：秒）
         */
        @JSONField(name = "UserReceiveTime")
        private Integer userReceiveTime;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getSubscriberNumber() {
            return subscriberNumber;
        }

        public void setSubscriberNumber(String subscriberNumber) {
            this.subscriberNumber = subscriberNumber;
        }

        public String getReportStatus() {
            return reportStatus;
        }

        public void setReportStatus(String reportStatus) {
            this.reportStatus = reportStatus;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public Integer getUserReceiveTime() {
            return userReceiveTime;
        }

        public void setUserReceiveTime(Integer userReceiveTime) {
            this.userReceiveTime = userReceiveTime;
        }
    }
}