package com.floyd;

import java.util.Arrays;

/**
 * ClassName: FloydAlgorithm
 * Package: com.floyd
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/7 12:09
 * @Version 1.0
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535; // 表示不连接
        matrix[0] = new int[]{0,5,7,N,N,N,2};
        matrix[1] = new int[]{5,0,N,9,N,N,3};
        matrix[2] = new int[]{7,N,0,N,8,N,N};
        matrix[3] = new int[]{N,9,N,0,N,4,N};
        matrix[4] = new int[]{N,N,8,N,0,5,4};
        matrix[5] = new int[]{N,N,N,4,5,0,6};
        matrix[6] = new int[]{2,3,N,N,4,6,0};

        Graph graph = new Graph(vertex, matrix, vertex.length);
        graph.floyd();
        graph.show();

    }
}

class Graph{
    private char[] vertex; // 顶点数组
    private int[][] matrix; // 邻接矩阵

    private int[][] dis; // 保存，从各个顶点出发到其它顶点的距离，最后的结果，也是保留在该数组
    private int[][] pre; // 保存，保存 各个顶点 到达 目标顶点的前驱顶点

    /**
     *
     * @param vertex 顶点数组
     * @param matrix 邻接矩阵
     * @param length 大小
     */
    public Graph(char[] vertex,int[][] matrix,int length){
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 对pre数组初始化
        for(int i = 0;i < length;i++){
            Arrays.fill(pre[i],i);
        }
    }

    public void show(){
        char[] vertex = {'A','B','C','D','E','F','G'};
        for (int k = 0; k < dis.length; k++) {
            // 输出pre
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + "  ");
            }
            System.out.println();
            // 输出dis
            for (int i = 0; i < dis.length; i++) {
                System.out.printf("(%c到%c的最短路径是:%d)  ",vertex[k],vertex[i],dis[k][i]);
            }
            System.out.println();
        }
    }

    public void floyd(){
        int len = 0;

        // k当做中间顶点 如 计算 A-C的最短距离，那么先 计算 A-K + K-C 的距离 和 A-C 比较，k [A,B,C,D,E,F,G]
        for (int k = 0; k < dis.length; k++) {
            // 从i顶点出发 [A,B,C,D,E,F,G]
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    // 求出从i顶点出发，经过k中间顶点到达j顶点的距离
                    len = dis[i][k] + dis[k][j];
                    if(len < dis[i][j]){
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }

}
