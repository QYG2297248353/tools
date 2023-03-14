package com.ms.math.arithmetic.factory;

import java.math.BigDecimal;

/**
 * 构建表达式
 *
 * @author ms2297248353
 */
public class CalculatorBuilds {

    private String expression = "";

    private CalculatorBuilds() {
    }

    /**
     * 创建计算器
     *
     * @return 计算器
     */
    public static CalculatorBuilds create() {
        return new CalculatorBuilds();
    }

    /**
     * 加法
     *
     * @param number 数字
     * @return 计算器
     */
    public CalculatorBuilds add(double number) {
        expression += "+" + number;
        return this;
    }

    /**
     * 减法
     *
     * @param number 数字
     * @return 计算器
     */
    public CalculatorBuilds subtract(double number) {
        expression += "-" + number;
        return this;
    }

    /**
     * 乘法
     *
     * @param number 数字
     * @return 计算器
     */
    public CalculatorBuilds multiply(double number) {
        expression += "*" + number;
        return this;
    }

    /**
     * 除法
     *
     * @param number 数字
     * @return 计算器
     */
    public CalculatorBuilds divide(double number) {
        expression += "/" + number;
        return this;
    }

    /**
     * 加法
     *
     * @param number 数字
     * @return 计算器
     */
    public CalculatorBuilds add(String number) {
        expression += "+" + number;
        return this;
    }

    /**
     * 减法
     *
     * @param number 数字
     * @return 计算器
     */
    public CalculatorBuilds subtract(String number) {
        expression += "-" + number;
        return this;
    }

    /**
     * 乘法
     *
     * @param number 数字
     * @return 计算器
     */
    public CalculatorBuilds multiply(String number) {
        expression += "*" + number;
        return this;
    }

    /**
     * 除法
     *
     * @param number 数字
     * @return 计算器
     */
    public CalculatorBuilds divide(String number) {
        expression += "/" + number;
        return this;
    }

    /**
     * 括号
     *
     * @param builds 表达式
     * @return 计算器
     */
    public CalculatorBuilds bracket(CalculatorBuilds.Builder builds) {
        expression += "(" + builds.getExpression() + ")";
        return this;
    }

    /**
     * 左侧括号
     *
     * @return 计算器
     */
    public CalculatorBuilds leftBracket() {
        expression += "(";
        return this;
    }

    /**
     * 右侧括号
     *
     * @return 计算器
     */
    public CalculatorBuilds rightBracket() {
        expression += ")";
        return this;
    }

    /**
     * 设置表达式
     *
     * @param expression 表达式
     * @return 计算器
     */
    public CalculatorBuilds setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public Builder builder() {
        return new Builder(expression);
    }

    public static class Builder {
        private String expression = "";

        private Builder(String expression) {
            this.expression = expression;
        }

        /**
         * 计算
         *
         * @return 结果
         */
        public BigDecimal calculate() {
            return CalculatorFactory.calculate(getExpression());
        }

        /**
         * 获取表达式
         *
         * @return 表达式
         */
        public String getEx() {
            return getExpression();
        }

        private String getExpression() {
            // 去除表达式前后的空格，如果首位是运算符，则去除
            return expression.trim().replaceAll("^[+\\-*/]", "");
        }
    }

}
