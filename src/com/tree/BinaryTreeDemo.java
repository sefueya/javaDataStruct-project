package com.tree;

/**
 * ClassName: BinaryTree
 * Package: com.tree
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/19 9:48
 * @Version 1.0
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        // 创建二叉树
        BinaryTree binaryTree = new BinaryTree();

        // 创建二叉树的结点
        Node node1 = new Node(1, "宋江");
        Node node2 = new Node(2, "吴用");
        Node node3 = new Node(3, "卢俊义");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "关胜");

        // 说明：先手动创建二叉树，后续学习递归创建二叉树
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        // 设置根结点链接
        binaryTree.setRoot(node1);


        // 测试
        System.out.println("前序遍历");
        binaryTree.preOrder(); // 1,2,3,5,4
        System.out.println("中序遍历");
        binaryTree.infixOrder(); // 2,1,5,3,4
        System.out.println("后序遍历");
        binaryTree.postOrder(); // 2,5,4,3,1

        //Node resNode = binaryTree.preSearch(5);
        //Node resNode = binaryTree.infixSearch(5);
        Node resNode = binaryTree.postSearch(5);
        if (resNode != null) {
            System.out.println("找到了:" + resNode);

        }

        if(binaryTree.delNode(4) != -1){
            System.out.println("删除成功");
        }
        binaryTree.preOrder();
    }


}

// 创建二叉树
class BinaryTree{
    // 根结点
    private Node root;

    public void setRoot(Node root){
        this.root = root;
    }

    // 业务逻辑层的前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法前序遍历");
        }
    }

    // 业务逻辑层的中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法中序遍历");
        }
    }

    // 业务逻辑层的后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法后序遍历");
        }
    }

    // 业务逻辑层的前序查找
    public Node preSearch(int no){
        if(root != null){
            return root.preSearch(no);
        }else{
            System.out.println("树为空，无法前序查找");
            return null;
        }
    }
    // 业务逻辑层的中序查找
    public Node infixSearch(int no){
        if(root != null){
            return root.infixSearch(no);
        }else {
            System.out.println("树为空，无法中序查找");
            return null;
        }
    }
    // 业务逻辑层的后序查找
    public Node postSearch(int no){
        if(root != null){
            return root.postSearch(no);
        }else{
            System.out.println("树为空，无法后序查找");
            return null;
        }
    }

    // 业务逻辑层的删除结点
    public int delNode(int no){
        if(root != null){
            if(root.getNo() == no){
                root = null;
                return 1;
            }else{
                return root.delNode(no);
            }
        }else{
            System.out.println("空树不能删除");
            return -1;
        }
    }
}

// 创建结点
class Node{
    // 结点的值 存放 编号 和 绰号
    private int no;
    private String name;
    // 左结点和右结点
    private Node left;
    private Node right;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    // 前序遍历的方法
    public void preOrder(){
        // 先输出当前结点，判断this是不是null的全部都在业务逻辑层处理
        System.out.println(this);
        // 递归向左子树
        if(this.left != null){
            this.left.preOrder();
        }
        // 递归向右子树
        if(this.right != null){
            this.right.preOrder();
        }
    }


    // 中序遍历的方法
    public void infixOrder(){
        // 递归向左子树
        if(this.left != null){
            this.left.infixOrder();
        }
        // 输出当前结点
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    // 后序遍历的方法
    public void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);

    }

    // 前序查找
    public Node preSearch(int no){
        // 判断一次就打印一次，用于统计判断的次数
        System.out.println("前序查找~");
        // 判断当前结点是不是
        if(this.no == no){
            return this;
        }

        Node resNode = null;

        // 判断当前结点的左子结点是否为空，如果不为空则递归前序查找
        if(this.left != null){
            resNode = this.left.preSearch(no);
        }
        if(resNode != null){
            return resNode;
        }

        // 当前结点的右子结点是否为空，如果不空，则继续向右递归前序查找
        if(this.right != null){
            resNode = this.right.preSearch(no);
        }
        // 所有都查找完了，必须得 返回了，如果一直都没找到这里返回的就是null
        return resNode;
    }

    // 中序查找
    public Node infixSearch(int no){

        Node resNode = null;
        // 如果左子结点不为空，则 递归 中序查找
        if(this.left != null){
            resNode = this.left.infixSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        // 判断前打印一句话，用于统计判断次数
        System.out.println("中序查找~");
        // 如果左子结点为空，则判断 当前结点是否为查找的，如果是则返回
        if(this.no == no){
            return this;
        }
        // 如果右子结点不为空，则递归 中序查找，如果右子结点找到则返回，如果没找到 返回 null
        if(this.right != null){
            resNode = this.right.infixSearch(no);
        }
        return resNode;
    }

    // 后序查找
    public Node postSearch(int no){

        Node resNode = null;
        // 如果左子结点不为空，则 递归 后序查找
        if(this.left != null){
            resNode = this.left.postSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        // 如果没有找到，就判断当前结点的右子节点是否为空，如果不为空，则右递归进行后序查找，如果找到，就返回
        if(this.right != null){
            resNode = this.right.postSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        // 判断前打印一句话用于，统计判断次数
        System.out.println("后序查找~");
        // 和当前结点进行，比如，如果是则返回，否则返回null
        if(this.no == no){
            return this;
        }
        return null;
    }

    // 删除结点
    public int delNode(int no){
        // 树是空树或只有一个结点的处理 放在业务逻辑层处理
        if(this.left != null && this.left.no == no){
            if(this.left.left == null && this.left.right == null){
                this.left = null;
            }else if(this.left.left != null && this.left.right != null){
                Node temp = this.left.right;
                this.left = this.left.left;
                this.left.right = temp;
            }else if(this.left.left == null){
                this.left = this.left.right;
            }else{
                this.left = this.left.left;
            }
            return 1;
        }
        if(this.right != null && this.right.no == no){
            if(this.right.left == null && this.right.right == null){
                this.right = null;
            }else if(this.right.left != null && this.right.right != null){
                Node temp = this.right.right;
                this.right = this.right.left;
                this.right.right = temp;
            }else if(this.right.left == null){
                this.right = this.right.right;
            }else{
                this.right = this.right.left;
            }
            return 1;
        }
        int res = -1;
        if(this.left != null){
            res = this.left.delNode(no);
        }
        if(res != 1 && this.right != null){
            res = this.right.delNode(no);
        }
        return res;
    }


    // 创建get set方法和toString方法
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}