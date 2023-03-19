package com.ms.resources.file;

import com.ms.core.base.basic.Strings;
import com.ms.core.exception.base.MsToolsException;
import com.ms.resources.file.enums.LevelEnum;
import com.ms.resources.file.enums.VolumeSizeEnum;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

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
     * @throws MsToolsException 如果压缩失败，抛出该异常
     */
    public static void compression(String sourceDir, String zipFileDir, String fileName, String comment, LevelEnum level, VolumeSizeEnum volumeSize, Boolean isDelete, Charset encoding, String password) throws MsToolsException, ZipException {
        File sourceD = checkSourceDir(sourceDir);
        zipFileDir = checkZipDir(sourceDir, zipFileDir);
        File zipFile = createCompression(sourceD, zipFileDir, fileName, ".zip");

        ZipFile zip;
        if (Strings.isBlank(password)) {
            zip = new ZipFile(zipFile);
            if (volumeSize == null || volumeSize == VolumeSizeEnum.VOLUME_SIZE_0) {
                if (sourceD.isFile()) {
                    zip.addFile(sourceD);
                } else {
                    zip.addFolder(sourceD);
                }
            } else {
                if (sourceD.isFile()) {
                    zip.createSplitZipFile(Collections.singletonList(sourceD), new ZipParameters(), true, volumeSize.getSize());
                } else {
                    zip.createSplitZipFileFromFolder(sourceD, new ZipParameters(), true, volumeSize.getSize());
                }
            }
        } else {
            zip = new ZipFile(zipFile, password.toCharArray());
            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setEncryptFiles(true);
            zipParameters.setEncryptionMethod(EncryptionMethod.AES);
            zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
            if (volumeSize == null || volumeSize == VolumeSizeEnum.VOLUME_SIZE_0) {
                if (sourceD.isFile()) {
                    zip.addFile(sourceD, zipParameters);
                } else {
                    zip.addFolder(sourceD, zipParameters);
                }
            } else {
                if (sourceD.isFile()) {
                    zip.createSplitZipFile(Collections.singletonList(sourceD), zipParameters, true, volumeSize.getSize());
                } else {
                    zip.createSplitZipFileFromFolder(sourceD, zipParameters, true, volumeSize.getSize());
                }
            }
        }
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
        File sourceD = checkSourceDir(sourceDir);
        zipFileDir = checkZipDir(sourceDir, zipFileDir);
        File zipFile = createCompression(sourceD, zipFileDir, fileName, ".zip");

        // 压缩配置
        try {
            ZipArchiveOutputStream zipArchiveOutputStream;
            if (volumeSize == null || volumeSize == VolumeSizeEnum.VOLUME_SIZE_0) {
                zipArchiveOutputStream = new ZipArchiveOutputStream(zipFile);
            } else {
                zipArchiveOutputStream = new ZipArchiveOutputStream(zipFile, volumeSize.getSize());
            }
            // 设置编码
            if (encoding == null) {
                encoding = StandardCharsets.UTF_8;
            }
            zipArchiveOutputStream.setEncoding(encoding.name());
            // 使用语言编码
            zipArchiveOutputStream.setUseLanguageEncodingFlag(true);
            // 设置注释
            if (Strings.isNotBlank(comment)) {
                zipArchiveOutputStream.setComment(comment);
            }
            // 设置压缩级别
            if (level == null) {
                level = LevelEnum.NORMAL;
            }
            zipArchiveOutputStream.setLevel(level.getLevel());
            // 无法编码的字符使用UTF-8编码
            zipArchiveOutputStream.setFallbackToUTF8(true);
            // 压缩
            addFileCompression(zipArchiveOutputStream, sourceD, sourceD.getName());
            zipArchiveOutputStream.finish();
        } catch (Exception e) {
            throw new MsToolsException(e);
        }

        // 删除源文件
        if (isDelete != null && isDelete) {
            FileUtils.deleteQuietly(sourceD);
        }
    }

    private static File createCompression(File sourceDir, String zipFileDir, String fileName, String suffix) throws MsToolsException {
        if (Strings.isBlank(fileName)) {
            fileName = sourceDir.getName();
            if (sourceDir.isFile()) {
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
            }
        }
        String zipFilePath = zipFileDir + File.separator + fileName + suffix;
        File zipFile = new File(zipFilePath);
        if (zipFile.exists() && zipFile.isFile() && zipFile.length() > 0) {
            throw new MsToolsException("压缩文件已存在");
        }
        return zipFile;
    }

    private static <T extends ArchiveOutputStream> void addFileCompression(T stream, File source, String fileName) throws MsToolsException {
        if (!source.exists()) {
            throw new MsToolsException("压缩文件或目录不存在");
        }
        try {
            if (source.isDirectory()) {
                File[] files = source.listFiles();
                if (files == null || files.length == 0) {
                    return;
                }
                for (File file : files) {
                    addFileCompression(stream, file, fileName + File.separator + file.getName());
                }
            } else {
                stream.putArchiveEntry(new ZipArchiveEntry(source, fileName));
                stream.write(FileUtils.readFileToByteArray(source));
                stream.closeArchiveEntry();
            }
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }

    private static String checkZipDir(String source, String zip) throws MsToolsException {
        if (Strings.isBlank(zip)) {
            File sourceFile = new File(source);
            zip = sourceFile.getParent();
        }
        File file = new File(zip);
        file.mkdirs();
        if (!file.canWrite()) {
            throw new MsToolsException("压缩文件保存目录不可写");
        }
        return zip;
    }

    private static File checkSourceDir(String source) throws MsToolsException {
        if (Strings.isBlank(source)) {
            throw new MsToolsException("压缩文件或目录路径不能为空");
        }
        File file = new File(source);
        if (!file.exists()) {
            throw new MsToolsException("压缩文件或目录不存在");
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return new File(source);
            }
            for (File f : files) {
                checkSourceDir(f.getAbsolutePath());
            }
        } else {
            if (!file.canRead()) {
                throw new MsToolsException("压缩文件不可读");
            }
        }
        return new File(source);
    }

}
