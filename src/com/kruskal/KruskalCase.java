package com.kruskal;

import java.util.Arrays;

/**
 * ClassName: KruskalCase
 * Package: com.kruskal
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/6 10:50
 * @Version 1.0
 */
public class KruskalCase {

    private int edgeNum;// 边的个数

    private char[] vertexs; // 顶点数组

    private int[][] matrix; //邻接矩阵

    // 使用int的最大值表示不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
            {0,12,INF,INF,INF,16,14},
            {12,0,10,INF,INF,7,INF},
            {INF,10,0,3,5,6,INF},
            {INF,INF,3,0,4,INF,INF},
            {INF,INF,5,4,0,2,8},
            {16,7,6,INF,2,0,9},
            {14,INF,INF,INF,8,9,0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.kruskal();
    }

    public KruskalCase(char[] vertexs,int[][] matrix){
        // 初始化顶点数
        int vlen = vertexs.length;

        // 初始化顶点，复制拷贝的方式
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        // 初始化边，复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        // 统计边
        for (int i = 0; i < vlen; i++) {
            // j = i + 1，不统计 自己和自己构成边，并且A-F统计一次不能让 F-A再统计一次·
            for (int j = i + 1; j < vlen; j++) {

                if(this.matrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }
    }

    // 打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵为:\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%10d\t",matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     *
     * @param ch 顶点的值 'A'
     * @return 返回顶点对应的下标，如果找不到返回-1
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i] == ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，放到EData[] 数组中，后面需要遍历该数据，是通过 matrix[][] 邻接矩阵来获取
     * Edata[]形式 [{'A','B',12},{'B','F',7},...]
     * @return
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            // j = i+1 自己不能跟自己构成边，并且A-F统计一次不能让 F-A再统计一次·
            for (int j = i + 1; j < vertexs.length; j++) {
                if(matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的 终点， 重要 难点，传入 字符'A'的下标 那么拿A的下标值去找 end[i] 然后再将 end[i] 给 i，然后接着 获取 end[i]
     * 直到 end[i] = 0 ，这说明 最开始传入的 i下标顶点的 终点 就是 此时i的下标
     * @param ends 记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成
     * @param i 顶点的下标
     * @return 顶点的 终点的下标
     */
    private int getEnd(int[] ends,int i ){
        while(ends[i] != 0){
            i = ends[i];
        }
        return i;
    }


    public void kruskal(){
        int index = 0;
        int[] ends = new int[edgeNum]; // 用于保存 当前最小生成树 中的每个顶点再最小生成树中的终点

        // 创建结果数组
        EData[] res = new EData[matrix.length - 1];

        // 获取图中所有边的集合
        EData[] edges = getEdges();

        Arrays.sort(edges);

        // 遍历edges数组(注意此时 edge是排好序的，从小到大)，将边添加到最小生成树中，判断准备加入的边 准备加入的边 会不会形成回路
        // 如果没有则加入，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            // 获取边的 两个点
            int p1 = getPosition(edges[i].start);  // 对于第一轮 p1=4
            int p2 = getPosition(edges[i].end); // 对于第二轮 p2=5

            // 获取p1,p2 在目前 最小生成树中的终点
            int p1End = getEnd(ends,p1); // 对于第一轮 p1End = 4
            int p2End = getEnd(ends,p2); // 第二第二轮 p2End = 5

            // 判断是否构成回路
            if(p1End != p2End){
                //p1的终点设置为 p2
                ends[p1End] = p2End;
                // 注意 p2的终点不用设置了，因为 ends[p2] 初始就是0，p2 = 5 进入 getEnd(p2) 会得到 5，相当于5的终点就是5，没问题

                // 注意不用判断 p1和p2谁大 谁做终点，以为 p1必然比p2小（可以看 获取 edges数组是怎么获取的），所以设置p1的终点为p2
                res[index++] = edges[i]; // 将这条边加入到 最小生成树res 中
            }
        }

        System.out.println("最小生成树为");
        // 统计并打印最小生成树中
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}

// 创建一个类 Edata，它的对象实例就表示一条边
class EData implements Comparable<EData>{
    char start;// 边的一个点
    char end; // 边的另一个点
    int weight; // 边的权值

    public EData(char start,char end,int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edata{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(EData o) {
        return this.weight - o.weight;
    }
}

