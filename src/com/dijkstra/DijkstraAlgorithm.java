package com.dijkstra;

import java.util.Arrays;

/**
 * ClassName: DijkstraAlgorithm
 * Package: com.dijkstra
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/6 12:07
 * @Version 1.0
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535; // 表示不连接

        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertex, matrix);
        graph.dsj(6);
    }
}

class Graph{
    private char[] vertex; // 顶点数组
    private int[][] matrix; // 邻接矩阵

    private VistedVertex vv; // 已经访问的顶点的集合

    public Graph(char[] vertex,int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph(){
        for(int[] link:matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    // 迪杰斯特拉算法实现, index表示出发顶点
    public void dsj(int index){
        vv = new VistedVertex(vertex.length, index);

    }


    // 更新 index下标顶点 到 周围顶点的距离 和 周围顶点的前驱结点
    private void update(int index){
        int len = 0;

        // 遍历邻接矩阵的行
        for(int j = 0;j < matrix[0].length;j++){
            // len的含义：出发顶点的距离 + 从index顶点到j顶点的距离 之和
            len = vv.getDis(index) + matrix[index][j];

            // 如果j没有访问过，并且len小于出发顶点 到 j 顶点的距离 就需要更新
            if(!vv.in(j) && len < vv.getDis(j)){


            }
        }
    }
}


// 已访问顶点集合
class VistedVertex{
    // 记录各个顶点是否访问过 1表示访问过，0未访问，会动态更新
    public int[] already_arr;

    // 每个下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;

    // 记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    // 构造器
    // index表示出发顶点
    public VistedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];

        // 初始化dis数组
        Arrays.fill(dis,65535);
        this.dis[index] = 0;
    }


    // 判断index顶点是否被访问过
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    // 更新 出发顶点 到 index顶点的距离
    public void updateDis(int index,int len){
        dis[index] = len;
    }


    //更新 顶点 的前驱结点 成 index
    public void updatePre(int pre,int index){
        pre_visited[pre] = index;
    }


    // 返回出发顶点到index顶点的距离
    public int getDis(int index){
        return dis[index];
    }



}