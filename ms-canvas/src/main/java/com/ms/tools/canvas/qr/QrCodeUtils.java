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

package com.ms.tools.canvas.qr;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.ms.tools.canvas.qr.factory.QrCodeFactory;
import com.ms.tools.core.base.basic.Strings;
import com.ms.tools.core.exception.base.MsToolsException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qyg2297248353
 */
public class QrCodeUtils {

    /**
     * 绘制二维码
     *
     * @param imgFile 二维码生成文件
     * @param format  文件格式 png, jpg
     * @param content 二维码内容
     * @param width   宽度 单位px
     * @param height  高度 单位px
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static File drawQrCodeLogo(File imgFile, String format, String content, int width, int height) throws MsToolsException {
        return QrCodeFactory.drawImageFile(imgFile, format, content, width, height, null);
    }

    /**
     * 绘制二维码
     *
     * @param imgFile 二维码生成文件
     * @param format  文件格式 png, jpg
     * @param content 二维码内容
     * @param width   宽度 单位px
     * @param height  高度 单位px
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static ImageOutputStream drawQrCodeLogo(ImageOutputStream imgFile, String format, String content, int width, int height) throws MsToolsException {
        return QrCodeFactory.drawImageStream(imgFile, format, content, width, height, null);
    }

    /**
     * 绘制二维码
     *
     * @param imgFile 二维码生成文件
     * @param format  文件格式 png, jpg
     * @param content 二维码内容
     * @param width   宽度 单位px
     * @param height  高度 单位px
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static OutputStream drawQrCodeLogo(OutputStream imgFile, String format, String content, int width, int height) throws MsToolsException {
        return QrCodeFactory.drawImageStream(imgFile, format, content, width, height, null);
    }

    /**
     * 绘制二维码
     *
     * @param imgFile 二维码生成文件
     * @param format  文件格式 png, jpg
     * @param content 二维码内容
     * @param width   宽度 单位px
     * @param height  高度 单位px
     * @param logo    中心logo文件 null 不使用logo
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static ImageOutputStream drawQrCodeLogo(ImageOutputStream imgFile, String format, String content, int width, int height, InputStream logo) throws MsToolsException {
        BufferedImage read = null;
        try {
            read = ImageIO.read(logo);
            logo.close();
        } catch (IOException ignored) {
        }
        return QrCodeFactory.drawImageStream(imgFile, format, content, width, height, read);
    }

    /**
     * 绘制二维码
     *
     * @param imgFile 二维码生成文件
     * @param format  文件格式 png, jpg
     * @param content 二维码内容
     * @param width   宽度 单位px
     * @param height  高度 单位px
     * @param logo    中心logo文件 null 不使用logo
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static OutputStream drawQrCodeLogo(OutputStream imgFile, String format, String content, int width, int height, File logo) throws MsToolsException {
        BufferedImage read = null;
        try {
            read = ImageIO.read(logo);
        } catch (IOException ignored) {
        }
        return QrCodeFactory.drawImageStream(imgFile, format, content, width, height, read);
    }

    /**
     * 绘制二维码
     *
     * @param imgFile 二维码生成文件
     * @param format  文件格式 png, jpg
     * @param content 二维码内容
     * @param width   宽度 单位px
     * @param height  高度 单位px
     * @param logo    中心logo文件 null 不使用logo
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static File drawQrCodeLogo(File imgFile, String format, String content, int width, int height, InputStream logo) throws MsToolsException {
        BufferedImage read = null;
        try {
            read = ImageIO.read(logo);
            logo.close();
        } catch (IOException ignored) {
        }
        return QrCodeFactory.drawImageFile(imgFile, format, content, width, height, read);
    }

    /**
     * 绘制二维码
     *
     * @param imgFile 二维码生成文件
     * @param format  文件格式 png, jpg
     * @param content 二维码内容
     * @param width   宽度 单位px
     * @param height  高度 单位px
     * @param logo    中心logo文件 null 不使用logo
     * @return 文件
     * @throws MsToolsException 异常
     */
    public static File drawQrCodeLogo(File imgFile, String format, String content, int width, int height, File logo) throws MsToolsException {
        BufferedImage read = null;
        try {
            read = ImageIO.read(logo);
        } catch (IOException ignored) {
        }
        return QrCodeFactory.drawImageFile(imgFile, format, content, width, height, read);
    }

    /**
     * 解析二维码
     *
     * @param file 二维码文件
     * @return 解析结果-字符串
     * @throws MsToolsException 异常
     */
    public static String analysisQrCode(File file) throws MsToolsException {
        if (!file.exists()) {
            return Strings.EMPTY;
        }
        try {
            BufferedImage imge = ImageIO.read(file);
            MultiFormatReader formatReader = new MultiFormatReader();
            LuminanceSource source = new BufferedImageLuminanceSource(imge);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map map = new HashMap(1);
            map.put(EncodeHintType.CHARACTER_SET, Strings.UTF_8);
            Result result = formatReader.decode(binaryBitmap, map);
            return result.toString();
        } catch (IOException | NotFoundException e) {
            throw new MsToolsException(e);
        }
    }
}
