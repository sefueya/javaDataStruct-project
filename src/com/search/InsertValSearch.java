package com.search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * ClassName: InsertValSearch
 * Package: com.search
 * Description:
 *
 * @Author sefue
 * @Create 2024/7/29 21:26
 * @Version 1.0
 */
public class InsertValSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
        int index = insertValSearch(arr, 0, arr.length - 1, 100);
        if(index == -1){
            System.out.println("没找到");
        }else {
            System.out.println("下标为:" + index);
        }
    }
    @Test
    public void test(){
        
    }
    // 插值查找算法
    public static int insertValSearch(int[] arr, int left, int right, int target) {
        System.out.println("hello");
        // 注意: 相较于二分查找，target < arr[0] || target > arr[arr.length - 1]是必须的
        // 否则得到的mid可能会越界
        if(left > right || target < arr[0] || target > arr[arr.length - 1]){
            return -1;
        }

        // 求出mid
        int mid = left + (target - arr[left])/(arr[right] - arr[left])*(right - left);
        if(arr[mid] == target){
            return mid;
        }else if(target > arr[mid]){
            return insertValSearch(arr,left + 1,right,target);
        }else{
            return insertValSearch(arr,left,right - 1,target);
        }
    }


}
