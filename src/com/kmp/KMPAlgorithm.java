package com.kmp;

/**
 * ClassName: KMPAlgorithm
 * Package: com.kmp
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/4 11:42
 * @Version 1.0
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";

        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);

        System.out.println(kmpSearch(str1, str2, next));



    }

    // 获取到一个子串的部分匹配值
    public static int[] kmpNext(String dest){
        // 创建一个next数组 保存 部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; // 如果字符串长度是1，部分匹配值是1
        for(int i = 1,j = 0;i < dest.length();i++){

            // 当dest.charAt(i) != dest.charAt(j)，需要从next[j - 1]获取新的j，直到发现有 dest.chatAt(i) == dest.charAt(j) 成立才退出
            while(j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }


            // 这个条件满足时，部分匹配值就需要加1
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int kmpSearch(String str1,String str2,int[] next){
        for(int i = 0,j = 0;i < str1.length();i++){
            while(j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }
}
