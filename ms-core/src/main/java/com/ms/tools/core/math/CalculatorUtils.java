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

package com.ms.tools.core.math;


import com.ms.tools.core.math.factory.CalculatorBuilds;
import com.ms.tools.core.math.factory.CalculatorFactory;

import java.math.BigDecimal;

/**
 * 计算器工具类
 *
 * @author ms
 */
public class CalculatorUtils {

    /**
     * 计算表达式的值
     *
     * @param expression 表达式
     * @return 表达式的值
     */
    public static BigDecimal calculate(String expression) {
        return CalculatorFactory.calculate(expression);
    }

    /**
     * 表达式构建
     *
     * @return 表达式构建器
     */
    public static CalculatorBuilds builderExpression() {
        return CalculatorBuilds.create();
    }

    /**
     * 列表求和
     *
     * @param numbers 数字
     * @return 求和结果
     */
    public static BigDecimal sum(double... numbers) {
        CalculatorBuilds calculatorBuilds = builderExpression();
        for (double number : numbers) {
            calculatorBuilds.add(number);
        }
        return calculatorBuilds.builder().calculate();
    }

    /**
     * 列表求和
     *
     * @param numbers 数字
     * @return 求和结果
     */
    public static BigDecimal sum(String... numbers) {
        CalculatorBuilds calculatorBuilds = builderExpression();
        for (String number : numbers) {
            calculatorBuilds.add(number);
        }
        return calculatorBuilds.builder().calculate();
    }

    /**
     * 列表求和
     *
     * @param numbers 数字
     * @return 求和结果
     */
    public static BigDecimal sum(BigDecimal... numbers) {
        CalculatorBuilds calculatorBuilds = builderExpression();
        for (BigDecimal number : numbers) {
            calculatorBuilds.add(number.toString());
        }
        return calculatorBuilds.builder().calculate();
    }
}
