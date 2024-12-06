package com.recursion;

/**
 * ClassName: Queue8
 * Package: com.recursion
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/11 11:14
 * @Version 1.0
 */
public class Queue8 {
    static int max = 8;
    static int[] array = new int[max];
    static int count = 1;
    public static void main(String[] args) {
        Queue8.check(0);
        Queue8.show();
    }

    // 用于放置第n个皇后，使用回溯
    public static void check(int n){
        // 如果放够8个，打印出当前解法，然后 继续返回上一级 i继续变化放第8个皇后
        // 一直到第8个皇后的当其余7个不变的情况下的 所有解法放完，回到放 第7个皇后 i变化
        // 一直以此循环到 重新第1个皇后的位置i变化，将所有解法解完才会停止递归
        if(n == max){ // 防止 check(n + 1) n = 7时的 数组越界
            return;
        }

        // 回溯，没有放完的情况下接着往里走
        // 依次放入皇后，并判断是否冲突
        for(int i = 0;i < array.length;i++){
            array[n] = i;
            // 如果放置的合法，那么接着放下一个，如果不合法那么不走if内的，下一轮循环会覆盖掉上一轮不合法的array[n] 并且尝试 +1后的位置能不能放置一直尝试完总共8个位置最终一定能找到一个位置
            if(isOK(n)){
                check(n + 1);
            }
            if(isOK(7)){
                return;
            }
        }

    }

    // 查看第n个皇后，是否与已经摆放的皇后冲突
    private static boolean isOK(int n){

        for (int i = 0; i < n; i++) {
            // 同一列 || 第n个皇后 和 第i个皇后 在同一斜线,abs()表示取绝对值
            if(array[i] == array[n] || (n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    public static void show(){
        System.out.println("第" + count++ +"种解法:");
        for (int h : array) {
            for (int i = 0; i < h; i++) {
                System.out.print(" ");
            }
            System.out.print(h);
            System.out.println();
        }
    }
}