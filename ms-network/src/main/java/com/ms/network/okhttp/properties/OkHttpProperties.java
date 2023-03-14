package com.ms.network.okhttp.properties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "ms.network.okhttp")
@RefreshScope
public class OkHttpProperties {
}
