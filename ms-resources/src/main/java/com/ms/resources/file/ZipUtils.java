package com.ms.resources.file;

import com.ms.core.exception.base.MsToolsException;
import com.ms.resources.file.enums.LevelEnum;
import com.ms.resources.file.enums.VolumeSizeEnum;
import com.ms.resources.file.factory.CompressionCore;

import java.nio.charset.Charset;

/**
 * Zip 压缩包处理工具类
 *
 * @author ms2297248353
 */
public class ZipUtils {

    /**
     * 压缩目录
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
     * 压缩目录
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
