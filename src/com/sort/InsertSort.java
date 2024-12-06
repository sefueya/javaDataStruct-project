package com.sort;

import com.sort.util.Nums;

/**
 * ClassName: InsertSort
 * Package: com.sort
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/12 10:58
 * @Version 1.0
 */
public class InsertSort {
    public static void main(String[] args) {
        Nums.confuse();
        long start = System.currentTimeMillis();
        insertSort(Nums.array);
        long end = System.currentTimeMillis();
        Nums.show();
        System.out.println("一共用时:" + (end - start));
    }

    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > insertVal){
                arr[j + 1] = arr[j];
                j--;
            }
            // 当i的值就是比前面所有序列的值大时那i就不用自己插自己了，此时 j + 1 是等于 i的，没必要在自己给自己赋值
            if(j + 1 != i){
                arr[j + 1] = insertVal;
            }
        }
    }

}
