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

package com.ms.security.jwt.jjwt.factory;

import com.ms.core.exception.base.MsToolsRuntimeException;
import com.ms.core.exception.security.jwt.MsJwtExpiredException;
import com.ms.core.exception.security.jwt.MsJwtRequireException;
import com.ms.core.exception.security.jwt.MsJwtRequireNullException;
import com.ms.security.codec.Base64;
import com.ms.security.encryption.key.GenerateKeyPair;
import com.ms.security.encryption.key.JJWTKey;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.KeyPair;
import java.util.Date;
import java.util.Map;

/**
 * @author qyg2297248353
 */
public class JJWTFactory {
    /**
     * 获取密钥-非对称加密
     *
     * @param signatureAlgorithm 加密类型
     * @return 密钥
     */
    public static GenerateKeyPair getKeyPair(SignatureAlgorithm signatureAlgorithm) {
        KeyPair keyPair = Keys.keyPairFor(signatureAlgorithm);
        return new GenerateKeyPair(keyPair);
    }

    /**
     * 获取密钥-对称加密
     *
     * @param secretKey 对称密钥字符串
     * @return 密钥
     */
    public static SecretKey getSecretKeyByString(String secretKey) {
        return Keys.hmacShaKeyFor(Base64.decodeBase64(secretKey));
    }

    /**
     * 获取密钥-对称加密
     *
     * @param signatureAlgorithm 加密类型
     * @return 密钥
     */
    public static SecretKey getSecretKey(SignatureAlgorithm signatureAlgorithm) {
        return Keys.secretKeyFor(signatureAlgorithm);
    }

    /**
     * 获取密钥-对称加密
     *
     * @param signatureAlgorithm 加密类型
     * @return 密钥
     */
    public static String getSecretKeyAsString(SignatureAlgorithm signatureAlgorithm) {
        SecretKey secretKey = Keys.secretKeyFor(signatureAlgorithm);
        return getSecretKeyAsString(secretKey);
    }

    /**
     * 获取密钥-对称加密
     *
     * @param secretKey 密钥-对称加密
     * @return 密钥
     */
    public static String getSecretKeyAsString(SecretKey secretKey) {
        return Base64.encodeBase64String(secretKey.getEncoded());
    }

    /**
     * 获取偏移时间
     * 不推荐使用
     *
     * @param date 时间
     * @return 时间
     */
    public static Clock getClock(Date date) {
        return () -> date;
    }

    /**
     * 初始化构建器
     *
     * @return 链式构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 初始化解析器
     *
     * @return 链式解析器
     */
    public static Parser parser() {
        return new Parser();
    }

    /**
     * 构建器
     */
    public static class Builder {
        private final JwtBuilder builder;

        private Builder() {
            builder = Jwts.builder();
        }

        /**
         * 构建主体信息-设置密钥(必须调用)
         * 使用单例对象密钥 JJWTKey
         *
         * @return 构建对象
         */
        public Body setKey() {
            builder.signWith(JJWTKey.getJJWTKey().getPrivate());
            return new Body(this);
        }

        /**
         * 构建主体信息-设置密钥(必须调用)
         * 必须先设置密钥才可配置主体信息
         * 非对称密钥使用 PrivateKey 私钥构建
         * 对称密钥 SecretKey
         * null 使用默认对象JJWTKey密钥 RSA256 PrivateKey
         *
         * @return 构建对象
         */
        public Body setKey(Key key) {
            if (key == null) {
                builder.signWith(JJWTKey.getJJWTKey().getPrivate());
            } else {
                builder.signWith(key);
            }
            return new Body(this);
        }

        /**
         * 构建头-添加参数
         *
         * @param name  参数名
         * @param value 参数值
         * @return 构建对象
         */
        public Builder addHeader(String name, String value) {
            builder.setHeaderParam(name, value);
            return this;
        }

        /**
         * 构建头-批量添加参数
         *
         * @param headers 参数Map
         * @return 构建对象
         */
        public Builder addHeaders(Map<String, Object> headers) {
            builder.setHeaderParams(headers);
            return this;
        }

        /**
         * 覆盖头-覆盖头部数据
         * 此方法将会覆盖已添加的Header(不推荐使用)
         *
         * @param headers 参数Map
         * @return 构建对象
         */
        public Builder coverageHeaders(Map<String, Object> headers) {
            builder.setHeader(headers);
            return this;
        }

        private JwtBuilder getBuilder() {
            return builder;
        }

        /**
         * 信息主体构建
         */
        public static class Body {
            private final JwtBuilder builder;

            private Body(Builder builder) {
                this.builder = builder.getBuilder();
            }


            /**
             * 构建参数-iss（发行人）声明
             *
             * @param iss 创建者(主程序)信息
             * @return 构建对象
             */
            public Body setIssuer(String iss) {
                builder.setIssuer(iss);
                return this;
            }

            /**
             * 构建参数-sub（主题）声明
             *
             * @param sub 主题(模块) 声明
             * @return 构建对象
             */
            public Body setSubject(String sub) {
                builder.setSubject(sub);
                return this;
            }

            /**
             * 构建参数-aud（观众）声明
             *
             * @param aud 使用者信息
             * @return 构建对象
             */
            public Body setAudience(String aud) {
                builder.setAudience(aud);
                return this;
            }

            /**
             * 构建参数-exp(Expiration Time) Claim
             *
             * @param exp 过期时间
             * @return 构建对象
             */
            public Body setExpiration(Date exp) {
                builder.setExpiration(exp);
                return this;
            }

            /**
             * 构建参数-exp(Expiration Time) Claim
             *
             * @param exp 过期时间
             * @return 构建对象
             */
            public Body setExpiration(long exp) {
                return setExpiration(new Date(exp));
            }

            /**
             * 构建参数-nbf(Not Before) Claim
             * 无默认值
             *
             * @param nbf 生效时间(应该晚或等于发布时间)
             * @return 构建对象
             */
            public Body setNotBefore(Date nbf) {
                builder.setNotBefore(nbf);
                return this;
            }

            /**
             * 构建参数-iat（发布于）声明
             * 默认初始化创建时间
             *
             * @param iat 发布时间
             * @return 构建对象
             */
            public Body setIssuedAt(Date iat) {
                builder.setIssuedAt(iat);
                return this;
            }

            /**
             * 构建参数-jti（JWT ID）声明
             *
             * @param jti JWT ID 声明
             * @return 构建对象
             */
            public Body setId(String jti) {
                builder.setId(jti);
                return this;
            }

            /**
             * 自定义构建参数-claim
             * 允许重复调用一次或多次
             *
             * @param claimK 自定义参数名
             * @param claimV 自定义参数值
             * @return 构建对象
             */
            public Body addClaim(String claimK, String claimV) {
                builder.claim(claimK, claimV);
                return this;
            }

            /**
             * 自定义构建参数-claim
             * 允许重复调用一次或多次
             *
             * @param claimK 自定义参数名
             * @param claimV 自定义参数对象
             * @return 构建对象
             */
            public Body addClaim(String claimK, Object claimV) {
                builder.claim(claimK, claimV);
                return this;
            }

            /**
             * 覆盖主体信息-claim
             *
             * @param maps 主体信息对象
             * @return 构建对象
             */
            public Body coverageClaim(Map<String, ?> maps) {
                builder.setClaims(maps);
                return this;
            }

            /**
             * 覆盖主体信息-claim
             *
             * @param claims 主体信息对象
             * @return 构建对象
             */
            public Body coverageClaim(Claims claims) {
                builder.setClaims(claims);
                return this;
            }

            /**
             * 压缩
             * <p>
             * 现JWA标准deflate压缩算法的编解码器
             * CompressionCodecs.DEFLATE
             * <p>
             * 实现gzip压缩算法的编解码器
             * CompressionCodecs.GZIP
             *
             * @param codec 压缩方式
             * @return 构建
             */
            public <T extends CompressionCodec> Body compress(T codec) {
                // builder.compressWith(CompressionCodecs.DEFLATE);
                builder.compressWith(codec);
                return this;
            }

            /**
             * 获取构建器
             * 自定义构建
             *
             * @return 构建器
             */
            public JwtBuilder getBuilder() {
                return builder;
            }

            /**
             * 返回构建对象
             *
             * @return 构建对象
             */
            public Builders builders() {
                return new Builders(this);
            }

            /**
             * 完成构建
             *
             * @return jwt
             */
            public String compact() {
                return builder.compact();
            }

        }
    }

    /**
     * 构建器
     */
    public static class Builders {
        private JwtBuilder builder;

        private Builders(Builder.Body builder) {
            this.builder = builder.getBuilder();
        }

        /**
         * 构建
         *
         * @return 获取构建
         */
        public JwtBuilder getBuilder() {
            return builder;
        }

        /**
         * 完成构建
         *
         * @return jwt
         */
        public String compact() {
            return builder.compact();
        }
    }

    /**
     * 解析器
     */
    public static class Parser {
        private final JwtParserBuilder parserBuilder;

        private Parser() {
            parserBuilder = Jwts.parserBuilder();
        }

        /**
         * 偏移时间
         * 60秒 = 3 * 60
         *
         * @param seconds 时间 单位秒 s
         * @return 构建
         */
        public Parser setClock(long seconds) {
            parserBuilder.setAllowedClockSkewSeconds(seconds);
            return this;
        }

        /**
         * 偏移时间
         *
         * @param clock 时间
         * @return 构建
         */
        public Parser setClock(Date clock) {
            parserBuilder.setClock(JJWTFactory.getClock(clock));
            return this;
        }

        /**
         * 偏移时间
         *
         * @param clock 时间
         * @return 构建
         */
        public Parser setClock(Clock clock) {
            parserBuilder.setClock(clock);
            return this;
        }

        /**
         * 安全验证-主题验证
         *
         * @param subject 主题
         * @return 构建
         */
        public Parser requireSubject(String subject) {
            parserBuilder.requireSubject(subject);
            return this;
        }

        /**
         * 安全验证-ID验证
         *
         * @param id ID
         * @return 构建
         */
        public Parser requireId(String id) {
            parserBuilder.requireId(id);
            return this;
        }

        /**
         * 安全验证-生效时间验证
         *
         * @param notBefore 生效时间
         * @return 构建
         */
        public Parser requireSubject(Date notBefore) {
            parserBuilder.requireNotBefore(notBefore);
            return this;
        }

        /**
         * 安全验证-发布者验证
         *
         * @param issuer 发布者
         * @return 构建
         */
        public Parser requireIssuer(String issuer) {
            parserBuilder.requireIssuer(issuer);
            return this;
        }

        /**
         * 安全验证-过期时间验证
         *
         * @param expiration 过期时间
         * @return 构建
         */
        public Parser requireExpiration(Date expiration) {
            parserBuilder.requireExpiration(expiration);
            return this;
        }

        /**
         * 安全验证-观众(受众)验证
         *
         * @param audience 受众
         * @return 构建
         */
        public Parser requireAudience(String audience) {
            parserBuilder.requireAudience(audience);
            return this;
        }

        /**
         * 安全验证-发布时间验证
         *
         * @param issuedAt 发布时间
         * @return 构建
         */
        public Parser requireIssuedAt(Date issuedAt) {
            parserBuilder.requireIssuedAt(issuedAt);
            return this;
        }

        /**
         * 安全验证-自定义参数验证
         *
         * @param name  参数名
         * @param value 参数值
         * @return 构建
         */
        public Parser require(String name, String value) {
            parserBuilder.require(name, value);
            return this;
        }

        /**
         * 设置自定义压缩解压器
         *
         * @param ccr 压缩解压接口
         * @return 解析对象
         */
        public <T extends CompressionCodecResolver> Parser setCompressionCodecResolver(T ccr) {
            parserBuilder.setCompressionCodecResolver(ccr);
            return this;
        }

        /**
         * 设置密钥
         * 非对称密钥使用 publicKey 公钥解析
         * 对称密钥 SecretKey
         *
         * @return 解析对象
         */
        public ParserJwt setKey() {
            parserBuilder.setSigningKey(JJWTKey.getJJWTKey().getPublicKey());
            return new ParserJwt(this);
        }

        /**
         * 设置密钥
         * 非对称密钥使用 publicKey 公钥解析
         * 对称密钥 SecretKey
         *
         * @param key 密钥
         * @return 解析对象
         */
        public ParserJwt setKey(Key key) {
            parserBuilder.setSigningKey(key);
            return new ParserJwt(this);
        }

        /**
         * 设置密钥解析器
         * 非对称密钥使用 publicKey 公钥解析
         * 对称密钥 SecretKey
         *
         * @param signingKeyResolver 密钥解析接口
         * @return 解析对象
         */
        public <T extends SigningKeyResolverAdapter> ParserJwt setKey(T signingKeyResolver) {
            parserBuilder.setSigningKeyResolver(signingKeyResolver);
            return new ParserJwt(this);
        }

        private JwtParserBuilder getParserBuilder() {
            return parserBuilder;
        }

        public static class ParserJwt {
            private static final String JWT_FORMAT_EXCEPTION = "JWT格式异常";
            private static final String JWT_SIGNATURE_EXCEPTION = "JWT签名异常";
            private static final String EXISTENCE_OF_ILLEGAL_PARAMETERS = "存在非法参数";
            private static final String VALIDATION_FIELD_NOT_EXIST = "效验字段不存在";
            private static final String VALIDATION_FIELD_VALUES_NOT_MATCH = "效验字段值不匹配";
            private final JwtParserBuilder parserBuilder;

            private ParserJwt(Parser parser) {
                parserBuilder = parser.getParserBuilder();
            }

            /**
             * 解析为 JWT对象
             *
             * @param jwt jwt
             * @return JWT对象
             * @throws MsJwtExpiredException     JWT过期异常
             * @throws MsJwtRequireException     JWT效验字段不存在
             * @throws MsJwtRequireNullException JWT效验字段值不匹配
             */
            public Jwt parseJwt(String jwt) throws MsJwtExpiredException, MsJwtRequireException, MsJwtRequireNullException {
                try {
                    return parserBuilder.build().parse(jwt);
                } catch (ExpiredJwtException e) {
                    // 过期异常
                    throw new MsJwtExpiredException(e);
                } catch (MalformedJwtException e) {
                    // 格式异常
                    throw new MsToolsRuntimeException(JWT_FORMAT_EXCEPTION, e);
                } catch (SignatureException e) {
                    // 签名异常
                    throw new MsToolsRuntimeException(JWT_SIGNATURE_EXCEPTION, e);
                } catch (IllegalArgumentException e) {
                    // 解析异常
                    throw new MsToolsRuntimeException(EXISTENCE_OF_ILLEGAL_PARAMETERS, e);
                } catch (MissingClaimException e) {
                    // 验证字段不存在
                    throw new MsJwtRequireNullException(VALIDATION_FIELD_NOT_EXIST, e);
                } catch (IncorrectClaimException e) {
                    // 验证字段值不匹配
                    throw new MsJwtRequireException(VALIDATION_FIELD_VALUES_NOT_MATCH, e);
                }
            }

            /**
             * 解析为 Jws对象
             *
             * @param jwt jwt
             * @return Jws对象
             * @throws MsJwtExpiredException     JWT过期异常
             * @throws MsJwtRequireException     JWT效验字段不存在
             * @throws MsJwtRequireNullException JWT效验字段值不匹配
             */
            public Jws<Claims> parseJws(String jwt) throws MsJwtExpiredException, MsJwtRequireNullException, MsJwtRequireException {
                try {
                    return parserBuilder.build().parseClaimsJws(jwt);
                } catch (ExpiredJwtException e) {
                    // 过期异常
                    throw new MsJwtExpiredException(e);
                } catch (MalformedJwtException e) {
                    // 格式异常
                    throw new MsToolsRuntimeException(JWT_FORMAT_EXCEPTION, e);
                } catch (SignatureException e) {
                    // 签名异常
                    throw new MsToolsRuntimeException(JWT_SIGNATURE_EXCEPTION, e);
                } catch (IllegalArgumentException e) {
                    // 解析异常
                    throw new MsToolsRuntimeException(EXISTENCE_OF_ILLEGAL_PARAMETERS, e);
                } catch (MissingClaimException e) {
                    // 验证字段不存在
                    throw new MsJwtRequireNullException(VALIDATION_FIELD_NOT_EXIST, e);
                } catch (IncorrectClaimException e) {
                    // 验证字段值不匹配
                    throw new MsJwtRequireException(VALIDATION_FIELD_VALUES_NOT_MATCH, e);
                }
            }

            /**
             * 解析为 对象
             *
             * @param jwt jwt
             * @return 对象
             * @throws MsJwtExpiredException     JWT过期异常
             * @throws MsJwtRequireException     JWT效验字段不存在
             * @throws MsJwtRequireNullException JWT效验字段值不匹配
             */
            public Object parseJwtClaims(String jwt) throws MsJwtExpiredException, MsJwtRequireNullException, MsJwtRequireException {
                try {
                    return parserBuilder.build().parse(jwt).getBody();
                } catch (ExpiredJwtException e) {
                    // 过期异常
                    throw new MsJwtExpiredException(e);
                } catch (MalformedJwtException e) {
                    // 格式异常
                    throw new MsToolsRuntimeException(JWT_FORMAT_EXCEPTION, e);
                } catch (SignatureException e) {
                    // 签名异常
                    throw new MsToolsRuntimeException(JWT_SIGNATURE_EXCEPTION, e);
                } catch (IllegalArgumentException e) {
                    // 解析异常
                    throw new MsToolsRuntimeException(EXISTENCE_OF_ILLEGAL_PARAMETERS, e);
                } catch (MissingClaimException e) {
                    // 验证字段不存在
                    throw new MsJwtRequireNullException(VALIDATION_FIELD_NOT_EXIST, e);
                } catch (IncorrectClaimException e) {
                    // 验证字段值不匹配
                    throw new MsJwtRequireException(VALIDATION_FIELD_VALUES_NOT_MATCH, e);
                }
            }

            /**
             * 解析为 Claims对象
             *
             * @param jwt jwt
             * @return Claims对象
             * @throws MsJwtExpiredException     JWT过期异常
             * @throws MsJwtRequireException     JWT效验字段不存在
             * @throws MsJwtRequireNullException JWT效验字段值不匹配
             */
            public Claims parseJwsClaims(String jwt) throws MsJwtExpiredException, MsJwtRequireNullException, MsJwtRequireException {
                try {
                    return parserBuilder.build().parseClaimsJws(jwt).getBody();
                } catch (ExpiredJwtException e) {
                    // 过期异常
                    throw new MsJwtExpiredException(e);
                } catch (MalformedJwtException e) {
                    // 格式异常
                    throw new MsToolsRuntimeException(JWT_FORMAT_EXCEPTION, e);
                } catch (SignatureException e) {
                    // 签名异常
                    throw new MsToolsRuntimeException(JWT_SIGNATURE_EXCEPTION, e);
                } catch (IllegalArgumentException e) {
                    // 解析异常
                    throw new MsToolsRuntimeException(EXISTENCE_OF_ILLEGAL_PARAMETERS, e);
                } catch (MissingClaimException e) {
                    // 验证字段不存在
                    throw new MsJwtRequireNullException(VALIDATION_FIELD_NOT_EXIST, e);
                } catch (IncorrectClaimException e) {
                    // 验证字段值不匹配
                    throw new MsJwtRequireException(VALIDATION_FIELD_VALUES_NOT_MATCH, e);
                }
            }
        }

    }

}
