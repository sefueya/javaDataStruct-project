package com.linkedList;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * ClassName: SingleLinkedList
 * Package: com.linkedList
 * Description:
 *
 * @Author sefue
 * @Create 2024/7/28 14:48
 * @Version 1.0
 */
public class SingleLinkedListTest {
    @Test
    public void test(){
        SingleLinkedList list = new SingleLinkedList();
        SingleLinkedList listTwo = new SingleLinkedList();
        list.addById(new HeroNode(11,"鲁智深","花和尚"));
        list.addById(new HeroNode(5,"松江","及时雨"));
        list.addById(new HeroNode(5,"宋江","及时雨"));
        list.addById(new HeroNode(24,"武松","行者"));
        list.addById(new HeroNode(55,"奥特曼","迪迦"));
        list.addById(new HeroNode(2,"卢俊义","玉麒麟"));
        // 修改前
        System.out.println("修改前");
        list.show();
        list.update(new HeroNode(6,"宋江","及时雨"));
        list.update(new HeroNode(5,"宋江","及时雨"));
        list.deleteById(5);

        // 修改后
        System.out.println("修改后");
        list.show();

        //System.out.println(list.size());
        //list.getByK(1);
        // 反转后
        //System.out.println("反转后");
        //list.reverse();
        //list.show();

        // 反转后打印
        //System.out.println("反转后打印");
        //SingleLinkedList.reverPrint(list.getHead());

        // 合并
        listTwo.addById(new HeroNode(77,"路费","海贼王"));
        listTwo.addById(new HeroNode(88,"孙悟空","齐天大圣"));
        listTwo.addById(new HeroNode(25,"猪八戒","天蓬元帅"));
        System.out.println("合并后");
        SingleLinkedList.addList(list,listTwo);
        list.show();
    }
}

// 定义SingleLinkedList 链表
class SingleLinkedList{
    // 先初始化头结点
    private HeroNode ahead = new HeroNode(0,"","");

    // 添加节点到单向链表
    // 思路：当不考虑编号顺序时
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的next指向新的节点
    public void add(HeroNode node){
        HeroNode temp = ahead;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    // 添加节点到单向链表，根据编号大小添加
    public void addById(HeroNode node){
        HeroNode temp = ahead;

        boolean flag = false; // flag 标志添加的编号是否存在，默认为false
        while (true){
            if(temp.next == null){ // 位置找到
                break;
            }
            if(temp.next.no > node.no){
                break;
            }else if(temp.next.no == node.no){  // 说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            System.out.printf("编号%d已存在，不能加入\n",node.no);
        }else{
            node.next = temp.next;
            temp.next = node;
        }
    }

    // 删除一个节点的思路
    // 1. 先找到需要删除的这个节点的前一个节点temp
    // 2. temp.next = temp.next.next
    // 3. 被删除的节点，将不会有其他引用指向，会被垃圾回收机制回收
    public void deleteById(int no){
        HeroNode temp = ahead;
        if(temp.next == null){
            System.out.println("链表为空，不能删除");
            return;
        }
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.println("没有找到" + no + "节点，不能删除");
        }
    }

    // 修改节点的信息，根据no编号来修改，no不能改
    public void update(HeroNode node){
        if(ahead.next == null){
            System.out.println("链表为空，无法修改");
            return;
        }
        HeroNode temp = ahead.next;
        boolean flag = false; // 表示是否找到节点
        while(true){
            if(temp == null){// 代表链表已经结束
                break;
            }
            if(temp.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = node.name;
            temp.nickname = node.nickname;
        }else{
            System.out.printf("没有找到编号为%d的节点\n",node.no);
        }
    }

    // 获取到单链表的节点的个数
    public int size(){
        if(ahead.next == null){
            return 0;
        }
        HeroNode temp = ahead.next;
        int size = 0;
        while(temp != null){
            size++;
            temp = temp.next;
        }
        return size;
    }


    // 查找单链表中的倒数第k个节点,返回值设置为void方便测试 [新浪面试题]
    public void getByK(int k){
        int length = size();
        if(length < k){
            System.out.println("链表长度不足k");
            return;
        }
        HeroNode temp = ahead;
        for(int i = 0;i <= length - k;i++){
            temp = temp.next;
        }
        System.out.println(temp);

    }

    // 单链表的反转 [腾讯面试题]
    public void reverse(){
        // 如果当前链表为空，或只有一个节点，无需反转，直接返回
        if(ahead.next == null || ahead.next.next == null) return;
        HeroNode reverseHead = new HeroNode(0,"","");
        while(true){
            HeroNode temp = ahead.next;
            if(temp == null){
                ahead.next = reverseHead.next;
                return;
            }
            ahead.next = temp.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
        }
    }

    // 从尾到头打印单链表[百度，要求方式：反向遍历，方式2：Stack栈]
    // 方式1：现将单链表进行反转操作，然后再遍历即可，这样的问题是会破坏原来的单链表的结构，不建议
    // 方式2：可以利用栈这个数据结构，将各个节点压入到栈中，利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reverPrint(HeroNode head){
        if(head.next == null){
            return;
        }

        // 创建一个栈，将各个节点压入栈 push
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        // 将栈中的节点进行打印 pop
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }

    }

    // 合并两个有序的单链表，合并之后依然有序
    public static void addList(SingleLinkedList listOne,SingleLinkedList listTwo){
        HeroNode temp = null;
        if(listOne.size() > listTwo.size()){
            temp = listTwo.getHead().next;
        }else{
            temp = listOne.getHead().next;
        }
        if(temp == null){
            return;
        }
        while(temp != null){
            listOne.addById(new HeroNode(temp.no,temp.name,temp.nickname));
            temp = temp.next;
        }





    }


    // 显示链表，遍历
    public void show(){
        // 判断链表是否为空
        if(ahead.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = ahead.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }


    public HeroNode getHead() {
        return ahead;
    }
}

// 定义一个Node节点，每一个Node对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickname='" + nickname + '\'' + '}';
    }
}