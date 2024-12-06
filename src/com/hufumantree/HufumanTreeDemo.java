package com.hufumantree;

import java.util.Collections;
import java.util.LinkedList;

/**
 * ClassName: HufumanTreeDemo
 * Package: com.hufumantree
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/23 10:23
 * @Version 1.0
 */
public class HufumanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        root.preOrder();
    }
    // 通过数组构建哈夫曼树
    public static Node createHuffmanTree(int[] arr){
        // 用链表接收Node对象，方便后续从头插入
        LinkedList<Node> nodes = new LinkedList<>();
        for(int v:arr){
            nodes.add(new Node(v));
        }


        // 构建赫夫曼树
        while(nodes.size() != 1){
            // 排序
            Collections.sort(nodes);

            // 取出两个最小的，即删除头结点并接收头结点给 left 和 right
            Node left = nodes.removeFirst();
            Node right = nodes.removeFirst();

            // 构建一颗新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            // 把 新的二叉树 放进 链表
            nodes.add(0,parent);
        }
        return nodes.removeFirst();
    }

}

// 创建结点类,并实现排序接口，方便后续调用方法排序
class Node implements Comparable<Node>{
    int value; // 权值
    Node left;
    Node right;

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    // 前序遍历
    public void preOrder(){
        System.out.println(this.value);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }

    }
}
