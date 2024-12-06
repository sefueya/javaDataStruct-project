package com.hufumanCode;

import java.util.*;

/**
 * ClassName: HufumanCode
 * Package: com.hufumanCode
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/23 11:24
 * @Version 1.0
 */
public class HufumanCode {

    // 定义哈夫曼编码表 Map<byte,String>
    static Map<Byte,String> huffmanCodes = new HashMap<>();

    // 定义StringBuilder，定义成静态的方便 递归 方法的使用
    static StringBuilder stringBuilder = new StringBuilder();
    static int strbuLen = 0;

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        byte[] sourceByte = decode(huffmanCodes,huffmanCodeBytes);
        System.out.println(new String(sourceByte));

    }


    // 根据字符串对应的byte数组，构建 Node链表，每个Node都构建好（权值，字母）
    private static LinkedList<Node> getNodes(byte[] bytes){
        LinkedList<Node> nodes = new LinkedList<>();

        // 存储每一个 byte出现的次数，使用 Map类
        HashMap<Byte, Integer> counts = new HashMap<>();
        for(byte b:bytes){
            counts.merge(b, 1, Integer::sum);
        }
        for(var entry:counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;

    }

    // 通过链表，创建对应的赫夫曼树
    public static Node createHuffmanTree(LinkedList<Node> nodes){
        while(nodes.size() != 1){
            Collections.sort(nodes);
            Node left = nodes.removeFirst();
            Node right = nodes.removeFirst();
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.add(0,parent);
        }
        return nodes.removeFirst();
    }

    // 生成赫夫曼树 对应的 赫夫曼编码表 存放在 map<Byte,String> ，如 97:100
    // code对应取值为 0或1,0代表往左边走，1代表往右边走，最终code追加在stringBuilder形成一个字母的编码
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){

        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);

        // 将code追加到这一轮递归的字符串后面,递归的第一轮 code为null，表示不追加
        if(code != null){
            stringBuilder2.append(code);
        }


        if(node != null){
            if(node.data == null){ // 非叶子节点
                getCodes(node.left,"0",stringBuilder2);
                getCodes(node.right,"1",stringBuilder2);
            }else{ // 叶子结点
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }

    // 为了重载方便用 下面getCodes调用上面的getCodes
    private static Map<Byte,String> getCodes(Node root){
        if(root == null){
            return null;
        }
        getCodes(root.left,"0",stringBuilder);
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    // 将字符串 编码成 byte[]数组
    private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes){
        StringBuilder strbu = new StringBuilder();
        for (byte b : bytes) {
            strbu.append(huffmanCodes.get(b));
        }

        strbuLen  = strbu.length();
        // 计算返回byte数组的长度
        int len = (strbu.length() + 7) / 8;

        byte[] huffmanCodeBytes = new byte[len];
        for (int i = 0,index = 0; i < strbu.length(); i += 8,index++) {
            String strByte;
            if(i + 8 > strbu.length()){
                strByte = strbu.substring(i);
            }else{
                strByte = strbu.substring(i,i + 8);
            }
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
        }
        return huffmanCodeBytes;
    }

    // 将前面的方法封装，便于调用
    private static byte[] huffmanZip(byte[] bytes){
        LinkedList<Node> nodes = getNodes(bytes);
        // 根据node创建赫夫曼树
        Node root = createHuffmanTree(nodes);
        // 根据生成的赫夫曼树 生成 赫夫曼编码
        // getCodes(root,null,stringBuilder)  为了调用方便重载了getCodes ，之后用下面的调用 方式
        Map<Byte, String> huffmanCodes = getCodes(root);

        // 根据生成的赫夫曼编码，压缩得到 压缩后的 赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }


    // 将一个byte转成一个二进制字符串
    // flag代表是否 不是最后一个字节数组的最后一个，如果是最后一个不需要补高位，是几位就返回几位
    private static String byteToBitString(byte b){
        int temp = b;
        // 补高位操作,当temp或运算 256，相当于 1 | 100000000 -> 100000001 ，此时截取后八位即可
        temp |=  256;

        // 如果b是一个负数，则返回32长度字符串
        // 如果b是一个正数，则 左边不会补零，相当于 1转成 String byte 需要的是 00000001，但其实返回的字符串是 1
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);


    }

    // 数据的解码
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i < huffmanBytes.length;i++){
            String str;
            if(i == huffmanBytes.length - 1){
                int wei = strbuLen % 8;
                str = Integer.toBinaryString(huffmanBytes[i]);
                if(huffmanBytes[i] >= 0){
                    while (str.length() < wei){
                        str = "0" + str;
                    }
                }
            }else{
                str = byteToBitString(huffmanBytes[i]);
            }
            stringBuilder.append(str);
        }

        // 拿到 哈夫曼编码字典的反向字典
        HashMap<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for(int i = 0;i < stringBuilder.length();){
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while(flag){
                String key = stringBuilder.substring(i , i + count);
                b = map.get(key);
                if(b == null){
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }

}


// 创建Node
class Node implements Comparable<Node>{
    Byte data; // 存放字符对应的ASCII
    int weight; // 权值，即这个字符出现了多少次
    Node left;
    Node right;
    public Node(Byte data,int weight){
        this.data = data;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o){
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left != null) this.left.preOrder();
        if(this.right != null) this.right.preOrder();
    }
}