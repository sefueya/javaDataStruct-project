package com.linkedList;

import org.junit.jupiter.api.Test;

/**
 * ClassName: Josepfu
 * Package: com.linkedList
 * Description:
 *
 * @Author sefue
 * @Create 2024/7/28 18:59
 * @Version 1.0
 */
public class Josepfu {
    @Test
    public void test(){
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addNode(5);
        list.show();
        // 测试小孩出圈是否正确
        list.countBoy(1,2,5);
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList{
    // 创建一个first节点，当前没有编号
    private Node first = null;
    // 根据给定的小孩数量构建
    public void addNode(int num){
        if(num < 2){
            System.out.println("num的值不正确");
            return;
        }
        Node temp = null;  // 辅助指针，帮助构建环形链表
        for(int i = 1;i <= num;i++){
            // 根据编号创建小孩节点
            Node boy = new Node(i);
            if(i == 1){
                first = boy;
                first.next = first;
                temp = first;
            }else{
                temp.next = boy;
                boy.next = first;
                temp = boy;
            }
        }
    }

    // 遍历当前环形链表
    public void show(){
        if(first == null){
            System.out.println("链表空，无法打印");
        }
        Node temp = first;
        while(true){
            System.out.println(temp);
            if(temp.next == first){ // 说明遍历完毕
                break;
            }
            temp = temp.next;
        }
    }

    // 根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums  表示最初由多少个小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        // 先对数据进行校验
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }

        // 创建一个辅助指针，帮助完成小孩出圈,这个指针指向最后一个节点
        Node temp = first;
        while(true){
            if(temp.next == first){
                break;
            }
            temp = temp.next;
        }
        // 当小孩报数前，先让first和helper移动k-1次
        for(int j = 0;j < startNo - 1;j++){
            first = first.next;
            temp = temp.next;
        }
        // 当小孩报数时，让first和helper移动m-1次，然后出圈
        while(true){
            if(temp == first){ // 说明圈中只有一个节点
                break;
            }
            for(int i = 0;i < countNum - 1;i++){
                first = first.next;
                temp = temp.next;
            }
            System.out.printf("小孩%d出圈\n",first.no);
            first = first.next;
            temp.next = first;
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.no);
    }

}

// 创建一个Node类，表示一个节点
class Node{
    int no; // 编号
    Node next;
    public Node(int no){
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}
