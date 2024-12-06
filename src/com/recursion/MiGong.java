package com.recursion;

/**
 * ClassName: MiGong
 * Package: com.recursion
 * Description:
 *
 * @Author sefue
 * @Create 2024/11/11 10:37
 * @Version 1.0
 */
public class MiGong {
    // 创建一个二维数组模拟迷宫
    static int[][] map = new int[8][7];

    public static void main(String[] args) {
        MiGong.iniMap();
        // 输入起点
        MiGong.setWay(1,1);
        MiGong.show();
    }
    public static void iniMap(){
        // 创建一个二维数组模拟迷宫
        map = new int[8][7];
        // 在里面用1表示墙，0表示还没走的点，2表示已经走过的点，3表示死点
        // 用循环绘制地图边缘的墙
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[map.length - 1][i] = 1;
        }
        for(int i = 0;i < map.length;i++){
            map[i][0] = 1;
            map[i][map[0].length - 1] = 1;
        }
        // 绘制地图内部的墙
        map[6][2] = 1;
    }

    // 打印地图
    public static void show(){
        if(map == null){
            return;
        }
        for (int[] row : map) {
            for (int column : row) {
                System.out.print(column + "  ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param i i,j表示 这一步要走位置对应 的 数组的下标
     * @param j
     * @return 回溯，如果按一个路径往下找 发现这样能走到终点，那么层层向上返回true，否则返回false 并标记此位置为思路
     *      如果小球能走到或者说 数组map的 map[6][5]置为1时，说明通路找到
     *      走法是下右上左，可以更改 方法内 各个setWay()的顺序更改走法
     */
    public static boolean setWay(int i,int j){
        if(map[6][5] == 1){
            return true;
        }else if(map[i][j] == 0){
            map[i][j] = 1;
            if(setWay(i + 1,j)){
                return true;
            }else if(setWay(i,j + 1)){
                return true;
            }else if(setWay(i - 1,j)){
                return true;
            }else if(setWay(i,j - 1)){
                return true;
            }else{
                map[i][j] = 3;
                return false;
            }
        }else{
            return false;
        }
    }
}
