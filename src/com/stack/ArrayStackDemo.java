package com.stack;

import java.util.Scanner;

/**
 * ClassName: ArrayStackDemo
 * Package: com.stack
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/9 10:19
 * @Version 1.0
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner sc = new Scanner(System.in);
        while(loop){
            System.out.println("show表示显示栈");
            System.out.println("exit表示退出栈");
            System.out.println("push表示入栈");
            System.out.println("pop表示入栈");
            System.out.println("请输入你的选择");
            key = sc.next();
            switch(key){
                case "show" -> stack.show();
                case "pop" -> System.out.println("出栈的数据是:" + stack.pop());
                case "push" -> {
                    System.out.println("请输入一个数：");
                    int value = sc.nextInt();
                    stack.push(value);
                }
                case "exit" ->{
                    sc.close();
                    loop = false;
                    System.out.println("程序退出");
                }
                default -> loop = false;
            }
        }
    }
}

class ArrayStack{

    // 栈顶,初始化为-1
    int top = -1;
    // 表示栈的大小
    int maxSize;
    // 存放栈元素数组
    int[] array;

    public ArrayStack(int maxSize){
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
}
