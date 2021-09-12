package com.huangning.java.leetcode.stackqueue;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Henry
 * Date: 7/4/2020
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 */
public class NumberofIslands {
    public static void main(String []args){
        System.out.println("计算岛屿个数: ");
        char [][]grid = new char[2][2];
        grid[0][0]='1';
        grid[0][1]='0';
        grid[1][0]='0';
        grid[1][1]='1';
        IslandSolution so = new IslandSolution();
        int res = so.numIslands(grid);
        System.out.println("岛屿的数量为："+res);


    }
}


//// DFS
//class IslandSolution {
//
//    public void DFS(char[][] grid, int row, int col){
//
//        int nrow = grid.length;
//        int ncol = grid[0].length;
//
//        if(row<0 || col<0 || row>=nrow || col>=ncol || grid[row][col] == '0'){
//            return;
//        }
//
//        grid[row][col] = '0';
//        DFS(grid, row-1, col);
//        DFS(grid, row+1, col);
//        DFS(grid, row, col-1);
//        DFS(grid, row, col+1);
//
//    }
//
//    public int numIslands(char[][] grid) {
//        if(grid.length==0){
//            return 0;
//        }
//        // get the row number and col number of the grid
//        int nrow = grid.length;
//        int ncol = grid[0].length;
//        int count = 0;
//
//        // traversal the grid
//        for(int i=0; i<nrow; i++){
//            for(int j=0;j<ncol; j++){
//
//                // if find the land then mark the point visited, and traversal the neighborhood point visited
//                if(grid[i][j]=='1'){
//                    DFS(grid, i, j);
//                    count += 1;
//                }
//            }
//        }
//        return count;
//    }
//}

// BFS
class IslandSolution {

    public int numIslands(char[][] grid) {
        if(grid.length==0){
            return 0;
        }
        // get the row number and col number of the grid
        int nrow = grid.length;
        int ncol = grid[0].length;
        int count = 0;

        // traversal the grid
        for(int i=0; i<nrow; i++){
            for(int j=0;j<ncol; j++){

                // if find the land then mark the point visited, and traversal the neighborhood point visited
                if(grid[i][j]=='1'){
                    count ++;
                    // mark as visited
                    grid[i][j]='0';

                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(i*ncol+j);
                    while (!queue.isEmpty()){

                        // pop the head element of the queue
                        int id = queue.poll();
                        int row = id/ncol;
                        int col = id%ncol;
                        if(row-1>=0 && grid[row-1][col]=='1'){
                            grid[row-1][col]='0';
                            queue.add((row-1)*ncol + col);
                        }

                        if(row+1<nrow && grid[row+1][col]=='1'){
                            grid[row+1][col]='0';
                            queue.add((row+1)*ncol + col);
                        }

                        if(col-1>=0 && grid[row][col-1]=='1'){
                            grid[row][col-1]='0';
                            queue.add(row*ncol + col-1);
                        }

                        if(col+1<ncol && grid[row][col+1]=='1'){
                            grid[row][col+1]='0';
                            queue.add(row*ncol + col+1);
                        }
                    }
                }
            }
        }
        return count;
    }
}