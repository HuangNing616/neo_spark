package com.huangning.java.leetcode.StackQueue;

import java.util.Stack;

/**
 * Author：Henry
 * Date: 12/04/2020
 * knowledge: 1. 写完后发现dfs和bfs的代码是一摸一样，只是数据结构不同，一个是栈，一个是队列
 *            2. 记住如果写if else if 的话，程序只能选择其中的一项执行
 *

 * Question:
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 */
public class NumberofIslandsII {
    public static void main(String []args){
        char [][] island = {{'1','1','1'}, {'0','1','0'}, {'0','1','0'}};
        NumberSolution so = new NumberSolution();
        int res = so.numIslands(island);
        System.out.println(res);

    }
}

class NumberSolution {
    public int numIslands(char[][] grid) {
        if(grid.length==0){
            return 0;
        }

        // get the row and col of the grid
        int nrow = grid.length;
        int ncol = grid[0].length;

        // initialize the num of the islands
        int numIsland = 0;
        Stack<Integer> stack = new Stack<>();

        // traversal the whole 2d array named grid
        for(int i = 0; i < nrow; i++){
            for(int j = 0; j< ncol; j++){
                if(grid[i][j]=='1'){
                    numIsland ++;
                    grid[i][j] = '0';
                    int id = i * ncol + j;
                    stack.push(id);
                    while (!stack.isEmpty()){
                        id = stack.pop();
                        int row = id / ncol;
                        int col = id % ncol;
                        if(row-1>=0 && grid[row-1][col] == '1' ){
                            grid[row-1][col] = '0';
                            stack.push((row-1)*ncol + col);
                        }
                        if(row+1<nrow && grid[row+1][col] == '1' ){
                            grid[row+1][col] = '0';
                            stack.push((row+1)*ncol + col);
                        }
                        if(col - 1 >=0  && grid[row][col-1] == '1' ){
                            grid[row][col-1] = '0';
                            stack.push(row*ncol + col-1);
                        }
                        if(col+1<ncol && grid[row][col+1] == '1' ){
                            grid[row][col+1] = '0';
                            stack.push(row*ncol + col+1);
                        }
                    }
                }
            }
        }
        return numIsland;
    }
}