package com.search;

import javax.management.openmbean.CompositeType;
import java.util.Arrays;

/**
 * ClassName: SeqSearch
 * Package: com.search
 * Description:
 *
 * @Author sefue
 * @Create 2024/7/29 20:03
 * @Version 1.0
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
        int index = erFen(arr,89);
        if(index == -1){
            System.out.println("没找到");
        }else{
            System.out.println("下标为:" + index);
        }
    }

    public static int erFen(int[] arr,int target){
        int l = 0;
        int r = arr.length-1;
        while(true){
            int mid = (l + r) / 2;
            if(arr[mid] == target){
                return mid;
            }else if(target > arr[mid]){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
            if(r < l){
                return -1;
            }
        }
    }

    public static int seqSearch(int[] arr, int target) {
        for(int i =0;i < arr.length;i++) {
            if(arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void quickSort(int[] arr, int left, int right) {
        if(left <= right){
            int pivotIndex = partition(arr,left,right);
            quickSort(arr,left,pivotIndex - 1);
            quickSort(arr,pivotIndex + 1,right);
        }
    }

    public static int partition(int[] arr,int left,int right){
        int pivot = arr[left];
        int l = left + 1;
        int r = right;
        while(l <= r){
            while(l <= r && arr[l] <= pivot){
                l++;
            }
            while(l <=r && arr[r] >= pivot){
                r--;
            }
            if(l <= r ){
                swap(arr,l,r);
            }
        }
        swap(arr,left,r);
        return r;
    }
    public static void swap(int[] arr,int left,int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

    }
}
