package com.sparsearray;


import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * ClassName: SparseArray
 * Package: com.sparsearray
 * Description:
 *
 * @Author sefue
 * @Create 2024/7/28 9:38
 * @Version 1.0
 */
public class SparseArray {
    

    @Test
    public void test1(){
        // 创建一个原始二维数组 11*11
        // 0表示没有其实，1表示黑子，2表示白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][3] = 2;
        chessArr1[1][3] = 1;
        chessArr1[1][4] = 1;
        // 输出原始的二维数组
        System.out.println("原始的二维数组~");
        for(int[] row: chessArr1){
            for(int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        // 将二维数组转换成稀疏数组
        // 1. 遍历原始二维数组，得到有效数据的个数
        int sum = 0;
        for(int[] row: chessArr1){
            for(int data : row){
                if(data != 0){
                    sum++;
                }
            }
        }
        // 2. 根据sum创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        // 3. 将二维数组的有效数据存入稀疏数组
        int count = 0;  // 用于记录是第几个非0数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        // 将稀疏数组存盘
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("map.data"));){
            for (int i = 0; i < sparseArr.length; i++) {
                for (int j = 0; j < sparseArr[0].length; j++) {
                    bw.write(sparseArr[i][j] + "\n");
                }
                bw.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        // 从磁盘读取稀疏数组
        int[][] sparseArr2 = new int[0][0];
        try(BufferedReader br = new BufferedReader(new FileReader("map.data"));){
            String s = null;
            int row = Integer.parseInt(br.readLine());
            int column = Integer.parseInt(br.readLine());
            int sum2 = Integer.parseInt(br.readLine());
            sparseArr2 = new int[sum2 + 1][3];
            sparseArr2[0][0] = row;
            sparseArr2[0][1] = column;
            sparseArr2[0][2] = sum2;
            l:for(int i = 1;;i++){
                for (int j = 0; j < 3; j++) {
                    if ((s = br.readLine()) != null)
                        sparseArr2[i][j] = Integer.parseInt(s);
                    else break l;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("磁盘读取的稀疏数组！");
        for(int[] row : sparseArr2){
            for(int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        // 将稀疏数组恢复成原始的二维数组
        // 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        // 2. 在读取稀疏数组后几行的数据，并赋给原始的二维数组
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }
        // 输出转换后的二维数组
        System.out.println("恢复后的二维数组");
        for (int i = 0; i < chessArr2.length; i++) {
            for (int j = 0; j < chessArr2[0].length; j++) {
                System.out.print(chessArr2[i][j] + "\t");
            }
            System.out.println();
        }
    }


    @Test
    public void test3(){
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        System.out.println("原始的二维数组");
        for(int[] row:chessArr){
            for(int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();

        }

        int sum = 0;
        for(int[] row:chessArr){
            for(int data:row){
                if(data != 0){
                    sum++;
                }
            }
        }
        System.out.println(sum);

        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        int index = 1;
        for(int i = 0;i < chessArr.length;i++){
            for(int j = 0;j < chessArr[0].length;j++){
                if(chessArr[i][j] != 0){
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = chessArr[i][j];
                    index++;
                }
            }
        }
        System.out.println("稀疏数组");
        for(int[] row:sparseArr){
            for(int data:row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        int[][] newArray = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i = 1;i < sparseArr.length;i++){
            newArray[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("转换变成的二维数组");
        for(int[] row:newArray){
            for(int data:row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }


    }

}
