package com.dynamic;

/**
 * ClassName: KnapsackProblem
 * Package: com.dynamic
 * Description:
 *
 * @Author sefue
 * @Create 2024/12/4 10:31
 * @Version 1.0
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};  // 物品的重量
        int[] val = {1500,3000,2000}; // 物品的价值
        int m = 4; // 背包的容量
        int n = val.length; // 物品的个数

        // 创建二维数组，代表 表,行代表 物品，代表 容量
        // 注意二维数组比实际 存放的数据要多一行 多一列，(必须得多一行一列，且第0行的数据和第0列的数据都得为0)否则下面处理时 i - 1 就会抛异常
        int[][] v = new int[n + 1][m + 1];

        // 为了记录放入商品情况，定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        // 动态规划处理
        for(int i = 1;i < v.length;i++){
            for(int j = 1;j < v[0].length;j++){
                // 当准备加入 新商品的重量 大于 当前背包的容量 时，就直接使用 上一个单元格 的装入策略
                if(w[i - 1] > j){ // 因为 i是从 1开始的，对应w中的物品重量得 -1，或者如果要对应 val数组中的数据 也得 减1
                    v[i][j] = v[i-1][j];
                }else{
                    // 当准备加入 新商品的重量 小于等于 当前背包容量时 v[i][j] = max{v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]}

                    // v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i-1][j - w[i - 1]]);

                    // 如果要记录商品存放的情况，不能直接使用上面的公式，否则不知道最终换的是哪个物品
                    if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]){
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 把当前放入情况记录到 path,只记录最优的情况
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }


        // 遍历二维数组
        for (int[] row : v) {
            for (int column : row){
                System.out.print(column + "\t");
            }
            System.out.println();
        }

        // 输出最优放入情况
        int i = path.length - 1;
        int j = path[0].length - 1;

        // 应该从 path 最后开始找
        while(i > 0 && j > 0){
            if(path[i][j] == 1){

                System.out.printf("第%d个商品放入背包\n",i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
