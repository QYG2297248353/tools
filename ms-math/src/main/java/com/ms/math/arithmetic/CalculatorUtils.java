package com.ms.math.arithmetic;

import com.ms.math.arithmetic.factory.CalculatorBuilds;
import com.ms.math.arithmetic.factory.CalculatorFactory;

import java.math.BigDecimal;

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
     */
    public static BigDecimal sum(BigDecimal... numbers) {
        CalculatorBuilds calculatorBuilds = builderExpression();
        for (BigDecimal number : numbers) {
            calculatorBuilds.add(number.toString());
        }
        return calculatorBuilds.builder().calculate();
    }
}
