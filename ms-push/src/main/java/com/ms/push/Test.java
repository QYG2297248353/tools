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

package com.ms.push;

import com.ms.push.email.EmailBoxUtils;
import com.ms.push.email.template.EmailFolderTemplate;

import java.util.List;

/**
 * @author ms2297248353
 * @date 2023年06月06日 13:55
 */
public class Test {

    public static void main(String[] args) {
        // PageBoxTemplate<List<EmailBoxTemplate>> boxList = new EmailBoxUtils(EmailBoxUtils.createMsReceiveEmailProperties("qyg2297248353@163.com", "OLSTAADHCATXPLOG", "imap.163.com"))
        //         .getReceiveList(1, 20);
        // List<EmailBoxTemplate> data = boxList.getData();
        // for (EmailBoxTemplate datum : data) {
        //     System.out.println(datum);
        // }

        // Optional<EmailBoxTemplate> detail = new EmailBoxUtils(EmailBoxUtils.createMsReceiveEmailProperties("qyg2297248353@163.com", "OLSTAADHCATXPLOG", "imap.163.com"))
        //         .getReceiveDetail("4737");
        // EmailBoxTemplate x = detail.get();
        // System.out.println(x);


        List<EmailFolderTemplate> folderList = new EmailBoxUtils(EmailBoxUtils.createMsReceiveEmailProperties("qyg2297248353@163.com", "OLSTAADHCATXPLOG", "imap.163.com"))
                .getFolderList();

        for (EmailFolderTemplate emailFolderTemplate : folderList) {
            System.out.println(emailFolderTemplate);
        }
    }

}