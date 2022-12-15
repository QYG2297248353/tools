/*
 * @MS 2022-12-13
 *  Copyright (c) 2001-2023 萌森
 *  保留所有权利
 *  本软件为萌森工作室所有及包含机密信息，须遵守其相关许可证条款进行使用。
 *  Copyright (c) 2001-2023 Meng Sen
 *  All rights reserved
 * This software is owned by Mengsen Studio and contains confidential information, and must be used in accordance with its relevant license terms.
 * Website：https://qyg2297248353.top
 */

package com.ms.base.enums.request;

/**
 * @author ms2297248353
 */

public enum MediaTypeEnum {
    /**
     * 不使用Body
     */
    NONE("none", "none"),

    /**
     * HTML格式
     */
    TEXT_HTML("text/html", "HTML文档"),

    /**
     * 纯文本格式
     * 空格转换为 “+” 加号
     * 不对特殊字符编码
     */
    TEXT_PLAIN("text/plain", "纯文本格式"),

    /**
     * XML格式
     */
    TEXT_XML("text/xml", "XML格式"),

    /**
     * Markdown格式
     */
    TEXT_X_MARKDOWN("text/x-markdown", "Markdown格式"),

    /**
     * gif图片格式
     */
    IMAGE_GIF("image/gif", "gif图片格式"),

    /**
     * jpg图片格式
     */
    IMAGE_JPEG("image/jpeg", "jpg图片格式"),

    /**
     * png图片格式
     */
    IMAGE_PNG("image/png", "png图片格式"),

    /**
     * XHTML格式
     */
    APPLICATION_XHTML_XML("application/xhtml+xml", "XHTML格式"),

    /**
     * XML数据格式
     */
    APPLICATION_XML("application/xml", "XML数据格式"),

    /**
     * 消息主体是序列化后的JSON字符串
     */
    APPLICATION_JSON("application/json", "JSON字符串"),

    /**
     * pdf格式
     */
    APPLICATION_PDF("application/pdf", "pdf格式"),

    /**
     * Word文档格式
     */
    APPLICATION_MS_WORD("application/msword", "Word文档格式"),

    /**
     * 二进制流数据
     * 例如文件下载
     */
    APPLICATION_OCTET_STREAM("application/octet-stream", "二进制流数据"),

    /**
     * 二进制流数据
     * 例如文件下载
     */
    APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded", "键值对形式"),

    /**
     * 参数为键值对形式
     * 在发送前编码所有字符（默认）
     * 浏览器的原生表单
     * 如果不设置 enctype 属性，那么最终就会以 application/x-www-form-urlencoded 方式提交数据
     */
    MULTIPART_FORM_DATA("multipart/form-data", "大量二进制数据");


    private String type;
    private String description;

    /**
     * 构建
     *
     * @param type
     * @param description
     */
    MediaTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * 获取类型
     *
     * @return 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 获取描述
     *
     * @return 描述
     */
    public String getDescription() {
        return description;
    }
}
