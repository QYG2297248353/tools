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
import com.ms.tools.api.tencent.sms.vo.SendSmsTencentVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 推送响应
 *
 * @author qyg2297248353
 */
public class SendSmsResponse {
    /**
     * 请求状态
     * 是否完成向服务商发送请求，具体是否下发成功调用详情获取
     */
    private Boolean status;

    /**
     * 是否全部成功
     */
    private Boolean success;

    /**
     * 成功数量
     */
    private Integer successQuantity;

    /**
     * 失败数量
     */
    private Integer failureQuantity;

    @JSONField(name = "RequestId")
    private String requestId;

    @JSONField(name = "SendStatusSet")
    private List<SendStatusSetRb> sendStatusSet;

    private Map<String, SendStatusSetRb> sendStatus;

    @JSONField(name = "Error")
    private ErrorRB error;

    public SendSmsResponse() {
        status = true;
        success = false;
        successQuantity = 0;
        failureQuantity = 0;
    }

    public SendSmsResponse(Boolean status, Boolean success, Integer successQuantity, Integer failureQuantity, String requestId, List<SendStatusSetRb> sendStatusSet, Map<String, SendStatusSetRb> sendStatus, ErrorRB error) {
        this.status = status;
        this.success = success;
        this.successQuantity = successQuantity;
        this.failureQuantity = failureQuantity;
        this.requestId = requestId;
        this.sendStatusSet = sendStatusSet;
        this.sendStatus = sendStatus;
        this.error = error;
    }

    public static SendSmsResponse errorResponse() {
        SendSmsResponse response = new SendSmsResponse();
        response.setStatus(false);
        response.setError(ErrorRB.error());
        return response;
    }

    public void finishing(SendSmsTencentVo.Send send) {
        if (error != null) {
            status = false;
        } else {
            int length = send.getPhoneNumber().length;
            int size = getSendStatusSet().size();
            int ok = (int) getSendStatusSet().stream().filter(r -> r.getCode().equalsIgnoreCase("ok")).count();
            if (ok == size && size == length) {
                setSuccess(true);
            }
            setSuccessQuantity(ok);
            setFailureQuantity(size - ok);

            sendStatus = new HashMap<>(sendStatusSet.size());
            sendStatusSet.forEach(d -> sendStatus.put(d.phoneNumber, d.finishing()));
        }
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getSuccessQuantity() {
        return successQuantity;
    }

    public void setSuccessQuantity(Integer successQuantity) {
        this.successQuantity = successQuantity;
    }

    public Integer getFailureQuantity() {
        return failureQuantity;
    }

    public void setFailureQuantity(Integer failureQuantity) {
        this.failureQuantity = failureQuantity;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<SendStatusSetRb> getSendStatusSet() {
        return sendStatusSet;
    }

    public void setSendStatusSet(List<SendStatusSetRb> sendStatusSet) {
        this.sendStatusSet = sendStatusSet;
    }

    public Map<String, SendStatusSetRb> getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Map<String, SendStatusSetRb> sendStatus) {
        this.sendStatus = sendStatus;
    }

    public ErrorRB getError() {
        return error;
    }

    public void setError(ErrorRB error) {
        this.error = error;
    }

    public static class SendStatusSetRb {
        @JSONField(name = "SerialNo")
        private String serialNo;
        @JSONField(name = "PhoneNumber")
        private String phoneNumber;
        @JSONField(name = "Fee")
        private Integer fee;
        @JSONField(name = "SessionContext")
        private String sessionContext;
        @JSONField(name = "Code")
        private String code;
        @JSONField(name = "Message")
        private String message;
        @JSONField(name = "IsoCode")
        private String isoCode;

        /**
         * 短信是否下发
         */
        private Boolean isSuccess;

        public SendStatusSetRb() {
            isSuccess = false;
        }

        public SendStatusSetRb(String serialNo, String phoneNumber, Integer fee, String sessionContext, String code, String message, String isoCode, Boolean isSuccess) {
            this.serialNo = serialNo;
            this.phoneNumber = phoneNumber;
            this.fee = fee;
            this.sessionContext = sessionContext;
            this.code = code;
            this.message = message;
            this.isoCode = isoCode;
            this.isSuccess = isSuccess;
        }

        public SendStatusSetRb finishing() {
            if (code.equalsIgnoreCase("ok")) {
                isSuccess = true;
            }
            return this;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Integer getFee() {
            return fee;
        }

        public void setFee(Integer fee) {
            this.fee = fee;
        }

        public String getSessionContext() {
            return sessionContext;
        }

        public void setSessionContext(String sessionContext) {
            this.sessionContext = sessionContext;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getIsoCode() {
            return isoCode;
        }

        public void setIsoCode(String isoCode) {
            this.isoCode = isoCode;
        }

        public Boolean getSuccess() {
            return isSuccess;
        }

        public void setSuccess(Boolean success) {
            isSuccess = success;
        }
    }
}
