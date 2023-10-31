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

package com.ms.tools.components.cloud.qiniu.utils;

import com.qiniu.util.Hex;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ms
 */
public class UploadFileUtils {

    /**
     * 获取文件的sha256
     *
     * @param file 文件
     * @return sha256
     * @throws IOException              io异常
     * @throws NoSuchAlgorithmException 算法异常
     */
    public static String getSha256(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = bis.read(buffer)) != -1) {
            digest.update(buffer, 0, bytesRead);
        }
        byte[] hash = digest.digest();
        return Hex.encodeHexString(hash);
    }
}
