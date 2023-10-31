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

import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class ArchiveUtils {
    /**
     * 创建压缩
     * Tar
     *
     * @param sourcePath      源文件路径
     * @param destinationPath 目标文件路径
     * @throws IOException IO异常
     */
    public static void createTarArchive(String sourcePath, String destinationPath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(destinationPath);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ArchiveOutputStream archiveOutputStream = new TarArchiveOutputStream(bufferedOutputStream);

        File sourceFile = new File(sourcePath);
        String baseName = sourceFile.getName();

        addArchiveEntryToTar(sourceFile, archiveOutputStream, baseName);

        archiveOutputStream.finish();
        bufferedOutputStream.close();
        fileOutputStream.close();
    }

    private static void addArchiveEntryToTar(File sourceFile, ArchiveOutputStream archiveOutputStream, String baseName) throws IOException {
        if (sourceFile.isFile()) {
            TarArchiveEntry entry = new TarArchiveEntry(sourceFile, baseName);
            archiveOutputStream.putArchiveEntry(entry);
            IOUtils.copy(Files.newInputStream(sourceFile.toPath()), archiveOutputStream);
            archiveOutputStream.closeArchiveEntry();
        } else {
            File[] files = sourceFile.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    addArchiveEntryToTar(file, archiveOutputStream, baseName + "/" + file.getName());
                }
            }
        }
    }

    /**
     * 创建压缩
     * Zip
     *
     * @param sourcePath      源文件路径
     * @param destinationPath 目标文件路径
     * @throws IOException IO异常
     */
    public static void createZipArchive(String sourcePath, String destinationPath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(destinationPath);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ArchiveOutputStream archiveOutputStream = new ZipArchiveOutputStream(bufferedOutputStream);

        File sourceFile = new File(sourcePath);
        String baseName = sourceFile.getName();

        addArchiveEntryToZip(sourceFile, archiveOutputStream, baseName);

        archiveOutputStream.finish();
        bufferedOutputStream.close();
        fileOutputStream.close();
    }

    private static void addArchiveEntryToZip(File sourceFile, ArchiveOutputStream archiveOutputStream, String baseName) throws IOException {
        if (sourceFile.isFile()) {
            ZipArchiveEntry entry = new ZipArchiveEntry(sourceFile, baseName);
            archiveOutputStream.putArchiveEntry(entry);
            IOUtils.copy(Files.newInputStream(sourceFile.toPath()), archiveOutputStream);
            archiveOutputStream.closeArchiveEntry();
        } else {
            File[] files = sourceFile.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    addArchiveEntryToZip(file, archiveOutputStream, baseName + "/" + file.getName());
                }
            }
        }
    }


}
