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

package com.ms.mybatis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

public class DemoTest {
    /**
     * 代码生成器
     * <p>
     * 样板代码生成器，只需要配置好数据库连接信息，表名，作者，父包名，父包模块名，即可生成对应的代码
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://ip:port/name_db", "username", "password")
                .globalConfig(builder ->
                        // 设置作者
                        builder.author("ms")
                                // 开启 swagger 模式
                                .enableSwagger()
                )
                .packageConfig(builder ->
                        // 设置父包名
                        builder.parent("com.ms.service.mybatis")
                                // 设置父包模块名
                                .moduleName("auto")
                )
                .strategyConfig(builder ->
                        // 设置需要生成的表名
                        builder.addInclude("tb_user", "tb_role", "tb_user_role")
                )
                .execute();
    }
}
