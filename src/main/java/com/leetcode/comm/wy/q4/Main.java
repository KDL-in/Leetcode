package com.leetcode.comm.wy.q4;
/*
1 1 1 1 0
0 1 0 1 0
1 1 2 1 1
0 2 0 0 1
 */
public class Main {
    private static int[][] cost;
    private static int[][] board;
    private static int d[][] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        int n = 0, m = 0;
        // todo 读二维数组
        board = new int[][]{
                {1,1,1,1,0},
                {0,1,0,1,0},
                {1,1,2,1,1},
                {0,2,0,0,1}
        };
        n = board.length;
        m = board[0].length;
        cost = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        cost[0][0] = 0;
        dfs(0, 0);
        System.out.println(cost[n - 1][m - 1] == Integer.MAX_VALUE ? -1 : cost[n - 1][m - 1]);
    }

    public static void dfs(int i, int j) {
        if ((i == cost.length - 1) && (j == cost[0].length - 1)) return;
        //System.out.println(i + " " + j + " " + board[i][j] + " " + cost[i][j]);
        for (int k = 0; k < 4; k++) {
            int x = i + d[k][0], y = j + d[k][1];
            if (x >= 0 && x < cost.length && y >= 0 && y < cost[i].length
                    && board[x][y] != 2) {
                int newCost = cost[i][j] + (board[x][y] == 0 ? 2 : 1);
                if (newCost < cost[x][y]) {
                    cost[x][y] = newCost;
                    dfs(x, y);
                }
            }
        }
    }
}
