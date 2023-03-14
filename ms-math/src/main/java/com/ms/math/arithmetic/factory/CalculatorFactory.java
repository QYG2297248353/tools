package com.ms.math.arithmetic.factory;

import com.ms.core.base.basic.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class CalculatorFactory {

    /**
     * 计算表达式的值
     *
     * @param expression 表达式
     * @return 表达式的值
     */
    public static BigDecimal calculate(String expression) {
        return calculate(expression, null, null);
    }

    /**
     * 计算表达式的值
     *
     * @param expression   表达式
     * @param accuracy     精度
     * @param roundingMode 舍入模式
     * @return 表达式的值
     */
    public static BigDecimal calculate(String expression, Integer accuracy, RoundingMode roundingMode) {
        // 去除表达式中的空格
        expression = StringUtils.replaceBlank(expression);
        Stack<BigDecimal> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                i = nextNumber(expression, numbers, i, c);
            } else if (isOperationSymbol(c)) {
                if ((i == 0 && expression.charAt(i) == '-') || (expression.charAt(i - 1) == '(' && expression.charAt(i) == '-')) {
                    i = nextNumber(expression, numbers, i, c);
                    continue;
                }
                if (i + 1 < expression.length() && expression.charAt(i + 1) == '-') {
                    // 截取剩余的字符串
                    StringBuilder num = new StringBuilder();
                    char c1 = expression.charAt(i + 1);
                    num.append(c1);
                    i++;
                    String ex = expression.substring(i + 1);
                    int ch = 0;
                    while (ch < ex.length() &&
                            (Character.isDigit(ex.charAt(ch)) || ex.charAt(ch) == '.')
                    ) {
                        num.append(ex.charAt(ch));
                        i++;
                        ch++;
                    }
                    String val = num.toString();
                    numbers.push(new BigDecimal(val));
                }
                // 处理其他运算符
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    evaluate(numbers, operators, roundingMode);
                }
                operators.push(c);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    evaluate(numbers, operators, roundingMode);
                }
                operators.pop();
            }
        }

        while (!operators.isEmpty()) {
            evaluate(numbers, operators, roundingMode);
        }

        BigDecimal result = numbers.pop();
        if (accuracy != null) {
            result = result.setScale(accuracy, BigDecimal.ROUND_HALF_UP);
        }
        return result;
    }

    private static int nextNumber(String expression, Stack<BigDecimal> numbers, int i, char c) {
        StringBuilder num = new StringBuilder();
        num.append(c);
        while (i + 1 < expression.length() &&
                (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')
        ) {
            num.append(expression.charAt(i + 1));
            i++;
        }
        String val = num.toString();
        numbers.push(new BigDecimal(val));
        return i;
    }

    private static boolean isOperationSymbol(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private static void evaluate(Stack<BigDecimal> numbers, Stack<Character> operators, RoundingMode roundingMode) {
        BigDecimal num2 = numbers.pop();
        BigDecimal num1 = numbers.pop();
        char operator = operators.pop();
        switch (operator) {
            case '+':
                numbers.push(num1.add(num2));
                break;
            case '-':
                numbers.push(num1.subtract(num2));
                break;
            case '*':
                numbers.push(num1.multiply(num2));
                break;
            case '/':
                if (roundingMode != null) {
                    numbers.push(num1.divide(num2, roundingMode));
                } else {
                    numbers.push(num1.divide(num2));
                }
                break;
            case '%':
                numbers.push(num1.remainder(num2));
                break;
            case '^':
                numbers.push(num1.pow(num2.intValue()));
                break;
            default:
                break;
        }
    }
}
