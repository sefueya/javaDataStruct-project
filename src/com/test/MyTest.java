package com.test;

import org.junit.jupiter.api.Test;

/**
 * ClassName: MyTest
 * Package: com.test
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/4 11:00
 * @Version 1.0
 */
public class MyTest {
    @Test
    public void test(){
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";

        String str2 = "尚硅谷你尚硅你";
        System.out.println(str1.indexOf(str2));
        int i = 0;
        int j = 0;


        while (i < str1.length() && j < str2.length()){
            if(str1.charAt(i) == str2.charAt(j)){
                i++;
                j++;
            }else{
                i = i - (j - 1);
                j = 0;
            }
        }

        if(j == str2.length()){
            i -= j;
        }

        System.out.println(i);
    }

}
