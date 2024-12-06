package com.stack;

/**
 * ClassName: Calculator
 * Package: com.stack
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/9 10:45
 * @Version 1.0
 */
public class Calculator {
    public static void main(String[] args) {
        // 表达式
        String expression = "10*(1+2*6-1+2*5-20/10)";
        // 创建数栈和符号栈
        ArrayStackTow numStack = new ArrayStackTow(10);
        ArrayStackTow operStack = new ArrayStackTow(10);
        // 用index扫描表达式中各个字符
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = 0;
        while (true){
            // 依次得到expression的每一个字符
            ch = expression.charAt(index++);
            if(operStack.isOper(ch)){
                if(operStack.isEmpty() || ch == '('){
                    operStack.push(ch);

                }else if(ch == ')'){
                    while(operStack.peek() != '('){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                    }
                    operStack.pop();

                }else if(operStack.priority(ch) > operStack.priority(operStack.peek())){
                    operStack.push(ch);
                }else{
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    oper = operStack.pop();
                    res = numStack.cal(num1, num2, oper);
                    numStack.push(res);
                    operStack.push(ch);
                }
            }else{
                // 如果发现数直接入栈，但是要小心多位数的问题
                StringBuilder sum = new StringBuilder(ch + "");
                while(index != expression.length() && !operStack.isOper(expression.charAt(index))){
                    sum.append(expression.charAt(index));
                    index++;
                }
                numStack.push(Integer.parseInt(sum.toString()));
            }
            if(index == expression.length()){
                break;
            }
        }
        while (!operStack.isEmpty()){
            oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            numStack.push(numStack.cal(num1,num2,oper));
        }
        numStack.show();
    }
}

class ArrayStackTow{

    // 栈顶,初始化为-1
    int top = -1;
    // 表示栈的大小
    int maxSize;
    // 存放栈元素数组
    int[] array;

    public ArrayStackTow(int maxSize){
        this.maxSize = maxSize;
        array = new int[this.maxSize];
    }

    // 出栈
    public int pop(){
        if(!isEmpty()){
            return array[top--];
        }else{
            throw new RuntimeException("栈空了不可出栈");
        }
    }

    // 入栈
    public void push(int value){
        if(isFull()){
            throw new RuntimeException("栈已满，不可入栈");
        }else{
            array[++top] = value;
        }
    }

    // 判断是否栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    // 判断是否栈空
    public boolean isEmpty(){
        return top == -1;
    }

    public void show(){
        for(int i = top;i > -1;i--){
            System.out.println(array[i] + "\t");
        }
    }

    // 只查看栈顶元素，而不pop
    public int peek(){
        return array[top];
    }

    // 判断运算符的优先级，返回值越大表示此运算符优先级越高
    public int priority(int oper){
        return switch (oper){
            case '*', '/' -> 1;
            case '+', '-' -> 0;
            default -> -1;
        };
    }

    // 判断char是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/' || val == '(' || val == ')';
    }

    // 计算方法
    public int cal(int num1,int num2,int oper){
        return switch (oper){
            case '+' -> num2 + num1;
            case '-' -> num2 - num1;
            case '*' -> num2 * num1;
            case '/' -> num2 / num1;
            default -> -1;
        };
    }
}