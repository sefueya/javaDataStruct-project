package com.sort;

import com.sort.util.Nums;

/**
 * ClassName: SelectSort
 * Package: com.sort
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/12 10:51
 * @Version 1.0
 */
public class SelectSort {
    public static void main(String[] args) {
        Nums.confuse();
        long start = System.currentTimeMillis();
        selectSort(Nums.array);
        long end = System.currentTimeMillis();
        System.out.println("一共用时:" + (end - start));
        Nums.show();
    }

    public static void selectSort(int[] arr){
        int minIndex = 0;
        for(int i = 0;i < arr.length;i++){
            minIndex = i;
            for(int j = i + 1;j < arr.length;j++){
                if(arr[minIndex] < arr[j]){
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}
