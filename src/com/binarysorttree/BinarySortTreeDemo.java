package com.binarysorttree;

/**
 * ClassName: BinarySortTree
 * Package: com.binarysorttree
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/30 9:35
 * @Version 1.0
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,1,5,2,9,12};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环的添加结点
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        binarySortTree.infixOrder();
        System.out.println("-------------------");
        binarySortTree.testOrder();

        //System.out.println("测试删除叶子结点");
        //binarySortTree.delNode(2);
        //binarySortTree.delNode(1);

        //System.out.println("测试删除有一个子节点的结点");
        //binarySortTree.delNode(0);

        System.out.println("测试删除有2个子节点的结点");
        binarySortTree.delNode(7);
        binarySortTree.infixOrder();

    }
}


// 二叉排序树类
class BinarySortTree{
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
    public Node search(int value){
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
}


// 结点类
class Node{
    int value;
    Node left;
    Node right;
    public Node(int value){
        this.value = value;
    }

    // 添加结点的方法,递归，需要满足二叉排序树的要求
    public void add(Node node){
        if(node == null){
            return;
        }

        // 判断传入的结点的值，和当前子树的节点的值关系
        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{ // 传入结点的值 大于或等于 当前节点的值，放到右节点
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
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
