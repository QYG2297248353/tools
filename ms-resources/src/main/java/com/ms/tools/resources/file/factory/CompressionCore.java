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

package com.ms.tools.resources.file.factory;

import com.ms.tools.core.base.basic.Strings;
import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.resources.file.enums.LevelEnum;
import com.ms.tools.resources.file.enums.VolumeSizeEnum;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author ms2297248353
 */
public class CompressionCore {

    /**
     * 密码压缩
     *
     * @param sourceDir  待压缩目录或文件
     * @param zipFileDir 压缩文件保存目录 null 使用压缩目录父目录
     * @param fileName   压缩文件名(不含文件扩展名) null 使用压缩目录名
     * @param comment    压缩文件注释 允许 null
     * @param level      压缩级别 null 时使用默认级别
     * @param volumeSize 卷大小 Must be between 64kB and about 4GB (depending on the JVM). null 时使用默认卷大小
     * @param isDelete   是否删除源文件 null 时使用 false
     * @param encoding   编码 null 时使用 UTF-8
     * @param password   密码 null 时使用不加密
     * @throws MsToolsException 如果压缩失败，抛出该异常
     */
    public static void compression(String sourceDir, String zipFileDir, String fileName, String comment, LevelEnum level, VolumeSizeEnum volumeSize, Boolean isDelete, Charset encoding, String password) throws MsToolsException {
        File sourceD = CompressionFactory.checkSourceDir(sourceDir);
        zipFileDir = CompressionFactory.checkZipDir(sourceDir, zipFileDir);
        File zipFile = CompressionFactory.createCompression(sourceD, zipFileDir, fileName, ".zip");

        ZipFile zip;
        if (Strings.isNotBlank(password)) {
            zip = new ZipFile(zipFile, password.toCharArray());
            ZipParameters parameters = CompressionFactory.setZipConfig(zip, comment, level, encoding, true);
            CompressionFactory.createZip4JPassFile(sourceD, volumeSize, zip, parameters);
        } else {
            zip = new ZipFile(zipFile);
            ZipParameters parameters = CompressionFactory.setZipConfig(zip, comment, level, encoding, false);
            CompressionFactory.createZip4JZipFile(sourceD, volumeSize, zip, parameters);
        }

        if (Boolean.TRUE.equals(isDelete)) {
            FileUtils.deleteQuietly(sourceD);
        }
    }

    /**
     * 普通压缩
     *
     * @param sourceDir  待压缩目录或文件
     * @param zipFileDir 压缩文件保存目录 null 使用压缩目录父目录
     * @param fileName   压缩文件名(不含文件扩展名) null 使用压缩目录名
     * @param comment    压缩文件注释 允许 null
     * @param level      压缩级别 null 时使用默认级别
     * @param volumeSize 卷大小 Must be between 64kB and about 4GB (depending on the JVM). null 时使用默认卷大小
     * @param isDelete   是否删除源文件 null 时使用 false
     * @param encoding   编码 null 时使用 UTF-8
     * @throws MsToolsException 如果压缩失败，抛出该异常
     */
    public static void compression(String sourceDir, String zipFileDir, String fileName, String comment, LevelEnum level, VolumeSizeEnum volumeSize, Boolean isDelete, Charset encoding) throws MsToolsException {
        File sourceD = CompressionFactory.checkSourceDir(sourceDir);
        zipFileDir = CompressionFactory.checkZipDir(sourceDir, zipFileDir);
        File zipFile = CompressionFactory.createCompression(sourceD, zipFileDir, fileName, ".zip");
        try {
            ZipArchiveOutputStream zipArchiveOutputStream;
            if (volumeSize == null || volumeSize == VolumeSizeEnum.VOLUME_SIZE_0) {
                zipArchiveOutputStream = new ZipArchiveOutputStream(zipFile);
            } else {
                zipArchiveOutputStream = new ZipArchiveOutputStream(zipFile, volumeSize.getSize());
            }
            if (encoding == null) {
                encoding = StandardCharsets.UTF_8;
            }
            zipArchiveOutputStream.setEncoding(encoding.name());
            zipArchiveOutputStream.setUseLanguageEncodingFlag(true);
            if (Strings.isNotBlank(comment)) {
                zipArchiveOutputStream.setComment(comment);
            }
            if (level == null) {
                level = LevelEnum.NORMAL;
            }
            zipArchiveOutputStream.setLevel(level.getLevel());
            zipArchiveOutputStream.setFallbackToUTF8(true);
            CompressionFactory.addFileCompression(zipArchiveOutputStream, sourceD, sourceD.getName());
            zipArchiveOutputStream.finish();
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
        if (Boolean.TRUE.equals(isDelete)) {
            FileUtils.deleteQuietly(sourceD);
        }
    }
}
