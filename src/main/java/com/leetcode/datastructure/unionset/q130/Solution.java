package com.leetcode.datastructure.unionset.q130;
/*
 * 130. Surrounded Regions
 *  找到被严格包围的O翻转为x
 * https://leetcode.com/problems/surrounded-regions/
 * */

/*
并查集
Runtime: 9 ms, faster than 8.29% of Java online submissions for Surrounded Regions.
Memory Usage: 40.9 MB, less than 75.42% of Java online submissions for Surrounded Regions.
*/

import java.util.LinkedList;

class Solution {
    int[] par;

    public void solve(char[][] board) {
        if (board.length == 0) return;
        par = new int[board.length * board[0].length];
        for (int i = 0; i < par.length; i++) par[i] = i;
        flip(par, board);
    }

    private void flip(int[] par, char[][] board) {
        int h, w, root, cur;
        h = board.length;
        w = board[0].length;

        // union
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (board[r][c] == 'X') continue;
                cur = r * w + c;
                if (r > 0 && board[r - 1][c] == 'O') union(cur, (r - 1) * w + c, h, w);
                if (c < w - 1 && board[r][c + 1] == 'O') union(cur, r * w + c + 1, h, w);
                if (r < h - 1 && board[r + 1][c] == 'O') union(cur, (r + 1) * w + c, h, w);
                if (c > 0 && board[r][c - 1] == 'O') union(cur, r * w + (c - 1), h, w);
            }
        }
        // flip
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (board[r][c] == 'X') continue;
                root = find(r * w + c);
                if (!isBorder(root, h, w)) board[r][c] = 'X';
            }
        }

    }

    private void union(int a, int b, int h, int w) {
        int ra = find(a);
        int rb = find(b);
        if (isBorder(ra, h, w)) par[rb] = ra;
        else par[ra] = rb;
    }

    private int find(int cur) {
        int p;
        while ((p = par[cur]) != cur) {
            par[cur] = par[p];
            cur = p;
        }
        return p;
    }

    private boolean isBorder(int root, int h, int w) {
        int r = root / w, c = root % w;
        return r == 0 || c == 0 || (r == h - 1) || (c == w - 1);
    }

    public static void main(String[] args) {
        char[][] board = new char[10][12];
        System.out.println(board[0].length);
    }
}