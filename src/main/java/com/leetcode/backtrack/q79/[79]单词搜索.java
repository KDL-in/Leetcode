package com.leetcode.backtrack.q79;
//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SE
//E"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯 矩阵 
// 👍 973 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private boolean flag;
    private boolean vis[][];
    private int d[][];
    public boolean exist(char[][] board, String word) {
        flag = false;
        vis = new boolean[board.length][board[0].length];
        d = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, 0, board, word);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private void dfs(int x, int y, int k, char[][] board, String word) {
        if (flag) return;
        if (k == word.length()) {
            flag = true;
            return;
        }
        if (x >= board.length || x < 0 || y >= board[0].length || y < 0 || vis[x][y] || board[x][y] != word.charAt(k)) return;
        //System.out.println(x + " " + y + " " + board[x][y]);
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + d[i][0], ny = y + d[i][1];
            dfs(nx, ny, k + 1, board, word);
        }
        vis[x][y] = false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
