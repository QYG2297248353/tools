package com.ms.resources.file.factory;

import com.ms.core.base.basic.Strings;
import com.ms.core.exception.base.MsToolsException;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.*;

public class DecompressionFactory {

    /**
     * 解压
     *
     * @param sourceFile 待解压文件
     * @param targetDir  解压目录
     * @param password   密码
     * @throws MsToolsException 异常
     */
    public static void decompression(File sourceFile, File targetDir, String password) throws MsToolsException {
        checkSourceFile(sourceFile);
        checkTargetDir(targetDir);
        try {
            if (Strings.isBlank(password)) {
                new ZipFile(sourceFile).extractAll(targetDir.getAbsolutePath());
            } else {
                new ZipFile(sourceFile, password.toCharArray()).extractAll(targetDir.getAbsolutePath());
            }
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }


    /**
     * 解压
     *
     * @param sourceFile 待解压文件
     * @param targetDir  解压目录
     * @throws MsToolsException 异常
     */
    public static void decompression(File sourceFile, File targetDir) throws MsToolsException {
        checkSourceFile(sourceFile);
        checkTargetDir(targetDir);
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            CompressorInputStream compressorInputStream = new CompressorStreamFactory().createCompressorInputStream(bufferedInputStream);
            ArchiveInputStream archiveInputStream = new ArchiveStreamFactory().createArchiveInputStream(compressorInputStream);

            ArchiveEntry archiveEntry;
            while ((archiveEntry = archiveInputStream.getNextEntry()) != null) {
                File entryFile = new File(targetDir, archiveEntry.getName());
                if (archiveEntry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    byte[] buffer = new byte[1024];
                    FileOutputStream fileOutputStream = new FileOutputStream(entryFile);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    int count;
                    while ((count = archiveInputStream.read(buffer)) != -1) {
                        bufferedOutputStream.write(buffer, 0, count);
                    }
                    bufferedOutputStream.close();
                }
            }
            archiveInputStream.close();
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }

    private static void checkTargetDir(File targetDir) throws MsToolsException {
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        if (!targetDir.isDirectory()) {
            throw new MsToolsException("解压目录不是目录");
        }
        if (!targetDir.canWrite()) {
            throw new MsToolsException("解压目录不可写");
        }
    }

    private static void checkSourceFile(File sourceFile) throws MsToolsException {
        if (!sourceFile.exists()) {
            throw new MsToolsException("待解压文件不存在");
        }
        if (!sourceFile.isFile()) {
            throw new MsToolsException("待解压文件不是文件");
        }
        if (!sourceFile.canRead()) {
            throw new MsToolsException("待解压文件不可读");
        }
    }


}
