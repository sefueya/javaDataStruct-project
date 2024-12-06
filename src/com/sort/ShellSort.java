package com.sort;

import com.sort.util.Nums;

/**
 * ClassName: ShellSort
 * Package: com.sort
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/12 12:02
 * @Version 1.0
 */
public class ShellSort {
    public static void main(String[] args) {
        Nums.confuse();
        long start = System.currentTimeMillis();
        shellSort(Nums.array);
        long end = System.currentTimeMillis();
        //Nums.show();
        System.out.println("一共用时:" + (end - start));
    }

    public static void shellSort(int[] arr){
        for(int step = arr.length / 2;step > 0;step /= 2){
            for(int i = step;i < arr.length;i++){
                int j = i - step;
                int insertVal = arr[i];
                while(j >= 0 && arr[j] > insertVal){
                    arr[j + step] = arr[j];
                    j -= step;
                }
                if(arr[j + step] != insertVal){
                    arr[j + step] = insertVal;
                }
            }
        }
    }
}
