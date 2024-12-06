package com.hashtab;

import java.util.Scanner;

/**
 * ClassName: HashTabDemo
 * Package: com.hashtab
 * Description:
 --
 * @Author sefue
 * @Create 2024/11/15 11:04
 * @Version 1.0
 */
public class HashTabDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTab = new HashTab(10);
        String key = "";
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("add:添加雇员");
            System.out.println("list:展示哈希表数据");
            System.out.println("find:查找雇员信息");
            System.out.println("del:删除雇员信息");
            System.out.println("update:更改雇员信息");
            System.out.println("exit:退出系统");
            key = sc.next();
            switch (key){
                case "add" ->{
                    System.out.println("输入id:");
                    int id = sc.nextInt();
                    System.out.println("输入名字：");
                    String name = sc.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                }
                case "list" -> hashTab.list();
                case "find" ->{
                    System.out.println("请输入id查找:");
                    int id = sc.nextInt();
                    hashTab.findEmpById(id);
                }
                case "del" ->{
                    System.out.println("请输入删除雇员id:");
                    int id = sc.nextInt();
                    hashTab.deleteById(id);
                }
                case "update" ->{
                    System.out.println("请输入要更改的雇员id：");
                    int id = sc.nextInt();
                    System.out.println("请输入更改的名称:");
                    String name = sc.next();
                    hashTab.update(id,name);
                }
                case "exit" -> {
                    sc.close();
                    System.exit(0);
                }
            }
        }


    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

// 创建EmpLinkedList，表示一条链表
class EmpLinkedList{
    // 头指针，指向链表中第一个雇员
    private Emp head;

    // 添加雇员到链表
    // 说明: 假定，当添加雇员时，id是自增长，即 增加雇员的时候，id一定是从小到大的
    // 所以 加雇员的时候直接加到链表最后即可
    public void add(Emp emp){
        if(head == null){
            head = emp;
            return;
        }
        Emp temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = emp;
        return;
    }

    // 遍历链表中 雇员信息
    public void list(int no){
        if(head == null){
            System.out.println("第" + (no+1) + "条链表为空");
            return;
        }
        System.out.print("第" + (no+1) + "当前链表的信息为:");
        Emp temp = head;
        while(temp != null){
            System.out.printf(" id = %d name=%s\t",temp.id,temp.name);
            temp = temp.next;
        }
        System.out.println();
    }

    // 根据id查找雇员
    public Emp findEmpById(int id){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }
        Emp temp = head;
        while(temp != null){
            if(temp.id == id){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // 根据id删除雇员
    public void deleteById(int id){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        Emp temp = head;
        if(temp.id == id){
            head = temp.next;
            return;
        }
        while(temp.next != null){
            if(temp.next.id == id){
                break;
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return;
    }

    public void updateById(int id, String name) {
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        Emp temp = head;
        while(temp != null){
            if(temp.id == id){
                temp.name = name;
                System.out.println("修改成功");
                return;
            }
            temp = temp.next;
        }
        System.out.println("未找到该雇员,修改失败");

    }
}


// 创建HashTab，代表哈希表，管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;
    // 构造器
    public HashTab(int size){
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        // 不要忘了给数组中的每一个 元素 初始化，否则会报空指针异常
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 编写 散列函数，使用一个 简单的取模法，确定插入的位置
    public int hashFun(int id){

        return id % size;
    }
    // 添加雇员
    public void add(Emp emp){
        // 根据员工id，得到该员工应当加入到 数组中 的 哪个位置， 即 哪个链表中
        int empListListNo = hashFun(emp.id);
        // 根据位置，加入链表
        empLinkedListArray[empListListNo].add(emp);

    }

    // 根据id查找雇员
    public void findEmpById(int id){
        // 找到这个 id 应该放在数组中的哪个位置
        int no = hashFun(id);
        Emp emp = empLinkedListArray[no].findEmpById(id);
        if(emp == null){
            System.out.println("没有找到id为:" + id + "的雇员");
        }else{
            System.out.println("在第" + no + "条链表找到信息为 id=" + emp.id +"  name=" + emp.name +" 的雇员 ");
        }
    }

    // 根据id删除雇员
    public void deleteById(int id){
        int no = hashFun(id);
        empLinkedListArray[no].deleteById(id);
    }

    // 根据id更改姓名
    public void update(int id, String name) {
        int no = hashFun(id);
        empLinkedListArray[no].updateById(id,name);
    }

    // 遍历哈希表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

}
