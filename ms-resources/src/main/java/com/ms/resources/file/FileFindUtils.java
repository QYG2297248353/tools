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

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileFindUtils {

    /**
     * 读取文件，按照优先级顺序查找文件并返回文件内容
     *
     * @param fileName 文件名
     * @return 文件内容
     * @throws MsToolsException 如果找不到文件，如果读取文件失败，抛出该异常
     */
    public static String readFileStr(String fileName) throws MsToolsException {
        File file = new File(fileName);
        if (file.exists()) {
            return readFile(file);
        }

        file = new File("../" + fileName);
        if (file.exists()) {
            return readFile(file);
        }

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (inputStream != null) {
            return readInputStream(inputStream);
        }

        file = new File("src/main/resources/" + fileName);
        if (file.exists()) {
            return readFile(file);
        }

        inputStream = FileFindUtils.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream != null) {
            return readInputStream(inputStream);
        }

        // 找不到文件，抛出异常
        throw new MsToolsException(new FileNotFoundException("Cannot find file: " + fileName));
    }

    /**
     * 读取文件，并返回文件内容
     *
     * @param file 文件对象
     * @return 文件内容
     * @throws MsToolsException 如果读取文件失败，抛出该异常
     */
    private static String readFile(File file) throws MsToolsException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return readInputStream(inputStream);
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 读取输入流，并返回输入流内容
     *
     * @param inputStream 输入流对象
     * @return 输入流内容
     * @throws MsToolsException 如果读取输入流失败，抛出该异常
     */
    private static String readInputStream(InputStream inputStream) throws MsToolsException {
        StringBuilder builder = new StringBuilder();
        byte[] buffer = new byte[4096];
        int n = 0;
        try {
            while ((n = inputStream.read(buffer)) != -1) {
                builder.append(new String(buffer, 0, n));
            }
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
        return builder.toString();
    }


    /**
     * 读取文件，按照优先级顺序查找文件并返回文件对象
     *
     * @param fileName 文件名
     * @return 文件对象
     * @throws MsToolsException 如果找不到文件，抛出该异常
     */
    public static File readFile(String fileName) throws MsToolsException {
        File file = new File(fileName);
        if (file.exists()) {
            return file;
        }
        file = new File("../" + fileName);
        if (file.exists()) {
            return file;
        }

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (inputStream != null) {
            return getFileFromInputStream(inputStream);
        }

        file = new File("src/main/resources/" + fileName);
        if (file.exists()) {
            return file;
        }

        inputStream = FileReader.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream != null) {
            return getFileFromInputStream(inputStream);
        }

        throw new MsToolsException(new FileNotFoundException("Cannot find file: " + fileName));
    }

    /**
     * 将输入流转换为文件对象，并返回该文件对象
     *
     * @param inputStream 输入流对象
     * @return 文件对象
     * @throws MsToolsException 如果读取输入流失败，抛出该异常
     */
    private static File getFileFromInputStream(InputStream inputStream) throws MsToolsException {
        File tempFile = FilesUtils.createTempFileOnExit("temp");
        FilesUtils.writeToFile(tempFile, inputStream);
        return tempFile;
    }


    /**
     * 读取文件，按照优先级顺序查找文件并返回文件对象
     *
     * @param fileName 文件名
     * @return 文件对象
     * @throws MsToolsException 如果找不到文件，抛出该异常
     */
    public static File findFile(String fileName) throws MsToolsException {
        File file = new File(fileName);
        if (file.exists()) {
            return file;
        }

        URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource(fileName);
        if (resourceUrl != null) {
            try {
                Path path = Paths.get(resourceUrl.toURI());
                return path.toFile();
            } catch (URISyntaxException e) {
                throw new MsToolsException(e);
            }
        }

        ClassLoader callerClassLoader = getCallerClassLoader();
        resourceUrl = callerClassLoader.getResource(fileName);
        if (resourceUrl != null) {
            try {
                Path path = Paths.get(resourceUrl.toURI());
                return path.toFile();
            } catch (URISyntaxException e) {
                throw new MsToolsException(e);
            }
        }

        resourceUrl = FileFindUtils.class.getClassLoader().getResource(fileName);
        if (resourceUrl != null) {
            try {
                Path path = Paths.get(resourceUrl.toURI());
                return path.toFile();
            } catch (URISyntaxException e) {
                throw new MsToolsException(e);
            }
        }

        throw new MsToolsException(new FileNotFoundException("Cannot find file: " + fileName));
    }

    /**
     * 获取调用者所在的ClassLoader
     *
     * @return 调用者所在的ClassLoader
     */
    private static ClassLoader getCallerClassLoader() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        List<StackTraceElement> elements = Arrays.asList(stackTrace);
        return elements.stream()
                .map(StackTraceElement::getClassName)
                .filter(className -> !className.equals(FileFindUtils.class.getName()))
                .map(FileFindUtils::getClassLoaderForClassName)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取指定类的ClassLoader
     *
     * @param className 类名
     * @return 类的ClassLoader
     */
    private static ClassLoader getClassLoaderForClassName(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.getClassLoader();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}
