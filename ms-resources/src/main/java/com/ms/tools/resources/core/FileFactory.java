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

package com.ms.tools.resources.core;

import com.ms.tools.core.exception.base.MsToolsException;
import com.ms.tools.core.id.ID;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qyg2297248353
 */
public class FileFactory {

    public static File getFile(String filePath) throws MsToolsException {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return file;
        } else {
            InputStream inputStream = new ResourcesFactory().getResourcesFile(filePath);
            if (inputStream != null) {
                try {
                    File temp = File.createTempFile("temp", "temp");
                    copyInputStreamToFile(inputStream, temp);
                    return temp;
                } catch (IOException e) {
                    throw new MsToolsException(e);
                }
            }
            throw new MsToolsException("文件不存在");
        }
    }

    /**
     * 复制输入流到文件
     *
     * @param inputStream 输入流
     * @param file        文件
     */
    public static void copyInputStreamToFile(InputStream inputStream, File file) {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<File> getFiles(String directoryPath) throws MsToolsException {
        File file = new File(directoryPath);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(files));
        }
        throw new MsToolsException("文件夹不存在");
    }

    public static File createFile(String filePath) throws MsToolsException {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return file;
        }
        try {
            if (!file.getParentFile().exists()) {
                boolean mkdir = file.getParentFile().mkdirs();
                if (!mkdir) {
                    throw new MsToolsException("父目录不存在，创建失败");
                }
            }
            if (file.createNewFile()) {
                return file;
            }
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
        throw new MsToolsException("创建文件失败");
    }

    public static File createFile(String directoryPath, String filename) throws MsToolsException {
        String filePath = directoryPath + File.separator + filename;
        return createFile(filePath);
    }

    public static File createDirectory(String directoryPath) throws MsToolsException {
        File file = new File(directoryPath);
        if (file.exists() && file.isDirectory()) {
            return file;
        }
        if (file.mkdirs()) {
            return file;
        }
        throw new MsToolsException("创建目录失败");
    }

    public static Path getPath(String filePath) {
        return Paths.get(filePath);
    }

    public static Path createFilePath(String directoryPath, String filename) throws IOException {
        Path path = getPath(directoryPath);
        Files.createDirectories(path);
        String filePath = directoryPath + File.separator + filename;
        Path file = getPath(filePath);
        Files.createFile(file);
        return file;
    }

    public static Path createDirectoryPath(String directoryPath) throws IOException {
        Path path = getPath(directoryPath);
        Files.createDirectories(path);
        return path;
    }

    public static Path createPath(String filePath) throws IOException {
        Path path = getPath(filePath);
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.createFile(path);
        return path;
    }

    /**
     * 获取文件的文件头信息
     *
     * @param inputStream 输入流
     * @return 文件头信息
     * @throws IOException 异常
     */
    private static String getFileHeader(InputStream inputStream) throws IOException {
        byte[] content = new byte[28];
        inputStream.read(content, 0, content.length);
        return bytesToHexString(content);
    }

    /**
     * 文件头字节数组转为十六进制编码
     *
     * @param content 文件头字节数据
     * @return 十六进制编码
     */
    private static String bytesToHexString(byte[] content) {
        StringBuilder builder = new StringBuilder();
        if (content == null || content.length <= 0) {
            return null;
        }
        String temp;
        for (byte b : content) {
            temp = Integer.toHexString(b & 0xFF).toUpperCase();
            if (temp.length() < 2) {
                builder.append(0);
            }
            builder.append(temp);
        }
        return builder.toString();
    }

    public static File writeToTempFile(InputStream fileStream, String suffix) throws MsToolsException {
        File tempFile = createTempFile(ID.uuid(), suffix);
        copyInputStreamToFile(fileStream, tempFile);
        return tempFile;
    }

    public static File writeToTempFile(byte[] fileBytes, String suffix) throws MsToolsException {
        File tempFile = createTempFile(ID.uuid(), suffix);
        try (FileOutputStream fileOutputStream = new FileOutputStream(tempFile)) {
            fileOutputStream.write(fileBytes);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
        return tempFile;
    }

    public static void writeToFile(String filePath, String content) throws MsToolsException {
        writeToFile(filePath, content, StandardCharsets.UTF_8);
    }

    public static void writeToFile(File file, String content) throws MsToolsException {
        writeToFile(file, content, StandardCharsets.UTF_8);
    }

    public static void writeToFile(String filePath, String content, String charset) throws MsToolsException {
        writeToFile(filePath, content, Charset.forName(charset));
    }

    public static void writeToFile(File file, String content, String charset) throws MsToolsException {
        writeToFile(file, content, Charset.forName(charset));
    }

    public static void writeToFile(String filePath, String content, Charset charset) throws MsToolsException {
        writeToFile(filePath, content, charset, false);
    }

    public static void writeToFile(File file, String content, Charset charset) throws MsToolsException {
        writeToFile(file, content, charset, false);
    }

    public static void writeToFile(String filePath, String content, boolean append) throws MsToolsException {
        writeToFile(filePath, content, StandardCharsets.UTF_8, append);
    }

    public static void writeToFile(File file, String content, boolean append) throws MsToolsException {
        writeToFile(file, content, StandardCharsets.UTF_8, append);
    }

    public static void writeToFile(String filePath, String content, String charset, boolean append) throws MsToolsException {
        writeToFile(filePath, content, Charset.forName(charset), append);
    }

    public static void writeToFile(String filePath, String content, Charset charset, boolean append) throws MsToolsException {
        File file = createFile(filePath);
        writeToFile(file, content, charset, append);
    }

    public static void writeToFile(File file, String content, Charset charset, boolean append) throws MsToolsException {
        writeStringToFile(file, content, charset, append);
    }

    /**
     * 写入字符串到文件
     *
     * @param file    文件
     * @param content 内容
     * @param charset 编码
     * @param append  是否追加
     * @throws MsToolsException 异常
     */
    public static void writeStringToFile(File file, String content, Charset charset, boolean append) throws MsToolsException {
        try (FileOutputStream outputStream = new FileOutputStream(file, append)) {
            outputStream.write(content.getBytes(charset));
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }


    public static void writeToFile(String filePath, byte[] content) throws MsToolsException {
        writeToFile(filePath, content, false);
    }

    public static void writeToFile(File file, byte[] content) throws MsToolsException {
        writeToFile(file, content, false);
    }

    public static void writeToFile(String filePath, byte[] content, boolean append) throws MsToolsException {
        File file = createFile(filePath);
        writeToFile(file, content, append);
    }

    public static void writeToFile(File file, byte[] content, boolean append) throws MsToolsException {
        writeByteArrayToFile(file, content, append);
    }

    /**
     * 写入字节数组到文件
     *
     * @param file    文件
     * @param content 字节数组
     * @param append  是否追加
     * @throws MsToolsException 异常
     */
    public static void writeByteArrayToFile(File file, byte[] content, boolean append) throws MsToolsException {
        try (FileOutputStream outputStream = new FileOutputStream(file, append)) {
            outputStream.write(content);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    public static void writeToFile(String filePath, InputStream inputStream) throws MsToolsException {
        File file = createFile(filePath);
        writeToFile(file, inputStream);
    }

    public static void writeToFile(File file, InputStream inputStream) {
        copyInputStreamToFile(inputStream, file);
    }


    public static boolean deleteFile(String filePath) throws MsToolsException {
        File file = getFile(filePath);
        return file.delete();
    }

    public static boolean deleteDirectory(String directoryPath) {
        File file = new File(directoryPath);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return file.delete();
            }
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDirectory(f.getAbsolutePath());
                } else {
                    f.delete();
                }
            }
        }
        return file.delete();
    }

    public static boolean deleteDirectory(String directoryPath, boolean deleteSelf) {
        File file = new File(directoryPath);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                if (deleteSelf) {
                    return file.delete();
                }
                return true;
            }
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDirectory(f.getAbsolutePath());
                } else {
                    f.delete();
                }
            }
        }
        if (deleteSelf) {
            return file.delete();
        }
        return true;
    }

    public static boolean deleteFileOrDirectory(String filePath) throws MsToolsException {
        File file = getFile(filePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                return deleteDirectory(filePath);
            } else {
                return deleteFile(filePath);
            }
        }
        return false;
    }

    public static BufferedReader readFileBuffered(String path, Charset charset) throws MsToolsException {
        File file = getFile(path);
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        } catch (FileNotFoundException e) {
            throw new MsToolsException(e);
        }
    }

    public static InputStreamReader readFileInputStream(String path, Charset charset) throws MsToolsException {
        File file = getFile(path);
        try {
            return new InputStreamReader(new FileInputStream(file), charset);
        } catch (FileNotFoundException e) {
            throw new MsToolsException(e);
        }
    }

    public static File createTempFile(String prefix, String suffix) throws MsToolsException {
        return createTempFile(prefix, suffix, null);
    }

    public static File createTempFile(String prefix, String suffix, File directory) throws MsToolsException {
        try {
            return File.createTempFile(prefix, suffix, directory);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    public static File createTempFile(String prefix, String suffix, File directory, boolean isDeleteOnExit) throws MsToolsException {
        try {
            File file = File.createTempFile(prefix, suffix, directory);
            if (isDeleteOnExit) {
                file.deleteOnExit();
            }
            return file;
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    public static String getSuffix(File file) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return fileName.substring(index + 1);
    }


    public static boolean exists(String path) {
        try {
            return getFile(path).exists();
        } catch (MsToolsException e) {
            return false;
        }
    }

    public static boolean delete(String path) {
        try {
            return getFile(path).delete();
        } catch (MsToolsException e) {
            return false;
        }
    }
}
