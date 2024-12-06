package com.tree;

/**
 * ClassName: ArrBinaryTreeDemo
 * Package: com.tree
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/20 10:18
 * @Version 1.0
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        // 测试
        //tree.preOrder(0); // 1 2 4 5 3 6 7
        tree.infixOrder(0); // 4 2 5 1 6 3 7
        tree.postOrder(0);  // 4 5 2 6 7 3 1
    }
}


// 编写一个ArrBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree{
    private int[] arr; // 存储二叉树结点的数组
    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }

    // 前序遍历
    //index 代表数组的下标,用于在数组中递归每个位置
    public void preOrder(int index){
        // 如果数组为空，或者arr.length = 0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能前序遍历");
        }

        // 打印当前元素
        System.out.println(arr[index]);
        // 向左递归
        if((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        // 向右递归
        if((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }

    // 中序遍历
    public void infixOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("树为空，不能中序遍历");
            return;
        }
        // 向左递归
        if((index * 2 + 1) < arr.length){
            infixOrder(index * 2 + 1);
        }

        // 打印当前元素
        System.out.println(arr[index]);

        // 向右递归
        if((index * 2 + 2) < arr.length){
            infixOrder(index * 2 + 2);
        }
    }

    // 后序递归
    public void postOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("树为空，不能后序遍历");
            return;
        }

        // 向左遍历
        if((index * 2 + 1) < arr.length){
            postOrder(index * 2 + 1);
        }

        // 向右遍历
        if((index * 2 + 2) < arr.length){
            postOrder(index * 2 + 2);
        }
        // 打印当前元素
        System.out.println(arr[index]);
    }
}


