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

import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.resources.file.enums.LevelEnum;
import com.ms.tools.resources.file.enums.VolumeSizeEnum;
import com.ms.tools.resources.file.factory.CompressionCore;
import com.ms.tools.resources.file.factory.DecompressionFactory;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Zip 压缩包处理工具类
 *
 * @author ms2297248353
 */
public class ZipUtils {

    /**
     * 密码解压
     *
     * @param sourceFile 待解压文件
     * @param targetDir  解压目录
     * @param password   密码 null 不使用密码
     * @return 解压后的文件目录
     * @throws MsToolsException 异常
     */
    public static File decompression(String sourceFile, String targetDir, String password) throws MsToolsException {
        File file = new File(targetDir);
        DecompressionFactory.decompression(new File(sourceFile), file, password);
        return file;
    }

    /**
     * 普通解压
     *
     * @param sourceFile 待解压文件
     * @param targetDir  解压目录
     * @return 解压后的文件目录
     * @throws MsToolsException 异常
     */
    public static File decompression(String sourceFile, String targetDir) throws MsToolsException {
        File file = new File(targetDir);
        DecompressionFactory.decompression(new File(sourceFile), file);
        return file;
    }


    /**
     * 普通压缩
     *
     * @param sourceDir 待压缩目录或文件
     * @throws MsToolsException 如果压缩失败，抛出该异常
     */
    public static void compression(String sourceDir) throws MsToolsException {
        CompressionCore.compression(sourceDir, null, null, null, null, null, null, null, null);
    }

    /**
     * 普通压缩
     *
     * @param sourceDir  待压缩目录或文件
     * @param zipFileDir 压缩文件保存目录 null 使用压缩目录父目录
     * @param fileName   压缩文件名(不含文件扩展名) null 使用压缩目录名
     * @throws MsToolsException 如果压缩失败，抛出该异常
     */
    public static void compression(String sourceDir, String zipFileDir, String fileName) throws MsToolsException {
        CompressionCore.compression(sourceDir, zipFileDir, fileName, null, null, null, null, null, null);
    }


    /**
     * 密码压缩
     *
     * @param sourceDir  待压缩目录或文件
     * @param zipFileDir 压缩文件保存目录 null 使用压缩目录父目录
     * @param fileName   压缩文件名(不含文件扩展名) null 使用压缩目录名
     * @param password   密码 null 时使用无密码
     * @throws MsToolsException 如果压缩失败，抛出该异常
     */
    public static void compression(String sourceDir, String zipFileDir, String fileName, String password) throws MsToolsException {
        CompressionCore.compression(sourceDir, zipFileDir, fileName, null, null, null, null, null, password);
    }


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
     * @param password   密码 null 时使用无密码
     * @throws MsToolsException 如果压缩失败，抛出该异常
     */
    public static void compression(String sourceDir, String zipFileDir, String fileName, String comment, LevelEnum level, VolumeSizeEnum volumeSize, Boolean isDelete, Charset encoding, String password) throws MsToolsException {
        CompressionCore.compression(sourceDir, zipFileDir, fileName, comment, level, volumeSize, isDelete, encoding, password);
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
        CompressionCore.compression(sourceDir, zipFileDir, fileName, comment, level, volumeSize, isDelete, encoding);
    }
}
