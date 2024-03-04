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

package com.ms.tools.resources.file;


import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 文件类型工具类
 */
public class FileTypeUtils {


    /**
     * 获取文件类型
     *
     * @param file 文件
     * @return 文件类型 contentType
     */
    public static String getFileType(File file) {
        try {
            String contentType = Files.probeContentType(file.toPath());
            if (contentType != null) {
                return contentType;
            }
        } catch (IOException ignored) {
        }

        String contentType = "application/octet-stream";
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1) {
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            switch (suffix) {
                case "jpg":
                case "jpeg":
                    contentType = "image/jpeg";
                    break;
                case "png":
                    contentType = "image/png";
                    break;
                case "gif":
                    contentType = "image/gif";
                    break;
                case "bmp":
                    contentType = "image/bmp";
                    break;
                case "mp4":
                    contentType = "video/mp4";
                    break;
                case "avi":
                    contentType = "video/avi";
                    break;
                case "flv":
                    contentType = "video/flv";
                    break;
                case "mp3":
                    contentType = "audio/mp3";
                    break;
                case "wav":
                    contentType = "audio/wav";
                    break;
                case "wma":
                    contentType = "audio/wma";
                    break;
                case "wmv":
                    contentType = "audio/wmv";
                    break;
                case "pdf":
                    contentType = "application/pdf";
                    break;
                case "doc":
                    contentType = "application/msword";
                    break;
                case "docx":
                    contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                    break;
                case "xls":
                    contentType = "application/vnd.ms-excel";
                    break;
                case "xlsx":
                    contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                    break;
                case "ppt":
                    contentType = "application/vnd.ms-powerpoint";
                    break;
                case "pptx":
                    contentType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
                    break;
                case "txt":
                    contentType = "text/plain";
                    break;
                default:
                    break;
            }
        }
        if ("application/octet-stream".equals(contentType)) {
            Tika tika = new Tika();
            try {
                contentType = tika.detect(file);
            } catch (IOException ignored) {
            }
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }

}
