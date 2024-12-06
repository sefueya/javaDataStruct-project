package com.dac;

/**
 * ClassName: Hanoitower
 * Package: com.dac
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/4 9:58
 * @Version 1.0
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoi(3,'F','A','T');
    }

    // 汉诺塔的移动方法
    // F,A,T 分别对应 第一个柱子 第二个柱子 第三个柱子
    public static void hanoi(int n,char F,char A,char T){
        // 从上到下是第1个盘...第n个盘
        if(n == 1){
            System.out.printf("move 第%d个盘 from %c To %c\n",n,F,T);
            return;
        }
        hanoi(n-1,F,T,A);
        System.out.printf("move 第%d个盘 from %c To %c\n",n,F,T);
        hanoi(n-1,A,F,T);
    }
}
