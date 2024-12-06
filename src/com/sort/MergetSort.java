package com.sort;

import com.sort.util.Nums;

/**
 * ClassName: MergetSort
 * Package: com.sort
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/13 12:00
 * @Version 1.0
 */
public class MergetSort {
    public static void main(String[] args) {
        Nums.confuse();
        long start = System.currentTimeMillis();
        int[] temp = new int[Nums.array.length];
        mergetSort(Nums.array,0,Nums.array.length -  1,temp);
        long end = System.currentTimeMillis();
        Nums.show();
        System.out.println("一共用时:" + (end - start));
    }


    /**
     *  合并的方法
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 代表中间索引，是左边有序序列的最右边，再加1就是右边序列的开始,mid定义的意义就是用于 +1 可以代表右边序列 的开始
     * @param right 这一段原始数组的段 的 最右边的索引
     * @param temp 中转数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int l = left;
        int r = mid + 1;
        int t = 0; // 代表temp数组的下标

        // 1.
        // 把两边的有序数组，按照规则填充到 temp数组
        // 直到左右两边的数组有一边空了
        while(l <= mid && r <= right){
            if(arr[l] <= arr[r]){
                temp[t++] = arr[l++];
            }else{
                temp[t++] = arr[r++];
            }
        }

        // 2.
        // 把有剩余数据的数组一次性填充到 temp数组
        while(l <= mid){
            temp[t++] = arr[l++];
        }
        while(r <= right){
            temp[t++] = arr[r++];
        }

        // 3.
        // 将temp数组拷贝到arr
        t = 0;
        l = left;
        while(l <= right){
            arr[l++] = temp[t++];
        }
    }

    // 分+合的方法
    public static void mergetSort(int[] arr,int left,int right,int[] temp){
        if(left < right){
            int mid = (left + right) / 2; // 代表中间索引，是左边有序序列的最右边，再加1就是右边序列的开始,mid定义的意义就是用于 +1 可以代表右边序列 的开始
            // 向左递归进行分解
            mergetSort(arr,left,mid,temp);
            // 向右递归进行分解
            mergetSort(arr,mid + 1,right,temp);
            // 开始合并
            merge(arr,left,mid,right,temp);
        }
    }
}
