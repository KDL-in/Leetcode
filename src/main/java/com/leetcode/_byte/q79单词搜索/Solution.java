package com.leetcode._byte.q79单词搜索;

class Solution {
    private boolean flag = false;
    private int dir[][] = {
        {0, -1}, {-1, 0}, {0, 1}, {1, 0}
    };
    private boolean vis[][];

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        vis = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++ i){
            for (int j = 0; j < board[0].length; ++ j){
                if (board[i][j] != word.charAt(0)) continue;
                vis[i][j] = true;
                if (dfs(i, j, 1, board, word)){
                    return true;
                }
                vis[i][j] = false;
            }
        }
        return false;
    }

    public boolean dfs(int i, int j, int k, char[][] board, String word){
        //System.out.println(i +" " + j + " " + board[i][j]);
        if(flag) return true;
        if(k == word.length()) return flag = true;
    
        
        for (int m = 0; m < 4; ++m){
            int x = i + dir[m][0], y = j + dir[m][1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || vis[x][y] || board[x][y] != word.charAt(k)) continue;
            vis[x][y] = true;
            if (dfs(x, y, k + 1, board, word)){
                return true;
            }
            vis[x][y] = false;
        }
        
        return false;
    }


}