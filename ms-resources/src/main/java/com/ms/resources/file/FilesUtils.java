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

package com.ms.resources.file;

import com.ms.core.exception.base.MsToolsException;
import com.ms.id.ID;
import com.ms.resources.core.FileFactory;
import com.ms.resources.core.ResourcesFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ms2297248353
 */
public class FilesUtils {
    /**
     * 获取文件
     *
     * @param path 文件路径
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static File getFile(String path) throws MsToolsException {
        return FileFactory.getFile(path);
    }

    /**
     * 获取文件夹下所有文件
     *
     * @param directoryPath 文件夹路径
     * @return 文件列表
     * @throws MsToolsException 异常
     */
    public static List<File> getFiles(String directoryPath) throws MsToolsException {
        return FileFactory.getFiles(directoryPath);
    }

    /**
     * 获取资源文件
     * classpath
     *
     * @param path 文件路径
     * @return 文件
     */
    public static InputStream getResourcesFile(String path) {
        return new ResourcesFactory().getResourcesFile(path);
    }

    /**
     * 创建文件
     *
     * @param path 文件路径 父路径不存在时自动创建
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static File createFile(String path) throws MsToolsException {
        return FileFactory.createFile(path);
    }

    /**
     * 创建目录
     *
     * @param path 目录路径
     * @return 目录
     * @throws MsToolsException 异常
     */
    public static File createDirectory(String path) throws MsToolsException {
        return FileFactory.createDirectory(path);
    }

    /**
     * 写入临时文件
     *
     * @param fileStream 文件流
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static File writeToTempFile(InputStream fileStream) throws MsToolsException {
        return FileFactory.writeToTempFile(fileStream, null);
    }

    /**
     * 写入临时文件
     *
     * @param fileBytes 文件字节
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static File writeToTempFile(byte[] fileBytes) throws MsToolsException {
        return FileFactory.writeToTempFile(fileBytes, null);
    }

    /**
     * 写入临时文件
     *
     * @param fileStream 文件流
     * @param suffix     后缀
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static File writeToTempFile(InputStream fileStream, String suffix) throws MsToolsException {
        return FileFactory.writeToTempFile(fileStream, suffix);
    }

    /**
     * 写入文件
     *
     * @param path    文件路径
     * @param content 内容
     * @throws MsToolsException 异常
     */
    public static void writeToFile(String path, String content) throws MsToolsException {
        FileFactory.writeToFile(path, content);
    }

    /**
     * 写入文件
     *
     * @param file    文件
     * @param content 内容
     * @throws MsToolsException 异常
     */
    public static void writeToFile(File file, String content) throws MsToolsException {
        FileFactory.writeToFile(file, content);
    }

    /**
     * 写入文件
     *
     * @param path    文件路径
     * @param content 内容
     * @param charset 编码
     * @throws MsToolsException 异常
     */
    public static void writeToFile(String path, String content, String charset) throws MsToolsException {
        writeToFile(path, content, Charset.forName(charset));
    }

    /**
     * 写入文件
     *
     * @param path    文件路径
     * @param content 内容
     * @param charset 编码
     * @throws MsToolsException 异常
     */
    public static void writeToFile(String path, String content, Charset charset) throws MsToolsException {
        FileFactory.writeToFile(path, content, charset);
    }

    /**
     * 写入文件
     *
     * @param file    文件
     * @param content 内容
     * @param charset 编码
     * @throws MsToolsException 异常
     */
    public static void writeToFile(File file, String content, String charset) throws MsToolsException {
        writeToFile(file, content, Charset.forName(charset));
    }

    /**
     * 写入文件
     *
     * @param file    文件
     * @param content 内容
     * @param charset 编码
     * @throws MsToolsException 异常
     */
    public static void writeToFile(File file, String content, Charset charset) throws MsToolsException {
        FileFactory.writeToFile(file, content, charset);
    }

    /**
     * 写入文件
     *
     * @param path    文件路径
     * @param content 内容
     * @param append  是否追加
     * @throws MsToolsException 异常
     */
    public static void writeToFile(String path, String content, boolean append) throws MsToolsException {
        FileFactory.writeToFile(path, content, append);
    }

    /**
     * 写入文件
     *
     * @param path    文件路径
     * @param content 内容
     * @param append  是否追加
     * @param charset 编码
     * @throws MsToolsException 异常
     */
    public static void writeToFile(String path, String content, boolean append, String charset) throws MsToolsException {
        writeToFile(path, content, append, Charset.forName(charset));
    }

    /**
     * 写入文件
     *
     * @param path    文件路径
     * @param content 内容
     * @param append  是否追加
     * @param charset 编码
     * @throws MsToolsException 异常
     */
    public static void writeToFile(String path, String content, boolean append, Charset charset) throws MsToolsException {
        FileFactory.writeToFile(path, content, charset, append);
    }

    /**
     * 写入文件
     *
     * @param file    文件
     * @param content 内容
     * @param append  是否追加
     * @param charset 编码
     * @throws MsToolsException 异常
     */
    public static void writeToFile(File file, String content, boolean append, String charset) throws MsToolsException {
        writeToFile(file, content, append, Charset.forName(charset));
    }

    /**
     * 写入文件
     *
     * @param file    文件
     * @param content 内容
     * @param append  是否追加
     * @param charset 编码
     * @throws MsToolsException 异常
     */
    public static void writeToFile(File file, String content, boolean append, Charset charset) throws MsToolsException {
        FileFactory.writeToFile(file, content, charset, append);
    }


    /**
     * 写入文件
     *
     * @param path    文件路径
     * @param content 内容
     * @throws MsToolsException 异常
     */
    public static void writeToFile(String path, byte[] content) throws MsToolsException {
        FileFactory.writeToFile(path, content);
    }

    /**
     * 写入文件
     *
     * @param path    文件路径
     * @param content 内容
     * @param append  是否追加
     * @throws MsToolsException 异常
     */
    public static void writeToFile(String path, byte[] content, boolean append) throws MsToolsException {
        FileFactory.writeToFile(path, content, append);
    }

    /**
     * 写入文件
     *
     * @param file    文件
     * @param content 内容
     * @throws MsToolsException 异常
     */
    public static void writeToFile(File file, byte[] content) throws MsToolsException {
        FileFactory.writeToFile(file, content);
    }

    /**
     * 写入文件
     *
     * @param file    文件
     * @param content 内容
     * @param append  是否追加
     * @throws MsToolsException 异常
     */
    public static void writeToFile(File file, byte[] content, boolean append) throws MsToolsException {
        FileFactory.writeToFile(file, content, append);
    }

    /**
     * 写入文件
     *
     * @param path    文件路径
     * @param content 内容
     * @throws MsToolsException 异常
     */
    public static void writeToFile(String path, InputStream content) throws MsToolsException {
        FileFactory.writeToFile(path, content);
    }

    /**
     * 写入文件
     *
     * @param file    文件
     * @param content 内容
     * @throws MsToolsException 异常
     */
    public static void writeToFile(File file, InputStream content) throws MsToolsException {
        FileFactory.writeToFile(file, content);
    }


    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return 是否成功
     * @throws MsToolsException 异常
     */
    public static boolean deleteFile(String filePath) throws MsToolsException {
        return FileFactory.deleteFile(filePath);
    }

    /**
     * 删除文件夹
     *
     * @param directoryPath 文件夹路径
     * @return 是否成功
     */
    public static boolean deleteDirectory(String directoryPath) {
        return FileFactory.deleteDirectory(directoryPath);
    }

    /**
     * 删除文件夹
     *
     * @param directoryPath 文件夹路径
     * @param deleteSelf    是否删除自己
     * @return 是否成功
     */
    public static boolean deleteDirectory(String directoryPath, boolean deleteSelf) {
        return FileFactory.deleteDirectory(directoryPath, deleteSelf);
    }

    /**
     * 文件读取
     *
     * @param path 文件路径
     * @return Reader
     * @throws MsToolsException 读取异常
     */
    public static BufferedReader getBufferedReader(String path) throws MsToolsException {
        return FileFactory.readFileBuffered(path, StandardCharsets.UTF_8);
    }

    /**
     * 获取Reade文件读取
     *
     * @param path    文件路径
     * @param charset 编码
     * @return Reader
     * @throws MsToolsException 读取异常
     */
    public static BufferedReader getBufferedReader(String path, Charset charset) throws MsToolsException {
        return FileFactory.readFileBuffered(path, charset);
    }

    /**
     * 文件读取
     *
     * @param path 文件路径
     * @return Reader
     * @throws MsToolsException 读取异常
     */
    public static InputStreamReader getStreamReader(String path) throws MsToolsException {
        return FileFactory.readFileInputStream(path, StandardCharsets.UTF_8);
    }

    /**
     * 获取Reade文件读取
     *
     * @param path    文件路径
     * @param charset 编码
     * @return Reader
     * @throws MsToolsException 读取异常
     */
    public static InputStreamReader getStreamReader(String path, Charset charset) throws MsToolsException {
        return FileFactory.readFileInputStream(path, charset);
    }

    /**
     * 文件读取
     *
     * @param path 文件路径
     * @return 文本内容
     * @throws MsToolsException 读取异常
     */
    public static String readToString(String path) throws MsToolsException {
        try (BufferedReader bufferedReader = FileFactory.readFileBuffered(path, StandardCharsets.UTF_8)) {
            return bufferedReader.toString();
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 创建临时文件
     *
     * @return 临时文件
     * @throws MsToolsException 文件
     */
    public static File createTempFile() throws MsToolsException {
        return createTempFile(ID.uuid(), null);
    }

    /**
     * 创建临时文件
     *
     * @param prefix 前缀
     * @return 临时文件
     * @throws MsToolsException 文件
     */
    public static File createTempFile(String prefix) throws MsToolsException {
        return createTempFile(prefix, null);
    }

    /**
     * 创建临时文件
     *
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 临时文件
     * @throws MsToolsException 文件
     */
    public static File createTempFile(String prefix, String suffix) throws MsToolsException {
        return FileFactory.createTempFile(prefix, suffix);
    }

    /**
     * 创建临时文件
     * 关闭后删除
     *
     * @param prefix 前缀
     * @return 临时文件
     * @throws MsToolsException 文件
     */
    public static File createTempFileOnExit(String prefix) throws MsToolsException {
        return FileFactory.createTempFile(prefix, null, null, true);
    }

    /**
     * 创建临时文件
     * 关闭后删除
     *
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 临时文件
     * @throws MsToolsException 文件
     */
    public static File createTempFileOnExit(String prefix, String suffix) throws MsToolsException {
        return FileFactory.createTempFile(prefix, suffix, null, true);
    }

    /**
     * 创建临时文件
     *
     * @param prefix    前缀
     * @param suffix    后缀
     * @param directory 目录
     * @return 临时文件
     * @throws MsToolsException 文件
     */
    public static File createTempFile(String prefix, String suffix, File directory) throws MsToolsException {
        return FileFactory.createTempFile(prefix, suffix, directory);
    }

    /**
     * 创建临时文件
     *
     * @param prefix         文件前缀
     * @param suffix         文件后缀
     * @param directory      文件目录
     * @param isDeleteOnExit 是否退出时删除
     * @return 临时文件
     * @throws MsToolsException 异常
     */
    public static File createTempFile(String prefix, String suffix, File directory, boolean isDeleteOnExit) throws MsToolsException {
        return FileFactory.createTempFile(prefix, suffix, directory, isDeleteOnExit);
    }

    /**
     * 获取文件后缀
     *
     * @param file 文件
     * @return 后缀
     */
    public static String getSuffix(File file) {
        return FileFactory.getSuffix(file);
    }

    /**
     * 获取系统临时目录
     *
     * @return 系统临时目录
     */
    public static String getSystemTempDirectory() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 判断文件是否存在
     *
     * @param path 文件路径
     * @return 是否存在
     */
    public static boolean exists(String path) {
        return FileFactory.exists(path);
    }

    /**
     * 删除文件
     *
     * @param path 文件路径
     * @return
     */
    public static boolean delete(String path) {
        return FileFactory.delete(path);
    }
}