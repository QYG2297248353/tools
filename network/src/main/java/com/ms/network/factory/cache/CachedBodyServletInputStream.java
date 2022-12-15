/*
 * @MS 2022-12-13
 *  Copyright (c) 2001-2023 萌森
 *  保留所有权利
 *  本软件为萌森工作室所有及包含机密信息，须遵守其相关许可证条款进行使用。
 *  Copyright (c) 2001-2023 Meng Sen
 *  All rights reserved
 * This software is owned by Mengsen Studio and contains confidential information, and must be used in accordance with its relevant license terms.
 * Website：https://qyg2297248353.top
 */

package com.ms.network.factory.cache;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * @author ms2297248353
 */
public class CachedBodyServletInputStream extends ServletInputStream {

    private static final Logger LOGGER = Logger.getLogger(CachedBodyServletInputStream.class.getName());

    private final InputStream cachedBodyInputStream;

    public CachedBodyServletInputStream(byte[] cachedBody) {
        this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
    }

    @Override
    public int read() throws IOException {
        return this.cachedBodyInputStream.read();
    }

    @Override
    public boolean isFinished() {
        try {
            return this.cachedBodyInputStream.available() == 0;
        } catch (IOException e) {
            LOGGER.warning("CachedBodyServletInputStream isFinished err: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }


}
