package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * ClassName: GraphDemo
 * Package: com.graph
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/3 9:53
 * @Version 1.0
 */
public class GraphDemo {

    public static void main(String[] args) {
        int n = 8;// 结点的个数
        String[] vertexs = {"1","2","3","4","5","6","7","8"};

        // 创建图对象
        Graph graph = new Graph(n);
        // 循环的添加 顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        // 添加边
        // A-B,A-C,B-C,B-D,B-E,
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);

        // 显示邻接矩阵
        graph.showGraph();

        // 深度优先遍历
        graph.dfs(); //1 2 4 8 5 3 6 7

        // 广度优先遍历
        //graph.bfs(0); //1 2 4 8 5 3 6 7,如果图不连通这里参数写0只能遍历到一部分
        //graph.bfs(); // 就算图不联通也可以全部遍历到

    }


}

class Graph{
    private ArrayList<String> vertexList; // 存储顶点的集合
    private int[][] edges; // 存储图对应的邻接矩阵

    private int numOfEdges; // 表示边的数目

    private boolean[] isVisited; // 记录某个顶点是否被访问

    // 构造器传入 n 顶点数量
    public Graph(int n){
        // 初始化矩阵和ArrayList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
    }

    // 插入结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边的方法, 如表示 A和B的边的关系则传入 v1=0,v2=1,weight=1
     * @param v1 表示顶点的下标，即 表示 哪个顶点，"A" -> edges[0][...], "B" -> edge[1][...]
     * @param v2
     * @param weight weight 取值有1和0,1代表有边，0代表没边
     */
    public void insertEdge(int v1,int v2,int weight){
        // 因为表达的是无向图，得两个顶点互相的路径都赋值weight
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 返回结点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    // 得到边的个数
    public int getNumOfEdges(){
        return numOfEdges;
    }

    // 返回i下标对应的数据 0->"A",1->"B"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph(){
        for (int[] row : edges) {
            System.out.println(Arrays.toString(row));
        }
    }


    // 输出无向图中有哪些连接
    public void test(int row,int column){

        for (int i = column; i < edges[row].length; i++) {
            if(edges[row][i] == 1){
                System.out.println(getValueByIndex(row) + "和" + getValueByIndex(i) + "有连接");
                test(i,i);
            }
        }
    }


    /**
     * row行从column开始返回邻接结点下标
     * @param row
     * @param column
     * @return 如果有则返回下标，没了则返回-1
     */
    public int getNeighbor(int row,int column){
        for (int i = column; i < edges[row].length; i++) {
            if(edges[row][i] == 1){
                return i;
            }
        }
        return -1;
    }

    // 深度优先遍历算法
    public void dfs(boolean[] isVisited,int i){

        System.out.println(getValueByIndex(i) + "->");
        isVisited[i] = true;
        int column = getNeighbor(i,0);
        while(column != -1){
            if(!isVisited[column]){
                dfs(isVisited,column);
            }
            column = getNeighbor(i,column + 1);
        }

    }

    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        for(int index = 0;index < getNumOfVertex();index++){
            if(!isVisited[index]) {
                dfs(isVisited, index);
            }
        }
    }

    // 对一个结点广度优先遍历
    public void bfs(boolean[] isVisited, int i){
        int u;// 表示队列头结点对应的下标
        int w; // 表示邻接节点w
        // 队列，记录结点访问顺序
        LinkedList<Object> queue = new LinkedList<>();
        // 访问结点
        System.out.println(getValueByIndex(i) + "->");
        isVisited[i] = true;
        // 将结点加入队列
        queue.addLast(i);
        while(!queue.isEmpty()){
            u = (Integer) queue.removeFirst();
            w = getNeighbor(u,0);
            while(w != -1){
                if(!isVisited[w]){
                    System.out.println(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNeighbor(u,w + 1);
            }

        }
    }

    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

}