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

public class TemplateValidationSmsResponse {
    /**
     * 请求状态
     * 是否完成向服务商发送请求，具体是否下发成功调用详情获取
     */
    private Boolean status;

    @JSONField(name = "RequestId")
    private String requestId;

    @JSONField(name = "DescribeTemplateStatusSet")
    private List<DescribeTemplateStatusSetRB> describeTemplateStatusSet;

    @JSONField(name = "Error")
    private ErrorRB error;

    private Map<Integer, DescribeTemplateStatusSetRB> describeTemplateStatus;

    public TemplateValidationSmsResponse() {
        status = true;
    }

    public TemplateValidationSmsResponse(Boolean status, String requestId, List<DescribeTemplateStatusSetRB> describeTemplateStatusSet, ErrorRB error, Map<Integer, DescribeTemplateStatusSetRB> describeTemplateStatus) {
        this.status = status;
        this.requestId = requestId;
        this.describeTemplateStatusSet = describeTemplateStatusSet;
        this.error = error;
        this.describeTemplateStatus = describeTemplateStatus;
    }

    public static TemplateValidationSmsResponse errorResponse() {
        TemplateValidationSmsResponse response = new TemplateValidationSmsResponse();
        response.setStatus(false);
        response.setError(ErrorRB.error());
        return response;
    }

    public void finishing() {
        if (error != null) {
            status = false;
        } else {
            describeTemplateStatus = new HashMap<>(describeTemplateStatusSet.size());
            describeTemplateStatusSet.forEach(d -> describeTemplateStatus.put(d.templateId, d.finishing()));
        }
    }

    public DescribeTemplateStatusSetRB getDescribeSignResponse(Integer templateId) {
        return describeTemplateStatus.get(templateId);
    }

    public Boolean getDescribeSignStatus(Integer templateId) {
        return describeTemplateStatus.get(templateId).isAvailable;
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

    public List<DescribeTemplateStatusSetRB> getDescribeTemplateStatusSet() {
        return describeTemplateStatusSet;
    }

    public void setDescribeTemplateStatusSet(List<DescribeTemplateStatusSetRB> describeTemplateStatusSet) {
        this.describeTemplateStatusSet = describeTemplateStatusSet;
    }

    public ErrorRB getError() {
        return error;
    }

    public void setError(ErrorRB error) {
        this.error = error;
    }

    public Map<Integer, DescribeTemplateStatusSetRB> getDescribeTemplateStatus() {
        return describeTemplateStatus;
    }

    public void setDescribeTemplateStatus(Map<Integer, DescribeTemplateStatusSetRB> describeTemplateStatus) {
        this.describeTemplateStatus = describeTemplateStatus;
    }

    @Data
    public static class DescribeTemplateStatusSetRB {
        /**
         * 模板ID
         */
        @JSONField(name = "TemplateId")
        private Integer templateId;
        /**
         * 是否国际/港澳台短信，其中
         * 0表示国内短信
         * 1表示国际/港澳台短信
         */
        @JSONField(name = "International")
        private Integer international;
        /**
         * 申请模板状态，其中:
         * 0表示审核通过
         * 1表示审核中
         * -1：表示审核未通过或审核失败。
         */
        @JSONField(name = "StatusCode")
        private Integer statusCode;
        /**
         * 审核回复，审核人员审核后给出的回复，通常是审核未通过的原因。
         */
        @JSONField(name = "ReviewReply")
        private String reviewReply;
        /**
         * 模板名称
         */
        @JSONField(name = "TemplateName")
        private String templateName;
        /**
         * 模板内容
         */
        @JSONField(name = "TemplateContent")
        private String templateContent;
        /**
         * 提交审核时间，UNIX 时间戳
         */
        @JSONField(name = "CreateTime")
        private Integer createTime;

        /**
         * 模板是否可用
         */
        private Boolean isAvailable;

        public DescribeTemplateStatusSetRB() {
            isAvailable = false;
        }

        public DescribeTemplateStatusSetRB finishing() {
            if (statusCode == 0) {
                isAvailable = true;
            }
            return this;
        }
    }
}
