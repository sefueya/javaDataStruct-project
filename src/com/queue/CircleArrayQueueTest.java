package com.queue;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * ClassName: CircleArrayQueueTest
 * Package: com.queue
 * Description:
 *
 * @Author sefue
 * @Create 2024/7/28 11:34
 * @Version 1.0
 */
public class CircleArrayQueueTest {
    @Test
    public void test(){
        CircleArrayQueue queue = new CircleArrayQueue(4);  // 说明：这里设置的4，其队列只能放3个，因为空出了一个空间做为约定

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
            System.out.println("hello");

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

class CircleArrayQueue {
    private int maxSize;  // 表示数组的最大容量
    private int front;  // 队列头
    private int rear;   // 队列尾
    private int[] arr; // 该数组用于存放数据，模拟队列

    // 创建队列的构造器
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }
    public boolean isFull(){

        return rear % maxSize == front;

    }

    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        arr[rear] = data;
        // 将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    // 获取队列的数据，出队列
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        // 这里分析不能直接return 要保留到一个临时变量，这样才可以先front++然后再return
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的没有数据");
            return;
        }
        // 思路：从front开始遍历，遍历多少个元素？
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    // 求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据，注意不是取数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的没有数据");
        }
        return arr[front];
    }
}
