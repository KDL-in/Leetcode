package com.leetcode.comm.lcof.q12;
/*
79. Word Search
图，单词搜索
https://leetcode.com/problems/word-search/
* */
/*
用回溯法回简洁很多。
* */
class Solution {
    private boolean flag = false;
    private boolean vis[][];
    private int[][] d = {
        {0,-1},{-1,0},{0,1},{1,0}
    };
    public boolean exist(char[][] board, String word) {
        vis = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i){
            for(int j = 0; j < board[0].length; ++ j){
                if(flag) return flag; 
                dfs(i, j, 0, board, word);
            }
        }
        
        return flag;
    }
    
    private void dfs(int i, int j, int k, char[][]board, String word){
        if (k == word.length()) {flag = true; return;}
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || vis[i][j] ) return;
        if (board[i][j] != word.charAt(k)) return;
        if (flag) return;
       
        int x,y;
        vis[i][j] = true;
        for(int s = 0; s < 4; ++s){
            x = i + d[s][0];
            y = j + d[s][1];
            dfs(x, y, k + 1, board, word);   
        }
        vis[i][j] = false;
        
    }
}