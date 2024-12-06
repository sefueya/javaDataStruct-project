package com.recursion;

/**
 * ClassName: Stair
 * Package: com.recursion
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/19 9:07
 * @Version 1.0
 */
public class Stair {
    static int count = 0;
    public static void main(String[] args) {
        stairMethod(8,0);
        System.out.println(count);
    }

    public static void stairMethod(int n,int start){

        if(start == n){
            count++;
            return;
        }

        if(n - start > 1){
            stairMethod(n,start + 1);
            stairMethod(n,start + 2);
        }else{
            stairMethod(n,start + 1);
        }
    }
}
