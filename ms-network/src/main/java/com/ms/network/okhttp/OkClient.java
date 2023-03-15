package com.ms.network.okhttp;

import com.alibaba.fastjson2.JSON;
import com.ms.core.base.basic.Strings;
import com.ms.core.exception.base.MsToolsRuntimeException;
import com.ms.network.okhttp.enums.ContentTypeEnum;
import com.ms.network.okhttp.enums.RequestMethodEnum;
import com.ms.network.okhttp.factory.OkHttpFactory;
import com.ms.network.okhttp.interfaces.MsWebSocketListener;
import com.ms.network.okhttp.properties.OkHttpProperties;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * OkHttp客户端
 * <p>
 * 自动注入当前对象
 * 配置文件将会覆盖默认配置
 *
 * @author ms2297248353
 */
@Component
public class OkClient {
    @Resource
    private OkHttpProperties okHttpProperties;


    private OkClient(OkHttpProperties properties) {
        if (okHttpProperties == null && properties == null) {
            okHttpProperties = new OkHttpProperties();
        } else if (properties != null) {
            okHttpProperties = properties;
        }
    }

    /**
     * 构建对象
     *
     * @return 对象
     */
    public static Builder build() {
        return new OkClient(null).builder();
    }

    /**
     * 构建对象
     *
     * @param properties 配置
     * @return 对象
     */
    public static Builder build(OkHttpProperties properties) {
        return new OkClient(properties).builder();
    }

    /**
     * 构建对象
     *
     * @param host 代理地址
     * @param port 代理端口
     * @return 对象
     */
    public static Builder buildProxy(String host, int port) {
        return new OkClient(new OkHttpProperties().setProxy(host, port)).builder();
    }

    private Builder builder() {
        return new Builder(okHttpProperties);
    }

    static class BaseBuilder {
        protected OkHttpClient okHttpClient;
        protected String uri;
        protected Map<String, String> headers;
        protected Map<String, String> params;

        public BaseBuilder() {
            headers = new HashMap<>();
            params = new HashMap<>();
        }

        public BaseBuilder(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            this.okHttpClient = okHttpClient;
            this.uri = uri;
            this.headers = headers;
        }

        void builderUri() {
            if (Strings.isBlank(uri)) {
                throw new MsToolsRuntimeException("请求地址不能为空");
            }
            if (params.isEmpty()) {
                return;
            }
            StringBuilder sb = new StringBuilder(uri);
            if (uri.contains(Strings.QUESTION_MARK)) {
                sb.append(Strings.AND);
            } else {
                sb.append(Strings.QUESTION_MARK);
            }
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey()).append(Strings.EQUALS).append(entry.getValue()).append(Strings.AND);
            }
            sb.deleteCharAt(sb.length() - 1);
            uri = sb.toString();
        }
    }

    public static class Builder extends BaseBuilder {
        private Map<String, String> headers;
        private String uri;

        private Builder(OkHttpProperties properties) {
            okHttpClient = new OkHttpFactory().create(properties);
            headers = new HashMap<>();
        }

        /**
         * 配置全局请求头
         *
         * @param name  键值
         * @param value 值
         * @return 对象
         */
        public Builder header(String name, String value) {
            okHttpClient = okHttpClient.newBuilder().addInterceptor(chain -> {
                Request request = chain.request().newBuilder().addHeader(name, value).build();
                return chain.proceed(request);
            }).build();
            return this;
        }

        /**
         * 添加请求头
         * 针对单词请求
         *
         * @param name  键值
         * @param value 值
         * @return 对象
         */
        public Builder addHeader(String name, String value) {
            headers.put(name, value);
            return this;
        }

        /**
         * 覆盖请求头
         * 针对单词请求
         *
         * @param header 参数
         * @return 对象
         */
        public Builder header(Map<String, String> header) {
            headers = header;
            return this;
        }


        /**
         * 配置请求地址
         *
         * @param uri 请求地址
         * @return 对象
         */
        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        /**
         * 配置请求地址
         *
         * @param url 请求地址
         * @return 对象
         */
        public Builder uri(URL url) {
            uri = url.toString();
            return this;
        }

        /**
         * 配置请求地址
         *
         * @param uri 请求地址
         * @return 对象
         */
        public Builder uri(URI uri) {
            this.uri = uri.toString();
            return this;
        }

        /**
         * 添加参数
         *
         * @param name  键值
         * @param value 值
         * @return 对象
         */
        public Builder addParam(String name, String value) {
            params.put(name, value);
            return this;
        }

        /**
         * 添加参数
         *
         * @param params 参数
         * @return 对象
         */
        public Builder addParams(Map<String, String> params) {
            this.params.putAll(params);
            return this;
        }

        /**
         * 添加参数
         *
         * @param params 参数
         * @return 对象
         */
        public Builder addParams(String... params) {
            if (params.length % 2 != 0) {
                throw new IllegalArgumentException("params length must be even");
            }
            for (int i = 0; i < params.length; i += 2) {
                this.params.put(params[i], params[i + 1]);
            }
            return this;
        }

        /**
         * 覆盖请求参数
         * 针对单词请求
         *
         * @param params 参数
         * @return 对象
         */
        public Builder param(Map<String, String> params) {
            this.params = params;
            return this;
        }


        /**
         * 构建请求对象
         * get 请求
         *
         * @return 对象
         */
        public GetRequest get() {
            return new GetRequest(okHttpClient, uri, headers);
        }

        /**
         * 构建请求对象
         * put 请求
         *
         * @return 对象
         */
        public PutRequest put() {
            return new PutRequest(okHttpClient, uri, headers);
        }

        /**
         * 构建请求对象
         * post 请求
         *
         * @return 对象
         */
        public PostRequest post() {
            return new PostRequest(okHttpClient, uri, headers);
        }

        /**
         * 构建请求对象
         * delete 请求
         *
         * @return 对象
         */
        public DeleteRequest delete() {
            return new DeleteRequest(okHttpClient, uri, headers);
        }

        /**
         * 构建请求对象
         * patch 请求
         *
         * @return 对象
         */
        public PatchRequest patch() {
            return new PatchRequest(okHttpClient, uri, headers);
        }

        /**
         * 构建请求对象
         * head 请求
         *
         * @return 对象
         */
        public HeadRequest head() {
            return new HeadRequest(okHttpClient, uri, headers);
        }

        /**
         * 构建请求对象
         * options 请求
         *
         * @return 对象
         */
        public OptionsRequest options() {
            return new OptionsRequest(okHttpClient, uri, headers);
        }

        /**
         * 构建请求对象
         * trace 请求
         *
         * @return 对象
         */
        public TraceRequest trace() {
            return new TraceRequest(okHttpClient, uri, headers);
        }

        /**
         * 构建请求对象
         * connect 请求
         *
         * @return 对象
         */
        public ConnectRequest connect() {
            return new ConnectRequest(okHttpClient, uri, headers);
        }

        /**
         * 构建请求对象
         * socket 请求
         *
         * @return 对象
         */
        public SocketRequest socket() {
            return new SocketRequest(okHttpClient, uri, headers);
        }
    }

    public static class GetRequest extends BaseBuilder {
        public GetRequest(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
            params = new HashMap<>();
        }

        /**
         * 完成构建
         *
         * @return 对象
         */
        public SendRequest complete() {
            return new SendRequest(builder(), this);
        }

        /**
         * 快捷执行请求
         *
         * @return 对象
         */
        public Response execute() {
            return complete().execute();
        }

        private Request builder() {
            builderUri();
            Request.Builder builder = new Request.Builder();
            builder.url(uri);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            return builder.build();
        }
    }

    public static class PutRequest extends BaseBuilder {
        /**
         * 请求体类型
         * 默认 application/json
         */
        private ContentTypeEnum contentType;

        /**
         * 请求体数据
         */
        private String body;

        public PutRequest(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
            contentType = ContentTypeEnum.APPLICATION_JSON;
        }

        /**
         * 设置请求体数据
         *
         * @param body 数据
         * @return 对象
         */
        public PutRequest body(String body) {
            this.body = body;
            return this;
        }

        /**
         * 设置请求体数据
         *
         * @param body 数据
         * @return 对象
         */
        public PutRequest body(byte[] body) {
            this.body = new String(body);
            return this;
        }

        /**
         * 设置请求体数据
         *
         * @param body 数据
         * @return 对象
         */
        public PutRequest body(InputStream body) throws IOException {
            this.body = new String(IOUtils.toByteArray(body));
            return this;
        }

        /**
         * 设置请求体数据
         * 自动识别对象类型，当允许转为JSON自动设置请求头为 application/json
         *
         * @param body 数据
         * @return 对象
         */
        public PutRequest body(Object body) {
            if (body instanceof String) {
                this.body = (String) body;
                if (JSON.isValid(this.body)) {
                    contentType = ContentTypeEnum.APPLICATION_JSON;
                }
                return this;
            }
            try {
                this.body = JSON.toJSONString(body);
                contentType = ContentTypeEnum.APPLICATION_JSON;
            } catch (Exception e) {
                this.body = body.toString();
            }
            return this;
        }

        /**
         * 请求体类型
         * 默认 application/json
         *
         * @param contentType 类型
         * @return 对象
         */
        public PutRequest contentType(ContentTypeEnum contentType) {
            this.contentType = contentType;
            return this;
        }

        /**
         * 请求体类型
         * 默认 application/json
         *
         * @param contentType 类型
         * @return 对象
         */
        public PutRequest contentType(String contentType) {
            this.contentType = ContentTypeEnum.APPLICATION_JSON.valueOfType(contentType);
            return this;
        }


        /**
         * 表单请求
         * 文件表单
         *
         * @return 对象
         */
        public MultipartFormDataRequest form() {
            contentType = ContentTypeEnum.MULTIPART_FORM_DATA;
            builderUri();
            return new MultipartFormDataRequest(RequestMethodEnum.PUT, okHttpClient, uri, headers);
        }

        /**
         * 表单请求
         * 普通表单
         *
         * @return 对象
         */
        public XWWWFormUrlencodedRequest xwwwFormUrlencoded() {
            contentType = ContentTypeEnum.APPLICATION_X_WWW_FORM_URLENCODED;
            builderUri();
            return new XWWWFormUrlencodedRequest(RequestMethodEnum.PUT, okHttpClient, uri, headers);
        }

        /**
         * 二进制请求
         */
        public BinaryRequest binary() {
            contentType = ContentTypeEnum.APPLICATION_OCTET_STREAM;
            builderUri();
            return new BinaryRequest(RequestMethodEnum.PUT, okHttpClient, uri, headers);
        }


        /**
         * 完成构建
         *
         * @return 对象
         */
        public SendRequest complete() {
            return new SendRequest(builder(), this);
        }

        /**
         * 快捷执行请求
         *
         * @return 对象
         */
        public Response execute() {
            return complete().execute();
        }

        private Request builder() {
            builderUri();
            Request.Builder builder = new Request.Builder();
            builder.url(uri);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 未配置请求体,代表不需要请求体
            if (body != null) {
                builder.addHeader("Content-Type", contentType.getValue());
                RequestBody requestBody = RequestBody.create(body, MediaType.parse(contentType.getValue()));
                builder.put(requestBody);
            }
            return builder.build();
        }
    }

    public static class PostRequest extends BaseBuilder {
        /**
         * 请求体类型
         * 默认 application/json
         */
        private ContentTypeEnum contentType;

        /**
         * 请求体数据
         */
        private String body;

        public PostRequest(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
            contentType = ContentTypeEnum.APPLICATION_JSON;
        }


        /**
         * 设置请求体数据
         *
         * @param body 数据
         * @return 对象
         */
        public PostRequest body(String body) {
            this.body = body;
            return this;
        }

        /**
         * 设置请求体数据
         *
         * @param body 数据
         * @return 对象
         */
        public PostRequest body(byte[] body) {
            this.body = new String(body);
            return this;
        }

        /**
         * 设置请求体数据
         *
         * @param body 数据
         * @return 对象
         */
        public PostRequest body(InputStream body) throws IOException {
            this.body = new String(IOUtils.toByteArray(body));
            return this;
        }

        /**
         * 设置请求体数据
         * 自动识别对象类型，当允许转为JSON自动设置请求头为 application/json
         *
         * @param body 数据
         * @return 对象
         */
        public PostRequest body(Object body) {
            if (body instanceof String) {
                this.body = (String) body;
                if (JSON.isValid(this.body)) {
                    contentType = ContentTypeEnum.APPLICATION_JSON;
                }
                return this;
            }
            try {
                this.body = JSON.toJSONString(body);
                contentType = ContentTypeEnum.APPLICATION_JSON;
            } catch (Exception e) {
                this.body = body.toString();
            }
            return this;
        }

        /**
         * 请求体类型
         * 默认 application/json
         *
         * @param contentType 类型
         * @return 对象
         */
        public PostRequest contentType(ContentTypeEnum contentType) {
            this.contentType = contentType;
            return this;
        }

        /**
         * 请求体类型
         * 默认 application/json
         *
         * @param contentType 类型
         * @return 对象
         */
        public PostRequest contentType(String contentType) {
            this.contentType = ContentTypeEnum.APPLICATION_JSON.valueOfType(contentType);
            return this;
        }


        /**
         * 表单请求
         * 文件表单
         *
         * @return 对象
         */
        public MultipartFormDataRequest form() {
            contentType = ContentTypeEnum.MULTIPART_FORM_DATA;
            builderUri();
            return new MultipartFormDataRequest(RequestMethodEnum.POST, okHttpClient, uri, headers);
        }

        /**
         * 表单请求
         * 普通表单
         *
         * @return 对象
         */
        public XWWWFormUrlencodedRequest xwwwFormUrlencoded() {
            contentType = ContentTypeEnum.APPLICATION_X_WWW_FORM_URLENCODED;
            builderUri();
            return new XWWWFormUrlencodedRequest(RequestMethodEnum.POST, okHttpClient, uri, headers);
        }

        /**
         * 二进制请求
         */
        public BinaryRequest binary() {
            contentType = ContentTypeEnum.APPLICATION_OCTET_STREAM;
            builderUri();
            return new BinaryRequest(RequestMethodEnum.POST, okHttpClient, uri, headers);
        }


        /**
         * 完成构建
         *
         * @return 对象
         */
        public SendRequest complete() {
            return new SendRequest(builder(), this);
        }

        /**
         * 快捷执行请求
         *
         * @return 对象
         */
        public Response execute() {
            return complete().execute();
        }

        private Request builder() {
            builderUri();
            Request.Builder builder = new Request.Builder();
            builder.url(uri);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 未配置请求体,代表不需要请求体
            if (body != null) {
                builder.addHeader("Content-Type", contentType.getValue());
                RequestBody requestBody = RequestBody.create(body, MediaType.parse(contentType.getValue()));
                builder.put(requestBody);
            }
            return builder.build();
        }


    }

    public static class DeleteRequest extends BaseBuilder {

        /**
         * 请求体类型
         * 默认 application/json
         */
        private ContentTypeEnum contentType;

        /**
         * 请求体数据
         */
        private String body;


        public DeleteRequest(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
            contentType = ContentTypeEnum.APPLICATION_JSON;
        }

        /**
         * 设置请求体数据
         *
         * @param body 数据
         * @return 对象
         */
        public DeleteRequest body(String body) {
            this.body = body;
            return this;
        }


        /**
         * 设置请求体数据
         * 自动识别对象类型，当允许转为JSON自动设置请求头为 application/json
         *
         * @param body 数据
         * @return 对象
         */
        public DeleteRequest body(Object body) {
            if (body instanceof String) {
                this.body = (String) body;
                if (JSON.isValid(this.body)) {
                    contentType = ContentTypeEnum.APPLICATION_JSON;
                }
                return this;
            }
            try {
                this.body = JSON.toJSONString(body);
                contentType = ContentTypeEnum.APPLICATION_JSON;
            } catch (Exception e) {
                this.body = body.toString();
            }
            return this;
        }

        /**
         * 请求体类型
         * 默认 application/json
         *
         * @param contentType 类型
         * @return 对象
         */
        public DeleteRequest contentType(ContentTypeEnum contentType) {
            this.contentType = contentType;
            return this;
        }

        /**
         * 表单请求
         * 文件表单
         *
         * @return 对象
         */
        public MultipartFormDataRequest form() {
            contentType = ContentTypeEnum.MULTIPART_FORM_DATA;
            builderUri();
            return new MultipartFormDataRequest(RequestMethodEnum.DELETE, okHttpClient, uri, headers);
        }

        /**
         * 表单请求
         * 普通表单
         *
         * @return 对象
         */
        public XWWWFormUrlencodedRequest xwwwFormUrlencoded() {
            contentType = ContentTypeEnum.APPLICATION_X_WWW_FORM_URLENCODED;
            builderUri();
            return new XWWWFormUrlencodedRequest(RequestMethodEnum.DELETE, okHttpClient, uri, headers);
        }

        /**
         * 完成构建
         *
         * @return 对象
         */
        public SendRequest complete() {
            return new SendRequest(builder(), this);
        }

        /**
         * 快捷执行请求
         *
         * @return 对象
         */
        public Response execute() {
            return complete().execute();
        }

        private Request builder() {
            builderUri();
            Request.Builder builder = new Request.Builder();
            builder.url(uri);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 未配置请求体,代表不需要请求体
            if (body != null) {
                builder.addHeader("Content-Type", contentType.getValue());
                RequestBody requestBody = RequestBody.create(body, MediaType.parse(contentType.getValue()));
                builder.delete(requestBody);
            }
            return builder.build();
        }
    }

    public static class PatchRequest extends BaseBuilder {
        public PatchRequest(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
        }
    }

    public static class HeadRequest extends BaseBuilder {
        public HeadRequest(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
        }
    }

    public static class OptionsRequest extends BaseBuilder {
        public OptionsRequest(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
        }
    }

    public static class TraceRequest extends BaseBuilder {
        public TraceRequest(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
        }
    }

    public static class ConnectRequest extends BaseBuilder {
        public ConnectRequest(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
        }
    }

    public static class SocketRequest {
        private final OkHttpClient okHttpClient;
        private final String uri;
        private final Map<String, String> headers;
        private Request.Builder builder;

        public SocketRequest(OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            this.okHttpClient = okHttpClient;
            this.uri = uri;
            this.headers = headers;
            socketInit();
        }

        /**
         * 连接
         *
         * @param listener 监听器 原始版
         * @return 对象
         */
        public MsWebSocket webSocket(WebSocketListener listener) {
            WebSocket webSocket = okHttpClient.newWebSocket(builder.build(), listener);
            return new MsWebSocket(webSocket);
        }

        /**
         * 连接
         *
         * @param listener 监听器 封装
         * @return 对象
         */
        public MsWebSocket webSocket(MsWebSocketListener listener) {
            WebSocket webSocket = okHttpClient.newWebSocket(builder.build(), listener);
            return new MsWebSocket(webSocket);
        }

        private void socketInit() {
            builder = new Request.Builder().url(uri);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public static class SendRequest {
        private final Logger logger = Logger.getLogger("SendRequest");
        private final BaseBuilder base;
        private final Request request;

        public SendRequest(Request builder, BaseBuilder base) {
            request = builder;
            this.base = base;
        }

        /**
         * 同步执行
         */
        public Response execute() {
            try {
                return base.okHttpClient.newCall(request).execute();
            } catch (IOException e) {
                throw new MsToolsRuntimeException(e);
            }
        }

        /**
         * 同步执行
         */
        public MsResponse executeMs() {
            try {
                Response response = base.okHttpClient.newCall(request).execute();
                return new MsResponse(response);
            } catch (IOException e) {
                throw new MsToolsRuntimeException(e);
            }
        }

        /**
         * 异步执行
         */
        public void enqueue(Callback callback) {
            base.okHttpClient.newCall(request).enqueue(callback);
        }

        /**
         * 异步执行
         * 忽略返回值
         */
        public void enqueue() {
            base.okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    String msg = String.format("请求执行失败: %s", e.getMessage());
                    logger.warning(msg);
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    boolean successful = response.isSuccessful();
                    if (successful) {
                        String msg = String.format("请求成功: %s", response.code());
                        logger.info(msg);
                    } else {
                        String msg = String.format("请求失败: %s", response.code());
                        logger.warning(msg);
                    }
                }
            });
        }
    }

    public static class MultipartFormDataRequest extends BaseBuilder {
        private final RequestMethodEnum method;
        /**
         * 数据存储
         */
        private Map<String, Object> data = new HashMap<>();

        public MultipartFormDataRequest(RequestMethodEnum method, OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
            this.method = method;
        }

        /**
         * 添加数据
         *
         * @param key   键
         * @param value 值
         * @return 对象
         */
        public MultipartFormDataRequest addData(String key, Object value) {
            data.put(key, value);
            return this;
        }

        /**
         * 添加数据
         *
         * @param key   键
         * @param value 值
         * @return 对象
         */
        public MultipartFormDataRequest addData(String key, File value) {
            data.put(key, value);
            return this;
        }

        /**
         * 添加数据
         *
         * @param data 数据
         * @return 对象
         */
        public MultipartFormDataRequest addData(Map<String, Object> data) {
            this.data.putAll(data);
            return this;
        }

        /**
         * 覆盖数据
         *
         * @param data 数据
         * @return 对象
         */
        public MultipartFormDataRequest setData(Map<String, Object> data) {
            this.data = data;
            return this;
        }


        /**
         * 发送
         *
         * @return 对象
         */
        public SendRequest send() {
            Request.Builder builder = new Request.Builder().url(uri);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
            multipartBodyBuilder.setType(MultipartBody.FORM);
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof File) {
                    File file = (File) value;
                    multipartBodyBuilder.addFormDataPart(key, file.getName(), RequestBody.create(file, null));
                } else if (value instanceof String) {
                    multipartBodyBuilder.addFormDataPart(key, (String) value);
                } else {
                    throw new MsToolsRuntimeException("不支持的数据类型");
                }
            }
            RequestBody requestBody = multipartBodyBuilder.build();
            switch (method) {
                case POST:
                    builder.post(requestBody);
                    break;
                case PUT:
                    builder.put(requestBody);
                    break;
                case DELETE:
                    builder.delete(requestBody);
                    break;
                default:
                    throw new MsToolsRuntimeException("不支持的请求类型");
            }
            return new SendRequest(builder.build(), this);
        }


    }

    public static class XWWWFormUrlencodedRequest extends BaseBuilder {
        private final RequestMethodEnum method;

        /**
         * 数据存储
         */
        private Map<String, String> data = new HashMap<>();


        public XWWWFormUrlencodedRequest(RequestMethodEnum method, OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
            this.method = method;
        }

        /**
         * 添加数据
         *
         * @param key   键
         * @param value 值
         * @return 对象
         */
        public XWWWFormUrlencodedRequest addData(String key, String value) {
            data.put(key, value);
            return this;
        }

        /**
         * 添加数据
         *
         * @param data 数据
         * @return 对象
         */
        public XWWWFormUrlencodedRequest addData(Map<String, String> data) {
            this.data.putAll(data);
            return this;
        }

        /**
         * 覆盖数据
         *
         * @param data 数据
         * @return 对象
         */
        public XWWWFormUrlencodedRequest setData(Map<String, String> data) {
            this.data = data;
            return this;
        }


        /**
         * 发送
         *
         * @return 对象
         */
        public SendRequest send() {
            Request.Builder builder = new Request.Builder().url(uri);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                formBodyBuilder.add(entry.getKey(), entry.getValue());
            }
            RequestBody requestBody = formBodyBuilder.build();
            switch (method) {
                case POST:
                    builder.post(requestBody);
                    break;
                case PUT:
                    builder.put(requestBody);
                    break;
                case DELETE:
                    builder.delete(requestBody);
                    break;
                default:
                    throw new MsToolsRuntimeException("不支持的请求类型");
            }
            return new SendRequest(builder.build(), this);
        }

    }

    public static class BinaryRequest extends BaseBuilder {
        private final RequestMethodEnum method;

        /**
         * 数据存储
         */
        private Object data;

        public BinaryRequest(RequestMethodEnum put, OkHttpClient okHttpClient, String uri, Map<String, String> headers) {
            super(okHttpClient, uri, headers);
            method = put;
        }

        /**
         * 添加数据
         *
         * @param data 数据
         * @return 对象
         */
        public BinaryRequest setData(Object data) {
            this.data = data;
            return this;
        }

        /**
         * 添加数据
         *
         * @param data 数据
         * @return 对象
         */
        public BinaryRequest addData(File data) {
            this.data = data;
            return this;
        }

        /**
         * 添加数据
         *
         * @param data 数据
         * @return 对象
         */
        public BinaryRequest addData(byte[] data) {
            this.data = data;
            return this;
        }

        /**
         * 发送
         *
         * @return 对象
         */
        public SendRequest send() {
            Request.Builder builder = new Request.Builder().url(uri);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            RequestBody requestBody;
            if (data instanceof File) {
                File file = (File) data;
                requestBody = RequestBody.create(file, MediaType.parse(getMimeType(file)));
            } else if (data instanceof byte[]) {
                requestBody = RequestBody.create((byte[]) data, MediaType.parse("application/octet-stream"));
            } else {
                throw new MsToolsRuntimeException("不支持的数据类型");
            }
            switch (method) {
                case POST:
                    builder.post(requestBody);
                    break;
                case PUT:
                    builder.put(requestBody);
                    break;
                default:
                    throw new MsToolsRuntimeException("不支持的请求类型");
            }
            return new SendRequest(builder.build(), this);
        }

        /**
         * 识别文件类型
         * 返回文件对应的MIME类型
         */
        private String getMimeType(File file) {
            FileNameMap fileNameMap = URLConnection.getFileNameMap();
            String path = file.getAbsolutePath();
            String contentTypeFor = fileNameMap.getContentTypeFor(path);
            if (contentTypeFor == null) {
                contentTypeFor = "application/octet-stream";
            }
            return contentTypeFor;
        }


    }
}
