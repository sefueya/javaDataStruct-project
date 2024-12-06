package com.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * ClassName: PolandNotation
 * Package: com.stack
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/10 10:20
 * @Version 1.0
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 表达式,为了方便数字之间用 空格隔开,如果不用则用 index从左至右扫描，如上计算器实现
        String suffixExpression = "3 4 + 5 * 6 -";  // 中缀(3+4)*5-6
        // 测试2："4 5 * 8 - 60 + 8 2 /" +  <- 4*5-8+60+8/2
        // 将表达式分割后放入 List中，配合栈完成计算
        List<String> list = PolandNotation.getListString(suffixExpression);
        System.out.println(calculate(list));

        String midExpression = "4 * 5 - 8 + 60 + 8 / 2";
        String suffixExpression2 = PolandNotation.midExpressionToSuffixExpression(midExpression);
        System.out.println(suffixExpression2);
    }

    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for(String ch:split){
            list.add(ch);
        }

        return list;
    }

    public static int calculate(List<String> list){
        Stack<String> stack = new Stack<>();

        for(String ch:list){
            // 不再写一个方法判断是否是数字,而是 使用 正则 判断是否是数字,而且兼容多位数
            if(ch.matches("\\d+")){
                // 是数字则入栈
                stack.push(ch);
            }else{
                // 如果是运算符则pop出两个数进行运算(注意是 次顶元素 - 栈顶元素)，然后将结果放回
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = switch (ch){
                    case "+" -> num1 + num2;
                    case "-" -> num2 - num1;
                    case "*" -> num2 * num1;
                    case "/" -> num2 / num1;
                    default -> 0;
                };
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static String midExpressionToSuffixExpression(String midExpression){
        Stack<String> s1 = new Stack<>();
        // 原本s2应该用栈，但是s2存放着最终结果，而想要的后缀表达式是s2依次弹出的逆序，这里直接用一个list，直接打印list就是原本栈s2的逆序结果
        List<String> s2 = new ArrayList<>();
        List<String> list = PolandNotation.getListString(midExpression);
        for(String ch:list){
            // 如果是数字直接压入s2
            if(ch.matches("\\d+")){
                s2.add(ch);
            // 这里将所有非数字都默认当作运算符，不判断是否为非法字符
            }else if(ch.equals("(") || s1.empty() || priority(ch) > priority(s1.peek())){
                // 如果是运算符，s1为空 或 优先级大于 s1栈顶 又或者 运算符是 '(' 都直接压入s1
                s1.push(ch);
            }else if(ch.equals(")")){
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else{
                // 如果运算符优先级 小于等于 s1栈顶则弹出s1栈顶并放入s2，直到这个运算符的优先级大于 s1的栈顶 或者 s1为空才将运算符放入 s1
                while(!s1.empty() && priority(ch) <= priority(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.push(ch);
            }
        }
        // 最后将s1剩余元素全部push到s2中
        while(!s1.empty()){
            s2.add(s1.pop());
        }
        return String.join(" ", s2);

    }

    // 判断运算符的优先级，返回值越大表示此运算符优先级越高
    public static int priority(String oper){
        return switch (oper){
            case "*", "/" -> 1;
            case "+", "-" -> 0;
            default -> -1;
        };
    }
}


