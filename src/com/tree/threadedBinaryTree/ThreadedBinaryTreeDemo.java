package com.tree.threadedBinaryTree;

/**
 * ClassName: ThreadedBinaryTreeDemo
 * Package: com.tree
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/20 10:49
 * @Version 1.0
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        Node root = new Node(1, "tom");
        Node node2 = new Node(3, "jack");
        Node node3 = new Node(6, "smith");
        Node node4 = new Node(8, "mary");
        Node node5 = new Node(10, "king");
        Node node6 = new Node(14, "damn");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        // 测试中序线索化
        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        threadBinaryTree.setRoot(root);
        threadBinaryTree.threadedNodes(root);

        // 测试10结点
        Node leftNode = node5.getLeft();
        Node rightNode = node5.getRight();
        System.out.println("10号结点的前驱节点是" + leftNode);
        System.out.println("10号结点的后继结点是" + rightNode);

        // 测试线索化的方式遍历 线索二叉树
        threadBinaryTree.threadedList();
        threadBinaryTree.threadedList2(root);
    }
}

// 创建线索化二叉树类
class ThreadBinaryTree{
    // 根结点
    private Node root;

    // 为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    // 在线索化时，pre总是保留 做线索化时的 前一个结点
    private Node pre;

    // 编写对二叉树进行 中序 线索化的方法
    // node 就是当前需要线索化的结点
    public void threadedNodes(Node node){

        // 如果node为 null，不能线索化
        if(node == null){
            return;
        }
        // 1.先线索化左子树
        threadedNodes(node.getLeft());
        // 2.线索化当前结点[重点]

        // 2.1 先处理当前节点的前驱节点,注意最左下角的节点是没有前驱节点的
        if(node.getLeft() == null){
            // 如果节点的左指针为空，那么将左指针指向当前节点的前驱节点
            node.setLeft(pre);
            // 修改当前结点的左指针类型
            node.setLeftType(1);
        }

        // 2.2 处理后继结点
        // 可看 中序线索化树.png
        // 针对于最左下角那个节点8来说，处理8的后继结点 是递归 倒数第二层 做的，所以不能用 node.getRight == null来做判断
        // 因为递归到8时pre还是null不能做处理，这个pre到 下一次 递归时才能是指向 左下角节点，此时可以让左下角节点（此时是pre）的右指针指向它的父节点（此时是 node）

        // 可以这样说，node到8结点的时候这一步是不会做的，只有node递归回去到node父节点3时才会做，此时pre是8，node是3，所以根据规则让8的后继节点指向3
        // 同样可以想想 10节点的后继结点是怎么指向 1节点的
        if(pre != null && pre.getRight() == null){
            // 让前驱节点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针类型
            pre.setRightType(1);
        }

        // 2.3 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        // 3.线索化右子树
        threadedNodes(node.getRight());
    }

    // 遍历 中序线索化二叉树 的方法
    public void threadedList(){
        Node node = root;
        while(node != null){
            // 循环找到 leftType == 1的节点，第一个找到的就是8节点
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            // 打印当前节点
            System.out.println(node);
            // 如果当前节点的右指针 指向的是 后继结点，就一直输出
            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }

            // 节点走向右子树
            node = node.getRight();
        }
    }

    // 自己写的，用之前递归遍历二叉树的方式遍历
    public void threadedList2(Node node){
        if(node.getLeft() != null && node.getLeftType() != 1){
            threadedList2(node.getLeft());
        }
        System.out.println(node);
        if(node.getRight() != null && node.getRightType() != 1){
            threadedList2(node.getRight());
        }
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public void setRoot(Node root){
        this.root = root;
    }


}

// 二叉树类的结点类
class Node{
    // 结点的值 存放 编号 和 绰号
    private int no;
    private String name;

    // 左结点和右结点
    private Node left;
    private Node right;

    // 说明
    // 1.如果leftType == 0 指向的是左子树，1则表示指向的是前驱节点
    // 2.如果RightType == 0 指向的是右子树，1则表示指向的是后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }


    public Node(int no, String name) {
        this.no = no;
        this.name = name;
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




