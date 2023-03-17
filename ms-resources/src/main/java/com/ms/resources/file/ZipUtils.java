package com.ms.resources.file;

import com.ms.core.base.basic.Strings;
import com.ms.core.exception.base.MsToolsException;
import com.ms.resources.file.enums.LevelEnum;
import com.ms.resources.file.enums.VolumeSizeEnum;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
     * @param sourceDir  待压缩目录 不包含文件名
     * @param zipFileDir 压缩文件保存目录 不包含文件名
     * @param fileName   压缩文件名 不包含后缀
     * @param comment    压缩文件注释
     * @param password   压缩文件密码
     * @param level      压缩级别
     * @param volumeSize 卷大小
     * @param isDelete   是否删除源文件
     * @param encoding   编码
     * @throws MsToolsException 如果压缩失败，抛出该异常
     */
    public static void compression(String sourceDir, String zipFileDir, String fileName, String comment, String password, LevelEnum level, VolumeSizeEnum volumeSize, boolean isDelete, Charset encoding) throws MsToolsException {
        File sourceD = new File(sourceDir);
        if (!sourceD.isDirectory() || !sourceD.exists()) {
            throw new MsToolsException("压缩目录不存在");
        }
        if (!sourceD.canRead()) {
            throw new MsToolsException("压缩目录不可读");
        }

        File zipD = new File(zipFileDir);
        if (zipD.exists() && zipD.isFile()) {
            throw new MsToolsException("存储路径不是目录");
        }
        if (!zipD.exists()) {
            zipD.mkdirs();
        }


        if (Strings.isBlank(fileName)) {
            fileName = sourceD.getName();
        }
        String zipFilePath = zipFileDir + File.separator + fileName + ".zip";
        File zipFile = new File(zipFilePath);
        if (zipFile.exists()) {
            throw new MsToolsException("压缩文件已存在");
        }

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(zipFile);
        } catch (FileNotFoundException e) {
            throw new MsToolsException(e);
        }
        ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(new DeflateCompressorOutputStream(fileOutputStream));
        // 设置编码
        zipArchiveOutputStream.setEncoding(encoding.name());
        // 使用语言编码
        zipArchiveOutputStream.setUseLanguageEncodingFlag(true);
        // 设置注释
        zipArchiveOutputStream.setComment(comment);
        // ZIP64支持
        zipArchiveOutputStream.setUseZip64(Zip64Mode.AsNeeded);
        // 设置压缩方式
        zipArchiveOutputStream.setMethod(ZipArchiveOutputStream.DEFLATED);
        // 设置压缩级别
        zipArchiveOutputStream.setLevel(level.getLevel());
        // 设置额外编码信息
        // zipArchiveOutputStream.setCreateUnicodeExtraFields(ZipArchiveOutputStream.UnicodeExtraFieldPolicy.ALWAYS);
        // 无法编码的字符使用UTF-8编码
        zipArchiveOutputStream.setFallbackToUTF8(true);


    }

}
