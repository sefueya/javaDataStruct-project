package com.sort.util;

/**
 * ClassName: Nums
 * Package: com.sort.util
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/12 10:31
 * @Version 1.0
 */
public class Nums {
    public static int[] array;
    static int max = 10000000;
    static{
        array = new int[max];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * max);
        }
    }
    public static void show(){
        for (int i : array) {
            System.out.print(i + "  ");
        }
    }



    public static void confuse(){
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*max);
        }
    }
}
