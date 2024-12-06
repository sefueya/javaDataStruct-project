package com.linkedList;

import org.junit.jupiter.api.Test;

/**
 * ClassName: DoubleLinkedListTest
 * Package: com.linkedList
 * Description:
 *
 * @Author sefue
 * @Create 2024/7/28 17:33
 * @Version 1.0
 */
public class DoubleLinkedListTest {
    @Test
    public void test(){
        DoubleLinkedList list = new DoubleLinkedList();
        HeroNode2 node1 = new HeroNode2(11, "鲁智深", "花和尚");
        HeroNode2 node2 = new HeroNode2(5, "松江", "及时雨");
        HeroNode2 node3 = new HeroNode2(5, "宋江", "及时雨");
        HeroNode2 node4 = new HeroNode2(24, "武松", "行者");
        HeroNode2 node5 = new HeroNode2(55, "奥特曼", "迪迦");
        HeroNode2 node6 = new HeroNode2(2, "卢俊义", "玉麒麟");
        list.addById(node1);
        list.addById(node2);
        list.addById(node3);
        list.addById(node4);
        list.addById(node5);
        list.addById(node6);
        list.show();
        HeroNode2 node7 = new HeroNode2(55, "奥特曼", "泰罗");
        list.update(node7);
        System.out.println("修改后");
        list.show();
        list.deleteById(2);
        list.deleteById(55);
        System.out.println("删除后");
        list.show();
    }
}


// 创建一个双向链表的类
class DoubleLinkedList{
    // 初始化头结点
    private HeroNode2 head = new HeroNode2(0,"","");

    // 返回头结点
    public HeroNode2 getHead(){
        return head;
    }

    // 添加一个节点到双向节点的最后
    public void add(HeroNode2 node){
        HeroNode2 temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    // 添加节点到单向链表，根据编号大小添加
    public void addById(HeroNode2 node){
        HeroNode2 temp = head;

        boolean flag = false; // flag 标志添加的编号是否存在，默认为false
        while (true){
            if(temp.next == null){ // 位置找到
                break;
            }
            if(temp.next.no > node.no){ // 位置找到
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
            if(temp.next != null){
                temp.next.pre = node;
            }
            temp.next = node;
            node.pre = temp;
        }
    }

    // 删除一个节点
    public void deleteById(int no){
        if(head.next == null){
            System.out.println("链表为空，不能删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            // 注意这里要小心被删除的节点是最后一个节点,如果不加判断会出现空指针异常
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }

        }else{
            System.out.println("没有找到" + no + "节点，不能删除");
        }
    }

    // 修改一个节点的内容
    public void update(HeroNode2 node){
        if(head.next == null){
            System.out.println("链表为空，无法修改");
            return;
        }
        HeroNode2 temp = head.next;
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


    // 遍历双向链表
    // 显示链表，遍历
    public void show(){
        // 判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }


}
// 定义双向链表的Node类
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}