## 使用事项

### SpringBoot使用注意事项

> 如果你使用到如下依赖：
> com.ms.network 或 com.ms.push

请在父类 或 项目 pom.xml 中添加如下依赖：

```xml
<!-- okHttp3 -->
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
    <version>4.10.0</version>
</dependency>
```

### 插件使用注意事项

[FastJson](https://github.com/alibaba/fastjson2/releases) 官网

> 当前使用 FastJson 2.0.24 版本，以及 1.0兼容版本
