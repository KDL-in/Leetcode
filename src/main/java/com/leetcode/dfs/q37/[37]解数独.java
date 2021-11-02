package com.leetcode.dfs.q37;//编写一个程序，通过填充空格来解决数独问题。
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".
//",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".
//","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6
//"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[
//".",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8
//"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],[
//"4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9",
//"6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4",
//"5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 数组 回溯 矩阵 👍 992 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private boolean flag;
    private boolean[][][] vis;
    private int n;

    public void solveSudoku(char[][] board) {
        vis = new boolean[3][board.length][9];
        n = board.length;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') continue;
                mark(i, j, board[i][j], true);
            }
        }
        dfs(0, board);
    }

    private void mark(int i, int j, char c, boolean b) {
        int v = c - '1';
        int k = i / 3 * (n / 3) + j / 3;
        vis[0][i][v] = b;
        vis[1][j][v] = b;
        vis[2][k][v] = b;
    }

    private void dfs(int k, char[][] board) {
        if (flag) return;
        if (k == n * n) {
            flag = true;
            //System.out.println("here");
            return;
        }
        int r = k / n, c = k % n;
        if (board[r][c] != '.') {
            dfs(k + 1, board);
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (check(r, c, i)) {
                char ch = (char) (i + '1');
                board[r][c] = ch;
                mark(r, c, ch, true);
                dfs(k + 1, board);
                if (flag) return;
                board[r][c] = '.';
                mark(r, c, ch, false);
            }
        }
    }


    private boolean check(int i, int j, int num) {
        int k = i / 3 * (n / 3) + j / 3;
        return !vis[0][i][num] && !vis[1][j][num] && !vis[2][k][num];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
