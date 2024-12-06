package com.sort;

import com.sort.util.Nums;

/**
 * ClassName: QuickSort
 * Package: com.sort
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/13 10:55
 * @Version 1.0
 */
public class QuickSort {

    public static void main(String[] args) {
        Nums.confuse();
        long start = System.currentTimeMillis();
        quickSort(Nums.array,0, Nums.array.length - 1);
        long end = System.currentTimeMillis();
        //Nums.show();
        System.out.println("一共用时:" + (end - start));
    }

    public static void quickSort(int[] arr,int left,int right){
        if(left <= right){
            int position = partition(arr,left,right);
            quickSort(arr,left, position - 1);
            quickSort(arr,position + 1,right);
        }



    }

    public static int partition(int[] arr,int left,int right){
        int l = left + 1;
        int r = right;
        while(l <= r){
            while(l <= r && arr[l] <= arr[left] ){
                l++;
            }

            while(l <= r && arr[r] >= arr[left]  ){
                r--;
            }
            if(l <= r){
                swap(arr,l,r);
            }
        }
        swap(arr,left,r) ;
        return r;
    }

    public static void swap(int[] arr,int left,int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

}
