package com.search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ClassName: BinarySearch
 * Package: com.search
 * Description:
 *
 * @Author sefue
 * @Create 2024/7/29 20:22
 * @Version 1.0
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1000,1234};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
        ArrayList<Integer> list = binarySearch2(arr,0,arr.length-1, 999);
        if(list != null){
            for (int i = 0;i < list.size();i++) {
                System.out.println(arr[list.get(i)]+ "下标为:" + list.get(i));
            }
        }else{
            System.out.println("没找到");
        }
    }

    /**
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param target 要查找的值
     * @return 如果找到就返回下标，没找到就返回-1
     * 注意：只能找到一个与target相同的值，如果查找1000，而数组中有两个1000则没办法全部输出
     */
    // 二分查找法
    public static int binarySearch(int[] arr,int left,int right,int target){
        // 往左一直没找到right会变成负数 此时right<left
        // 往右一直没找到left会等于right,此时right<left
        if(right < left){
            return -1;
        }
        int mid = (left + right) / 2;
        if(arr[mid] == target){
            return mid;
        }else if(target > arr[mid]){
            left = mid + 1;
            return binarySearch(arr,left,right,target);
        }else{
            right = mid - 1;
            return binarySearch(arr,left,right,target);
        }
    }

    // 二分查找法改进
    public static ArrayList<Integer> binarySearch2(int[] arr,int left,int right,int target){
        // 往左一直没找到right会变成负数 此时right<left
        // 往右一直没找到left会等于right,此时right<left
        if(right < left){
            return null;
        }
        int mid = (left + right) / 2;
        if(arr[mid] == target){
            // 在找到mid值，不要马上返回，向mid左边扫描，将所有满足1000的元素的下标，加入集合ArrayList
            // 并向右边扫描，将所有满足1000的元素的下标，加入集合ArrayList
            ArrayList<Integer> list = new ArrayList<>();
            list.add(mid);
            int temp = mid - 1;
            while(true){
                // 第二个条件要退出循环的原因是：数组是按大小排列的 假设 1000左边不是1000了那就说明再左边也不会有1000了
                if(temp < 0 || arr[temp] != target){
                    break;
                }
                list.add(temp);
                temp--;
            }
            temp = mid + 1;
            while(true){
                if(temp >= arr.length || arr[temp] != target){
                    break;
                }
                list.add(temp);
                temp++;
            }
            list.sort(Integer::compareTo);
            return list;
        }else if(target > arr[mid]){
            left = mid + 1;
            return binarySearch2(arr,left,right,target);
        }else{
            right = mid - 1;
            return binarySearch2(arr,left,right,target);
        }
    }


    public static void radixSort(int[] arr){
        int max = arr[0];
        for(int i = 1;i < arr.length;i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        int[][] buckets = new int[10][arr.length];
        int[] bucketCount = new int[10];

        for(int n = 0,temp = 1;n < maxLength;n++,temp *= 10){
            for(int i = 0;i < arr.length;i++){
                int digit = arr[i] / temp % 10;
                buckets[digit][bucketCount[digit]++] = arr[i];
            }
            int index = 0;
            for(int i = 0;i < bucketCount.length;i++){
                if(bucketCount[i] != 0){
                    for(int j = 0;j < bucketCount[i];j++){
                        arr[index++] = buckets[i][j];
                    }
                }
                bucketCount[i] = 0;
            }
        }
    }
}
