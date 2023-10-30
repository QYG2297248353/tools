package com.cloud.ms.component.qiniucloud.utils;

import org.apache.commons.codec.binary.Hex;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ms
 */
public class UploadFileUtils {

    /**
     * 获取文件的sha256
     *
     * @param file 文件
     * @return sha256
     * @throws IOException              io异常
     * @throws NoSuchAlgorithmException 算法异常
     */
    public static String getSha256(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = bis.read(buffer)) != -1) {
            digest.update(buffer, 0, bytesRead);
        }
        byte[] hash = digest.digest();
        return Hex.encodeHexString(hash);
    }
}
