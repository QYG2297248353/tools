package com.ms.resources.stream;

import com.ms.core.exception.base.MsToolsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtils {

    /**
     * 读取输入流，并返回输入流内容
     *
     * @param inputStream 输入流
     * @return 输入流内容
     */
    public static String readInputStream(InputStream inputStream) throws MsToolsException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }

    /**
     * 读取输入流，并返回输入流内容
     *
     * @param inputStream 输入流
     * @param charsetName 字符集名称
     * @return 输入流内容
     */
    public static String readInputStream(InputStream inputStream, String charsetName) throws MsToolsException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charsetName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new MsToolsException(e);
        }
    }


}
