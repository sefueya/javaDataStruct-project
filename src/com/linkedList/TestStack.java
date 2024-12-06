package com.linkedList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.Stack;

/**
 * ClassName: TestStack
 * Package: com.linkedList
 * Description:
 *
 * @Author sefue
 * @Create 2024/7/28 16:21
 * @Version 1.0
 */
public class TestStack {
    @Test
    public void test(){
        Stack<String> stack = new Stack();
        // 入栈
        stack.add("javaSE");
        stack.add("MySQL");
        stack.add("javaWeb");
        // 出栈
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
