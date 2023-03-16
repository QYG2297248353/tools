package com.ms.network.okhttp.socket;

import okhttp3.Request;
import okhttp3.WebSocket;
import okio.ByteString;

/**
 * 基本操作
 *
 * @author ms2297248353
 */
public class MsWebSocket {

    private WebSocket webSocket;

    private MsWebSocket() {
    }

    public MsWebSocket(WebSocket webSocket) {
        this();
        this.webSocket = webSocket;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }


    /**
     * 发送文本
     *
     * @param text 文本
     */
    public void send(String text) {
        webSocket.send(text);
    }

    /**
     * 发送字节
     *
     * @param bytes 字节
     */
    public void send(byte[] bytes) {
        ByteString byteString = ByteString.of(bytes);
        webSocket.send(byteString);
    }

    /**
     * 获取消息长度
     * <p>
     * 单位：字节
     *
     * @return 长度
     */
    public long getMessageSize() {
        return webSocket.queueSize();
    }

    /**
     * 获取原始请求
     *
     * @return 请求
     */
    public Request getRequest() {
        return webSocket.request();
    }

    /**
     * 取消
     * 快速释放
     */
    public void cancel() {
        webSocket.cancel();
    }

    /**
     * 关闭连接
     *
     * @param code   状态码
     * @param reason 原因
     */
    public void close(int code, String reason) {
        webSocket.close(code, reason);
    }

    /**
     * 关闭连接
     *
     * @param code 状态码
     */
    public void close(int code) {
        webSocket.close(code, null);
    }
}
