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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 针对Zip 文件处理
 * Zip不支持中文文件名
 *
 * @author ms2297248353
 */
public class ZipJavaUtils {


    /**
     * 压缩ZIP
     *
     * @param sourceDirPath 源文件夹路径
     * @param zipFilePath   压缩文件保存路径
     * @throws IOException IO异常
     */
    public static void compressZip(String sourceDirPath, String zipFilePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFilePath);
        ZipOutputStream zos = new ZipOutputStream(fos);

        File sourceDir = new File(sourceDirPath);
        String[] fileList = sourceDir.list();

        for (String fileName : fileList) {
            ZipEntry ze = new ZipEntry(fileName);
            zos.putNextEntry(ze);

            FileInputStream fis = new FileInputStream(sourceDirPath + File.separator + fileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }

            fis.close();
            zos.closeEntry();
        }

        zos.close();
        fos.close();
    }

    /**
     * 解压ZIP
     *
     * @param zipFilePath 压缩文件路径
     * @param destDirPath 解压目标文件夹
     * @throws IOException IO异常
     */
    public static void decompressZip(String zipFilePath, String destDirPath) throws IOException {
        File destDir = new File(destDirPath);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));

        ZipEntry ze = zis.getNextEntry();
        while (ze != null) {
            String fileName = ze.getName();
            File newFile = new File(destDirPath + File.separator + fileName);

            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            fos.close();
            zis.closeEntry();
            ze = zis.getNextEntry();
        }

        zis.close();
    }

}
