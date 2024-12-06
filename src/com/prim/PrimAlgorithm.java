package com.prim;

import java.util.Arrays;

/**
 * ClassName: PrimAlgorithm
 * Package: com.prim
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/5 10:32
 * @Version 1.0
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        // 创建
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;

        // 邻接矩阵的关系,用 10000这个 比较大的权值 来表示 两个点不连通
        int[][] weight = new int[][]{
            {10000,5,7,10000,10000,10000,2},
            {5,10000,10000,9,10000,10000,3},
            {7,10000,10000,10000,8,10000,10000},
            {10000,9,10000,10000,10000,4,10000},
            {10000,10000,8,10000,10000,5,4},
            {10000,10000,10000,4,5,10000,6},
            {2,3,10000,10000,4,6,10000}
        };

        // 创建MGraph对象
        MGraph graph = new MGraph(verxs);

        // 创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,verxs,data,weight);

        minTree.prim(graph,0);
    }
}


// 创建最小生成树->村庄的图
class MinTree{
    // 创建图的邻接矩阵

    /**
     *
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs,char[] data,int[][] weight){
        int i,j;
        // 遍历顶点
        for(i = 0;i < verxs;i++){
            graph.data[i] = data[i];
            for(j = 0;j < verxs;j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    // 显示图的的邻接矩阵
    public void showGraph(MGraph graph){
        for(int[] link:graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写 prim算法，生成最小生成树
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成， 'A' -> 0
     */
    public void prim(MGraph graph,int v){
        // visited[] 用于标记顶点是否被访问过
        int[] visited = new int[graph.verxs];

        // 把当前顶点 标记为访问过
        visited[v] = 1;

        // 用 h1 和 h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;

        int minWeight = 10000; // 将 minWeight 初始成一个大数，后面在遍历过程中，会被替换

        // n个顶点的图 生成 n-1 个边
        for (int k = 1;k < graph.verxs;k++){

            // 下面是确定每一次生成的子图，和哪个结点的距离最近
            // i表示被访问过的结点，j表示还没有访问过的结点，用下面的 if实现i和j的区别
            for(int i = 0;i < graph.verxs;i++){
                for(int j = 0;j < graph.verxs;j++){
                    if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        // 寻找已经访问过的顶点 和 未访问过的顶点 之间 权值最小的边
                        // 替换minWeight
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 输出这条最小的边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            // 将新顶点标记为已经访问
            visited[h2] = 1;
            // minWeight 重新设置为最大值
            minWeight = 10000;
        }
    }
}


class MGraph{
    int verxs; // 表示图节点的个数
    char[] data; // 存放节点的数据
    int[][] weight;  // 存放边，是 邻接矩阵

    public MGraph(int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
