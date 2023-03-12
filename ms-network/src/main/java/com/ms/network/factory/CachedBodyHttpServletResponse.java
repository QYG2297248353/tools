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

package com.ms.network.factory;

import org.apache.commons.io.output.TeeOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author ms2297248353
 */
public class CachedBodyHttpServletResponse extends HttpServletResponseWrapper {
    /**
     * 我们的分支流
     */
    private final ByteArrayOutputStream output;
    private final OutputStream bufferOutputStream;
    private ServletOutputStream filterOutput;

    public CachedBodyHttpServletResponse(HttpServletResponse response) throws IOException {
        super(response);
        bufferOutputStream = response.getOutputStream();
        output = new ByteArrayOutputStream();
    }

    /**
     * 利用TeeOutputStream复制流，解决多次读写问题
     * 用super.getOutputStream来获取源outputstream，也可以用注释的那种方式获取，传过来
     *
     * @return 输出流
     */
    @Override
    public ServletOutputStream getOutputStream() {
        if (filterOutput == null) {
            filterOutput = new ServletOutputStream() {

                private final TeeOutputStream teeOutputStream = new TeeOutputStream(bufferOutputStream, output);
                // private final TeeOutputStream teeOutputStream = new TeeOutputStream(CachedBodyHttpServletResponse.super.getOutputStream(), CachedBodyHttpServletResponse.this.output);

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener writeListener) {

                }

                @Override
                public void write(int b) throws IOException {
                    teeOutputStream.write(b);
                }
            };
        }
        return filterOutput;
    }

    public byte[] toByteArray() {
        return output.toByteArray();
    }

}
