package com.sort;

import com.sort.util.Nums;

/**
 * ClassName: BubbleSort
 * Package: com.sort
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/12 10:30
 * @Version 1.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        Nums.confuse();
        long start = System.currentTimeMillis();
        bubbleSort(Nums.array);
        long end = System.currentTimeMillis();
        System.out.println("一共用时:" + (end - start));
        Nums.show();
    }

    public static void bubbleSort(int[] arr){
        boolean flag = false;
        for(int i = 0;i < arr.length;i++){
            flag = true;
            for(int j = 0;j < arr.length - 1 - i;j++){
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            // 优化，如果一次循环中没有一次交换，那就说明已经 序列已经有序
            if(flag) return;
        }
    }
}
