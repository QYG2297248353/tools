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

package com.ms.tools.spring.el;

import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * SpringELUtils Spring El 表达式工具类
 *
 * @author ms2297248353
 */
public class SpringELUtils {

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    /**
     * 解析表达式
     * <p>
     * 书写规则：#变量名.属性名
     *
     * @param expression 表达式 例如：#user.name
     * @param context    上下文
     * @return 解析结果
     */
    public static Object evaluate(String expression, Map<String, Object> context) {
        Expression exp = PARSER.parseExpression(expression);
        EvaluationContext evalContext = createContext(context);
        return exp.getValue(evalContext);
    }

    /**
     * 解析表达式
     * <p>
     * 书写规则：#变量名.属性名
     * 例如：#user.name
     *
     * @param expression  表达式
     * @param context     上下文
     * @param desiredType 返回类型
     * @param <T>         返回类型
     * @return 解析结果
     */
    public static <T> T evaluate(String expression, Map<String, Object> context, Class<T> desiredType) {
        Expression exp = PARSER.parseExpression(expression);
        EvaluationContext evalContext = createContext(context);
        return exp.getValue(evalContext, desiredType);
    }

    /**
     * 设置值
     *
     * @param expression 表达式
     * @param context    上下文
     * @param value      值
     */
    public static void setValue(String expression, Map<String, Object> context, Object value) {
        Expression exp = PARSER.parseExpression(expression);
        EvaluationContext evalContext = createContext(context);
        exp.setValue(evalContext, value);
    }

    /**
     * 执行方法
     *
     * @param expression 表达式
     * @param context    上下文
     * @return 执行结果
     */
    public static Object executeMethod(String expression, Map<String, Object> context) {
        Expression exp = PARSER.parseExpression(expression);
        EvaluationContext evalContext = createContext(context);
        return exp.getValue(evalContext, Object.class);
    }

    private static EvaluationContext createContext(Map<String, Object> context) {
        StandardEvaluationContext evalContext = new StandardEvaluationContext();
        evalContext.addPropertyAccessor(new MapAccessor());
        evalContext.setVariables(context);
        return evalContext;
    }

    /**
     * 解析表达式
     *
     * @param expression 表达式
     * @param object     对象
     * @return 解析结果
     */
    public static Object evaluate(String expression, Object object) {
        Expression exp = PARSER.parseExpression(expression);
        EvaluationContext evalContext = createContext(object);
        return exp.getValue(evalContext);
    }

    /**
     * 解析表达式
     *
     * @param expression  表达式
     * @param object      对象
     * @param desiredType 返回类型
     * @param <T>         返回类型
     * @return 解析结果
     */
    public static <T> T evaluate(String expression, Object object, Class<T> desiredType) {
        Expression exp = PARSER.parseExpression(expression);
        EvaluationContext evalContext = createContext(object);
        return exp.getValue(evalContext, desiredType);
    }

    /**
     * 设置值
     *
     * @param expression 表达式
     * @param object     对象
     * @param value      值
     */
    public static void setValue(String expression, Object object, Object value) {
        Expression exp = PARSER.parseExpression(expression);
        EvaluationContext evalContext = createContext(object);
        exp.setValue(evalContext, value);
    }

    private static EvaluationContext createContext(Object object) {
        StandardEvaluationContext evalContext = new StandardEvaluationContext();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                evalContext.setVariable(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return evalContext;
    }
}
