package com.ms.resources.stream;

import com.ms.core.exception.base.MsToolsException;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * 常见流工具类
 *
 * @author qyg2297248353
 */
public class StreamReaderUtils {

    public static FileReader reader(String file) throws MsToolsException {
        try {
            return new FileReader(file);
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }


    /**
     * 读取文件
     *
     * @param file 文件
     * @return InputStreamReader
     * @throws MsToolsException MsToolsException
     */
    public static InputStreamReader reader(File file) throws MsToolsException {
        try {
            return new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 读取文件
     *
     * @param file    文件
     * @param charset 字符集
     * @return InputStreamReader
     * @throws MsToolsException MsToolsException
     */
    public static InputStreamReader reader(File file, String charset) throws MsToolsException {
        try {
            return new InputStreamReader(Files.newInputStream(file.toPath()), charset);
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }


    /**
     * 读取文件
     *
     * @param file    文件
     * @param charset 字符集
     * @return InputStreamReader
     * @throws MsToolsException MsToolsException
     */
    public static InputStreamReader reader(File file, Charset charset) throws MsToolsException {
        try {
            return new InputStreamReader(Files.newInputStream(file.toPath()), charset);
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }
}
