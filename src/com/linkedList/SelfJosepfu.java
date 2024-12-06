package com.linkedList;

/**
 * ClassName: SelfJosepfu
 * Package: com.linkedList
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/8 11:45
 * @Version 1.0
 */
public class SelfJosepfu {
    public static void main(String[] args) {
        CircleLinkedList list = new CircleLinkedList();
        list.chuQuan(2,2,5);
    }
}

class CircleLinkedList{
    LinkedNode first = null;
    public void addNode(int num){
        if(num < 1){
            System.out.println("num值不正确");
            return;
        }
        LinkedNode temp = null;
        for(int i = 1;i <= num;i++){
            LinkedNode newNode = new LinkedNode(i);
            if(first == null){
                first = newNode;
                newNode.next = first;
                temp = first;
            }else{
                temp.next = newNode;
                newNode.next = first;
                temp = newNode;
            }
        }
    }

    public void show(){
        if(first == null){
            System.out.println("没有任何结点");
            return;
        }
        LinkedNode temp = first;
        while(true){
            System.out.println(temp);
            if(temp.next == first){
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * @param startId 表示从第几个小孩开始数
     * @param countNum 表示一次数几下
     * @param num 表示一共有多少个小孩
     */
    public void chuQuan(int startId,int countNum,int num){

        // 数据校验
        if(startId < 1 || startId > num){
            System.out.println("出圈设计有误");
            return;
        }

        this.addNode(num);
        // 出圈使用first移动，first每一轮移动到的位置就是该删除的节点
        // 用一个辅助指针标记first的前一个位置，用于删除节点
        LinkedNode temp = first;
        while (temp.next != first){
            temp = temp.next;
        }
        // 让first指向开始游戏的位置
        for(int i = 1;i < startId;i++){
            first = first.next;
            temp = temp.next;
        }
        // 当first指针和temp相等时代表此时就剩一个小孩，出循环
        while(first != temp){
            for(int i = 1;i < countNum;i++){
                first = first.next;
                temp = temp.next;
            }
            System.out.println(first + "出圈了");
            temp.next = first.next;
            first = temp.next;
        }
        System.out.println("最后一个小孩是:" + first);
    }
}

class LinkedNode{
    int id;
    LinkedNode next;
    public LinkedNode(int id){
        this.id = id;
    }
    @Override
    public String toString(){
        return "结点id为:" + id;
    }
}