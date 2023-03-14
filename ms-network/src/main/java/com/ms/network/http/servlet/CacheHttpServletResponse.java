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

package com.ms.network.http.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * @author ms2297248353
 */
public class CacheHttpServletResponse extends HttpServletResponseWrapper {
    private HttpServletResponse response;
    private CopyServletOutputStream copyServletOutputStream;
    private PrintWriter writer;

    public CacheHttpServletResponse(HttpServletResponse response) throws IOException {
        super(response);
        this.response = response;
        copyServletOutputStream = new CopyServletOutputStream(response.getOutputStream());
        writer = new PrintWriter(new OutputStreamWriter(copyServletOutputStream, response.getCharacterEncoding()));
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return copyServletOutputStream;
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    public void copyBodyToResponse() throws IOException {
        copyServletOutputStream.copyBodyToResponse();
    }

    @Override
    public void setStatus(int sc) {
        response.setStatus(sc);
    }

    @Override
    public void setHeader(String name, String value) {
        response.setHeader(name, value);
    }

    @Override
    public void addHeader(String name, String value) {
        response.addHeader(name, value);
    }

    @Override
    public void setContentType(String type) {
        response.setContentType(type);
    }

    @Override
    public void setContentLength(int len) {
        response.setContentLength(len);
    }

    private static class CopyServletOutputStream extends ServletOutputStream {
        private OutputStream outputStream;
        private ByteArrayOutputStream copy;

        public CopyServletOutputStream(OutputStream outputStream) {
            this.outputStream = outputStream;
            copy = new ByteArrayOutputStream();
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
            copy.write(b);
        }

        public void copyBodyToResponse() throws IOException {
            outputStream.write(copy.toByteArray());
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            throw new UnsupportedOperationException();
        }
    }
}
