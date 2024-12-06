package com.avl;

/**
 * ClassName: AVLTreeDemo
 * Package: com.avl
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/1 10:06
 * @Version 1.0
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};  测试左旋转
        //int[] arr = {10,12,8,9,7,6}; 测试右旋转
        int[] arr = {10,11,7,6,8,9};

        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
        System.out.println(avlTree.getRoot().left);
        System.out.println(avlTree.getRoot().left.left);
        System.out.println(avlTree.getRoot().right);
        System.out.println(avlTree.getRoot().right.left);
        System.out.println(avlTree.getRoot().right.right);

    }
}


// AVL树类
class AVLTree{
    private Node root;

    // 添加
    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    // 中序遍历，降序
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }
    }

    // 降序遍历
    public void testOrder(){
        if(root != null){
            root.testOrder();
        }
    }

    // 查找结点
    public 
Node search(int value){
        if(root == null){
            return null;
        }else{
            return root.search(value);
        }
    }

    // 查找父节点
    public Node searchParent(int value){
        if(root == null || root.value == value){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    // 删除节点
    public void delNode(int value){
        if(root == null){
            return;
        }else{
            Node targetNode = search(value);
            if(targetNode == null){
                return;
            }
            // 如果发现这颗二叉排序树只有一个节点，即root节点
            if(root.left == null && root.right == null){
                root = null;
                return;
            }
            // 去找到targetNode的父节点
            Node parent = searchParent(value);

            // 如果parent为null，代表

            // 如果删除的结点是一个叶子结点
            if(targetNode.left == null && targetNode.right == null){
                // 判断targetNode是父节点的左 还是 右
                if(parent.left != null && parent.left.value == value){
                    parent.left = null;
                }else{
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){ // 如果删除的节点有两个节点
                targetNode.value = delRightTreeMin(targetNode.right);
            }else{// 如果删除的节点 有 一个节点 可能是 左 或 右
                if(targetNode.left != null){
                    if(parent == null){
                        root = targetNode.left;
                    }else if(parent.left.value == value){
                        parent.left = targetNode.left;
                    }else{
                        parent.right = targetNode.left;
                    }
                }else{
                    if(parent == null){
                        root = targetNode.right;
                    }else if(parent.left.value == value){
                        parent.left = targetNode.right;
                    }else{
                        parent.right = targetNode.right;
                    }
                }
            }
        }
    }

    // 返回以node为根结点的二叉排序树的最小结点的值 并且 删除 这个最小结点
    public int delRightTreeMin(Node node){
        Node target = node;

        // 循环查找左子结点，就会找到最小结点
        while(target.left != null){
            target = target.left;
        }
        int temp = target.value;

        delNode(target.value);
        return temp;
    }

    public Node getRoot(){
        return root;
    }
}


class Node{
    int value;
    Node left;
    Node right;
    public Node(int value){
        this.value = value;
    }


    // 左旋转方法,当添加结点方法触发时 最后判断 右子树的高度 - 左子树的高度 是否 不大于 1，如果不符合则调用这个左旋方法
    private void leftRotate(){
        // 1.根据根节点值创 一个 新节点
        Node newNode  = new Node(value);

        // 2.把新节点的 左子树 指向 根节点的左子树
        newNode.left = left;

        // 3.把新节点的 右子树 指向 根节点的 右子树的左子树
        newNode.right = right.left;

        // 4.把根节点的值 换成 根节点的右子树的值
        value = right.value;

        // 5.根节点 的左子树 指向 新节点
        left = newNode;

        // 6.根节点 的右子树 指向 右子树的右子树
        right = right.right;

    }

    //右旋转方法，添加结点方法 执行最后 判断一下 左子树的高度 - 右子树的 高度 是否 不大于 1，如果不符合 则 右旋转
    private void rightRotate(){

        // 1.以根节点的值，创建一个新节点
        Node newNode = new Node(value);

        // 2.让新节点的左子树 指向 根节点 的 左子树 的 右子树
        newNode.left = left.right;

        // 3.让新节点的右子树 指向 根节点的 右子树 的 右子树
        newNode.right = right;

        // 4.让根节点的值 改成 根节点的 左子树的值
        value = left.value;

        // 5.根节点的 左子树 指向 根节点的 左子树 的 左子树
        left = left.left;

        // 6.根节点的 右子树 指向 新节点
        right = newNode;

    }

    // 返回左子树的高度
    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }

    // 返回右子树的高度
    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }

    // 返回 以 该结点 为 根节点 的树的高度
    public int height(){

        return Math.max(left == null ?0 : left.height(), right == null?0 : right.height()) + 1;
    }



    // 添加结点的方法,递归，需要满足二叉排序树的要求
    public void add(Node node){
        if(node == null){
            return;
        }

        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }

        // 如果 右子树 - 左子树 高度 > 1 则 左旋转
        if(rightHeight() - leftHeight() > 1){
            // 又如果 当前结点的 右子树的左子树高度 大于 它的右子树的右子树 高度，那么先对 当前节点的 右子树 做 右旋转，再对当前节点做 左旋转
            if(right.leftHeight() > right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else{
                // 如果当前节点的 右子树 的 左子树高度 不大于 当前节点的 右子树的右子树 高度，那么直接 当前节点 做 左旋转
                leftRotate();
            }
            // 一定要加return
            return;
        }

        // 如果 左子树 - 右子树 高度 > 1 则 右旋转
        if(leftHeight() - rightHeight() > 1){

            // 又 如果 当前节点的 左子树 的 右子树高度 大于 当前节点的 左子树的左子树 高度，那么先对 当前节点的 左子树 做 左旋转,再对 当前节点 做 右旋转
            if(left.rightHeight() > left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }else{
                // 如果当前节点的 左子树 的 右子树高度 不大于 当前节点的 左子树的左子树高度，那么直接 当前节点 做 右旋转
                rightRotate();
            }
        }

    }

    // 中序遍历，升序
    public void infixOrder(){

        if(this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if(this.right != null){
            this.right.infixOrder();
        }
    }

    // 降序遍历
    public void testOrder(){
        if(this.right != null){
            this.right.testOrder();
        }
        System.out.println(this);
        if(this.left != null){
            this.left.testOrder();
        }
    }


    // 查找结点
    public Node search(int value){
        if(value == this.value){
            return this;
        }else if(value < this.value){ // 如果值小于当前节点，往左子结点走
            // 如果左节点为空，意味着找不到
            if(this.left == null){
                return null;
            }
            return this.left.search(value);
        }else{ // 如果值 大于或等于当前节点，往右子节点走
            if(this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    // 查找要删除的节点的 父节点
    public Node searchParent(int value){
        if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }else{
            if(value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else if(value >= this.value && this.right != null){
                return this.right.searchParent(value);
            }else{
                return null;
            }

        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

