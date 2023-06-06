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

package com.ms.push.email;

import com.ms.core.exception.base.MsToolsRuntimeException;
import com.ms.push.email.factory.BoxEmailFactory;
import com.ms.push.email.properties.MsBoxEmailProperties;
import com.ms.push.email.properties.MsEmailProperties;
import com.ms.push.email.template.EmailBoxTemplate;
import com.ms.push.email.template.EmailFolderTemplate;
import com.ms.push.email.template.PageBoxTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 邮件工具类
 * 收件箱管理
 *
 * @author ms2297248353
 */
@Component
public class EmailBoxUtils {

    private static final String DEFAULT_ERR_MSG = "MailUtils初始化失败，配置文件未加载";

    @Resource
    private MsEmailProperties msEmailProperties;

    @Resource
    private MsBoxEmailProperties msBoxEmailProperties;


    private EmailBoxUtils() {
    }

    public EmailBoxUtils(MsEmailProperties msEmailProperties, MsBoxEmailProperties msBoxEmailProperties) {
        this();
        if (msEmailProperties != null) {
            this.msEmailProperties = msEmailProperties;
        }
        if (msBoxEmailProperties != null) {
            this.msBoxEmailProperties = msBoxEmailProperties;
        }
        if (msBoxEmailProperties == null && msEmailProperties != null) {
            this.msEmailProperties = msEmailProperties;
            this.msBoxEmailProperties = msEmailProperties.getReceive();
        }
        if (this.msEmailProperties == null && this.msBoxEmailProperties == null) {
            throw new MsToolsRuntimeException(DEFAULT_ERR_MSG);
        }
    }

    public EmailBoxUtils(MsBoxEmailProperties msBoxEmailProperties) {
        this(null, msBoxEmailProperties);

    }

    public EmailBoxUtils(MsEmailProperties msEmailProperties) {
        this(msEmailProperties, null);
    }

    /**
     * 创建配置文件
     * <p>
     * 默认 网易 993 端口
     *
     * @param username 用户名
     * @param password 密码
     * @param host     主机
     * @return 配置文件
     */
    public static MsBoxEmailProperties createMsReceiveEmailProperties(String username, String password, String host) {
        return new MsBoxEmailProperties(username, password, host);
    }

    /**
     * 创建配置文件
     *
     * @param username 用户名
     * @param password 密码
     * @param host     主机
     * @param port     端口
     * @return 配置文件
     */
    public static MsBoxEmailProperties createMsReceiveEmailProperties(String username, String password, String host, int port) {
        return new MsBoxEmailProperties(username, password, host, port);
    }

    /**
     * 获取配置文件
     *
     * @return 配置文件
     */
    public MsBoxEmailProperties getMsReceiveEmailProperties() {
        if (msBoxEmailProperties == null && msEmailProperties != null) {
            msBoxEmailProperties = msEmailProperties.getReceive();
        }
        if (msBoxEmailProperties == null) {
            throw new MsToolsRuntimeException(DEFAULT_ERR_MSG);
        }
        return msBoxEmailProperties;
    }

    /**
     * 获取收件箱列表
     *
     * @param pageNo  页码
     * @param pageNum 每页数量
     * @return 收件箱列表
     */
    public PageBoxTemplate<List<EmailBoxTemplate>> getReceiveList(int pageNo, int pageNum) {
        return BoxEmailFactory.getReceiveList(getMsReceiveEmailProperties(), pageNo, pageNum);
    }

    /**
     * 获取文件夹列表
     *
     * @param folder  文件夹名称
     * @param pageNo  页码
     * @param pageNum 每页数量
     * @return 获取文件夹列表
     */
    public PageBoxTemplate<List<EmailBoxTemplate>> getBoxList(String folder, int pageNo, int pageNum) {
        return BoxEmailFactory.getBoxList(getMsReceiveEmailProperties(), folder, pageNo, pageNum);
    }


    /**
     * 邮件详情-收件箱
     *
     * @param messageId 邮件id
     * @return 邮件详情
     */
    public Optional<EmailBoxTemplate> getReceiveDetail(String messageId) {
        return getReceiveDetailEmail(messageId, false);
    }

    /**
     * 邮件详情(隐私查看)-收件箱
     *
     * @param messageId 邮件id
     * @return 邮件详情
     */
    public Optional<EmailBoxTemplate> getReceiveDetailPrivacy(String messageId) {
        return getReceiveDetailEmail(messageId, true);
    }

    /**
     * 邮件详情-收件箱
     *
     * @param messageId 邮件id
     * @param privacy   是否隐私查看
     * @return 邮件详情
     */
    public Optional<EmailBoxTemplate> getReceiveDetailEmail(String messageId, boolean privacy) {
        return BoxEmailFactory.getReceiveDetailEmail(getMsReceiveEmailProperties(), messageId, privacy);
    }

    /**
     * 邮件详情
     *
     * @param folder    文件夹名称
     * @param messageId 邮件id
     * @param privacy   是否隐私查看
     * @return 邮件详情
     */
    public Optional<EmailBoxTemplate> getBoxDetail(String folder, String messageId, boolean privacy) {
        return BoxEmailFactory.getBoxDetail(getMsReceiveEmailProperties(), folder, messageId, privacy);
    }

    /**
     * 删除邮件-收件箱
     *
     * @param messageId 邮件id
     * @return 是否成功
     */
    public boolean deleteReceiveEmail(String messageId) {
        return BoxEmailFactory.deleteReceiveEmail(getMsReceiveEmailProperties(), messageId);
    }

    /**
     * 删除邮件-收件箱
     *
     * @param messageIds 邮件id列表
     */
    public void deleteReceiveEmail(String... messageIds) {
        BoxEmailFactory.deleteReceiveEmail(getMsReceiveEmailProperties(), messageIds);
    }

    /**
     * 删除邮件
     *
     * @param folder    文件夹名称
     * @param messageId 邮件id
     * @return 是否成功
     */
    public boolean deleteBoxEmail(String folder, String messageId) {
        return BoxEmailFactory.deleteBoxEmail(getMsReceiveEmailProperties(), folder, messageId);
    }

    /**
     * 删除邮件
     *
     * @param folder     文件夹名称
     * @param messageIds 邮件id列表
     * @return 是否成功
     */
    public boolean deleteBoxEmail(String folder, String... messageIds) {
        return BoxEmailFactory.deleteBoxEmail(getMsReceiveEmailProperties(), folder, messageIds);
    }

    /**
     * 全部已读-收件箱
     *
     * @return 是否成功
     */
    public boolean readAll() {
        return BoxEmailFactory.readAll(getMsReceiveEmailProperties());
    }

    /**
     * 全部已读
     *
     * @param folder 文件夹名称
     * @return 是否成功
     */
    public boolean readAll(String folder) {
        return BoxEmailFactory.readAll(getMsReceiveEmailProperties(), folder);
    }


    /**
     * 获取文件夹列表
     *
     * @return 文件夹列表
     */
    public List<EmailFolderTemplate> getFolderList() {
        return BoxEmailFactory.getFolderList(getMsReceiveEmailProperties());
    }
}