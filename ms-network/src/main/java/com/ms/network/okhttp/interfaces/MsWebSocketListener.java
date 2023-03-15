package com.ms.network.okhttp.interfaces;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * WebSocket监听器
 *
 * @author ms2297248353
 */
public abstract class MsWebSocketListener extends WebSocketListener {
    /**
     * 连接成功
     *
     * @param webSocket WebSocket
     */
    public abstract void onOpen(okhttp3.WebSocket webSocket);

    /**
     * 连接关闭
     *
     * @param webSocket WebSocket
     * @param code      状态码
     * @param reason    原因
     */
    public abstract void onClose(okhttp3.WebSocket webSocket, int code, String reason);

    /**
     * 连接失败
     *
     * @param webSocket WebSocket
     * @param t         异常
     */
    public abstract void onFailure(okhttp3.WebSocket webSocket, Throwable t);

    /**
     * 接收到消息
     *
     * @param webSocket WebSocket
     * @param text      消息
     */
    @Override
    public abstract void onMessage(okhttp3.WebSocket webSocket, String text);

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        onOpen(webSocket);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
        onMessage(webSocket, bytes.utf8());
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        onClose(webSocket, code, reason);
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        onClose(webSocket, code, reason);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        onFailure(webSocket, t);
    }
}
