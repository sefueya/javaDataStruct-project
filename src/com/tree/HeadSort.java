package com.tree;

/**
 * ClassName: HeadSort
 * Package: com.tree
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/22 12:10
 * @Version 1.0
 */
public class HeadSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9,-1,90,89,56,-999};
        headSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void headSort(int[] arr){

        // 第一轮构建堆，注意理解这个 i--
        for(int i = arr.length / 2 - 1;i >= 0;i--){
            adjustHeap(arr,i,arr.length);
        }

        // 第二轮，交换堆顶和末尾，并且构建堆，注意此时 构建堆 直接从 堆顶 开始，因为交换完之后堆顶是最下面的那一批元素值
        // 将堆顶元素和末尾元素交换，然后重新调整 堆结构
        for(int i = arr.length - 1;i > 0;i--){
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }

    }

    // 该方法将以i为父节点的树调整为大顶堆
    // i代表非叶子节点在数组中的索引，length表示对多少个元素进行构建大顶堆（每一轮应该少一个元素进行构建）
    public static void adjustHeap(int[] arr,int i,int length){

        for(int j = i * 2 + 1;j < length;j = j * 2 + 1){
            if(j + 1 < length && arr[j] < arr[j + 1]){
                j++;
            }
            if(arr[j] <= arr[i]){
                break;
            }else{
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i = j;
            }
        }
    }
}
