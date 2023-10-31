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

package com.ms.tools.canvas.qr.factory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ms.tools.core.base.basic.Strings;
import com.ms.tools.core.exception.base.MsToolsException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * @author qyg2297248353
 */
public class QrCodeFactory {
    private static final Integer DEFAULT_WIDTH = 200;
    private static final Integer DEFAULT_HEIGHT = 200;

    public static File drawImageFile(File imageFile, String format, String content, Integer width, Integer height, BufferedImage logo) throws MsToolsException {
        try {
            // 绘画
            BufferedImage bufferedImage = drawQrCode(content, width, height, logo);
            // 写文件
            ImageIO.write(bufferedImage, format, imageFile);
        } catch (WriterException | IOException e) {
            throw new MsToolsException(e);
        }
        return imageFile;
    }

    public static ImageOutputStream drawImageStream(ImageOutputStream imageStream, String format, String content, int width, int height, BufferedImage logo) throws MsToolsException {
        try {
            // 绘画
            BufferedImage bufferedImage = drawQrCode(content, width, height, logo);
            // 写文件
            ImageIO.write(bufferedImage, format, imageStream);
        } catch (WriterException | IOException e) {
            throw new MsToolsException(e);
        }
        return imageStream;
    }

    public static OutputStream drawImageStream(OutputStream imageStream, String format, String content, int width, int height, BufferedImage logo) throws MsToolsException {
        try {
            // 绘画
            BufferedImage bufferedImage = drawQrCode(content, width, height, logo);
            // 写文件
            ImageIO.write(bufferedImage, format, imageStream);
        } catch (WriterException | IOException e) {
            throw new MsToolsException(e);
        }
        return imageStream;
    }

    /**
     * Creates a new QrCode
     *
     * @param content 二维码内容
     * @param width   width
     * @param height  height
     * @param logo    logo文件
     * @return 绘画内容
     */
    private static BufferedImage drawQrCode(String content, int width, int height, BufferedImage logo) throws WriterException, IOException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        // 排错率  L<M<Q<H
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 编码
        hints.put(EncodeHintType.CHARACTER_SET, Strings.UTF_8);
        // 外边距：margin
        hints.put(EncodeHintType.MARGIN, 1);
        /*
         * content : 需要加密的 文字
         * BarcodeFormat.QR_CODE:要解析的类型（二维码）
         * hints：加密涉及的一些参数：编码、排错率
         */
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        // 内存中的一张图片：此时需要的图片 是二维码-> 需要一个boolean[][] ->BitMatrix
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, (bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB()));
            }
        }
        // 画logo
        if (logo != null) {
            image = QrCodeFactory.logoMatrix(image, logo);
        }
        return image;
    }

    /**
     * 绘制Logo
     *
     * @param matrixImage 二维码
     * @param logo        logo
     * @return 添加logo的二维码
     * @throws IOException 异常
     */
    private static BufferedImage logoMatrix(BufferedImage matrixImage, BufferedImage logo) {
        // 在二维码上画logo:产生一个  二维码画板
        Graphics2D g2 = matrixImage.createGraphics();

        // 画logo： String->BufferedImage(内存)
        int height = matrixImage.getHeight();
        int width = matrixImage.getWidth();
        // 纯logo图片
        g2.drawImage(logo, width * 2 / 5, height * 2 / 5, width * 1 / 5, height * 1 / 5, null);

        // 产生一个 画 白色圆角正方形的 画笔
        BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        // 将画板-画笔 关联
        g2.setStroke(stroke);
        // 创建一个正方形
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(width * 2 / 5, height * 2 / 5, width * 1 / 5, height * 1 / 5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setColor(Color.WHITE);
        g2.draw(round);

        // 灰色边框
        BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke2);
        // 创建一个正方形
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(width * 2 / 5 + 2, height * 2 / 5 + 2, width * 1 / 5 - 4, height * 1 / 5 - 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setColor(Color.GRAY);
        g2.draw(round2);

        g2.dispose();
        matrixImage.flush();

        return matrixImage;
    }
}
