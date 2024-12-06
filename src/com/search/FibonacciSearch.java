package com.search;

import java.util.Arrays;

/**
 * ClassName: FibonacciSearch
 * Package: com.search
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/14 11:02
 * @Version 1.0
 */
public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        System.out.println(fibSearch(arr, 1));
    }

    // 首先需要一个斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    // 斐波那契查找方法
    public static int fibSearch(int[] arr,int key){
        int left = 0;
        int right = arr.length - 1;
        int k = 0; // 表示斐波那契数组的下标
        int mid = 0;
        int[] f = fib();
        // 获取到斐波那契分割数组的下标，根据数组右边下标 right 对应的 斐波那契数组中的值，得到此时斐波那契数组这个数 对应的下标
        while(right > f[k] - 1){
            k++;
        }
        // 因为 f[k] 可能不会直接等于 数组右边下标 right,f[k]很可能大于 数组 right，因此需要给数组arr扩充，使它的长度 即 right更大直到等于 f[k]
        int[] temp = Arrays.copyOf(arr,f[k]); // f[k] 代表新数组的 长度 newLength

        // arr扩充时 右边新的位置以 right位置的值填充
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        while(left <= right){
            mid = left + f[k - 1] - 1;
            if(key < temp[mid]){
                right = mid - 1;
                k--;
            }else if(key > temp[mid]){
                left = mid +1;
                // 为什么是 k-2? 可看 斐波那契数组 {1,1,2,3,5,8,13,21,34,55} ，假设数组长度是 8此时 f[k] = 8,k = 5,此时根据f[k] = 8将数组分为 5 + 3，数组右边长度就是3
                // 此时key大于mid上的值，就应该往右边找，而 右边子数组 根据{1,1,2,3,5,8}的黄金分割点应该就是 2
                // 以为：数组长度为8时分割点就是 长度为5，那么数组长度为3时 分割点就是 长度为2
                // 2在斐波那契数组上的下标就是 注意 此时 mid = left + f[k - 1] - 1，也就是说 f - 1 = 4对应斐波那契数组中的 8，而2 在斐波那契数组中 8的位置 - 2所以是 k-2
                k -= 2;
            }else{
                // 查找到以后不能轻易的直接 返回 mid下标了，因为有可能 mid的值是 arr右边扩充的某个下标，而扩充的下标上的值都是 right下标上的值，应当返回right ，如果返回mid就导致这个下标在arr原数组上找不到
                if(mid > right){
                    return right;
                }else{
                    return mid;
                }
            }
        }
        return -1;
    }
}
