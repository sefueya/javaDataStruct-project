package com.horse;

import java.awt.*;
import java.util.ArrayList;

/**
 * ClassName: HorseChessboard
 * Package: com.horse
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/7 12:38
 * @Version 1.0
 */
public class HorseChessboard {

    private static int X; // 棋盘的列数
    private static int Y; // 棋盘的行数

    // 创建一个数组，标记棋盘上的各个位置是否被访问过
    private static boolean[] visited;
    // 使用一个属性，表示是否棋盘的所有位置都被访问过了
    private static boolean finished;


    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * X];

        long start = System.currentTimeMillis();

        traversalChessboard(chessboard,row - 1,column - 1,1);

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        for (int[] i : chessboard) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 根据当前位置（Point对象），计算马儿还能走哪些位置（Point），并放入到个集合中（ArrayList），最多有8个位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0 && !visited[p1.y * X + p1.x]){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0 && !visited[p1.y * X + p1.x]){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0 && !visited[p1.y * X + p1.x]){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0 && !visited[p1.y * X + p1.x]){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y && !visited[p1.y * X + p1.x]){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y && !visited[p1.y * X + p1.x]){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y && !visited[p1.y * X + p1.x]){
            ps.add(new Point(p1));
        }
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y && !visited[p1.y * X + p1.x]){
            ps.add(new Point(p1));
        }
        return ps;
    }

    public static void traversalChessboard(int[][] chessboard,int row,int column,int step){
        chessboard[row][column] = step;
        visited[row * X + column] = true;
        ArrayList<Point> ps = next(new Point(column, row));

        // 贪心优化，先走 下下步中能走的位置最少的
        sort(ps);
        // 遍历ps
        while (!ps.isEmpty()){
            Point p = ps.remove(0);
            if(!visited[p.y * X + p.x]){
                traversalChessboard(chessboard,p.y,p.x,step + 1);
            }
        }
        if(step < X * Y && !finished){
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        }else{
            finished = true;
        }
    }


    // 使用贪心算法对原来的算法优化，没优化 16s+，优化后 1ms
    // 获取当前位置可以走的下一个位置的集合,然后 进行非递减排序
    public static void sort(ArrayList<Point> ps){
        ps.sort((p1,p2) -> next(p1).size() - next(p2).size());
    }

}
