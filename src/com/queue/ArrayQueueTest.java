package com.queue;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * ClassName: ArrayQueue
 * Package: com.queue
 * Description:
 *
 * @Author sefue
 * @Create 2024/7/28 10:52
 * @Version 1.0
 */
public class ArrayQueueTest {
    @Test
    public void test1() {
        ArrayQueue queue = new ArrayQueue(3);

        char key = ' '; // 接收用户输入
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = sc.next().charAt(0); // 接收一个字符

            switch (key) {
                case 's' -> queue.showQueue();
                case 'a' -> {
                    System.out.println("输入一个数");
                    int value = sc.nextInt();
                    try {
                        queue.addQueue(value);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 'g' -> System.out.println("取出的数据是" + queue.getQueue());
                case 'h' -> System.out.println("队列头的数据是" + queue.getQueue());
                case 'e' -> {
                    sc.close();
                    loop = false;
                }
                default -> loop = false;
            }
        }
        System.out.println("程序退出");


    }
}


// 使用数组模拟队列编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize;  // 表示数组的最大容量
    private int front;  // 队列头
    private int rear;   // 队列尾
    private int[] arr; // 该数组用于存放数据，模拟队列

    // 创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 注意：front指向的是头部元素的前一个位置，而不是头部元素
        rear = -1;  // 注意：rear指向的是尾部元素的前一个位置，而不是头部元素
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列是否空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        arr[++rear] = data; // 让rear后移
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常处理
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[++front]; // 让front后移
    }

    // 显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "\t");
        }
    }

    // 显示队列的头数据，注意不是取数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的没有数据");
        }
        return arr[front + 1];
    }
}
