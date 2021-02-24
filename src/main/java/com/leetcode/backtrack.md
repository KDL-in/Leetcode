#### 22. Generate Parentheses

```
/*
* 22. Generate Parentheses
* 括号生成
* https://leetcode.com/problems/generate-parentheses/
* */
```

本质上是一个子集数问题，每个位置可以是“(”或“)”。到达目标长度，则检查合法性。
然后就是剪枝。
- l规定为n
- r规定为n
- 添加")"时必须保证，l<r

```java
/*

Runtime: 0 ms, faster than 100.00% of Java online submissions for Generate Parentheses.
Memory Usage: 39.4 MB, less than 31.65% of Java online submissions for Generate Parentheses.
* */

class Solution {
    private StringBuilder s;
    private List<String> res;
    public List<String> generateParenthesis(int n) {
        s = new StringBuilder();
        res = new ArrayList<>();
        backtrack(n, n);
        return res;
    }

    private void backtrack(int l, int r) {
        if (l == 0 && r == 0) {
            res.add(s.toString());
        }
        if (l > 0) {
            s.append("(");
            backtrack(l - 1, r);
            s.deleteCharAt(s.length() - 1);
        }
        if (l < r && r > 0) {
            s.append(")");
            backtrack(l, r - 1);
            s.deleteCharAt(s.length() - 1);
        }
    }

}
```

#### 37. Sudoku Solver

```
/*
* 37. Sudoku Solver
* 数独游戏
* https://leetcode.com/problems/sudoku-solver/
*
* */
```

经典问题，回溯解数独

```java
/*
Runtime: 12 ms, faster than 53.71% of Java online submissions for Sudoku Solver.
Memory Usage: 36.2 MB, less than 88.06% of Java online submissions for Sudoku Solver.
* */

class Solution {
    private int N = 9;

    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int i, int j) {
        if (j == N) return backtrack(board, i + 1, 0);
        if (i == N) return true;
        if (board[i][j] != '.') return backtrack(board, i, j + 1);
        for (char c = '1'; c <= '9'; ++c) {
            if (!check(i, j, c, board)) continue;
            board[i][j] = c;
            // 找到直接返回
            if (backtrack(board, i, j+1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    private boolean check(int i, int j, char t, char[][] board) {
        int r, c;
        r = i/3 * 3;c = j / 3 * 3;
        for (int k = 0; k < N; k++)
            if (board[i][k] == t||board[k][j] == t||board[r+k%3][c+k/3] == t) return false;
        return true;
    }
}

/*
快速剪枝版本
Runtime: 4 ms, faster than 86.08% of Java online submissions for Sudoku Solver.
        Memory Usage: 36.4 MB, less than 78.79% of Java online submissions for Sudoku Solver.
*/

public class SolutionV1 {
    private int N = 9;
    private boolean [][][]flag;

    public void solveSudoku(char[][] board) {
        flag = new boolean[N][3][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j]!='.'){
                    mark(i, j, board[i][j], true);
                }
            }
        }
        backtrack(board, 0, 0);
    }


    private boolean backtrack(char[][] board, int i, int j) {
        if (j == N) return backtrack(board, i + 1, 0);
        if (i == N) return true;
        if (board[i][j] != '.') return backtrack(board, i, j + 1);
        for (char c = '1'; c <= '9'; ++c) {
            if (check(i, j, c)) continue;
            board[i][j] = c;
            mark(i, j, c, true);
            // 找到直接返回
            if (backtrack(board, i, j+1)) {
                return true;
            }
            board[i][j] = '.';
            mark(i, j, c, false);
        }
        return false;
    }

    private void mark(int i, int j, char c, boolean b) {
        int ci = c - '1';
        flag[i][0][ci] = flag[j][1][ci] = flag[i / 3 * 3 + j / 3][2][ci] = b;
    }

    private boolean check(int i, int j, char c) {
        int ci = c - '1';
        return flag[i][0][ci] || flag[j][1][ci] || flag[i / 3 * 3 + j / 3][2][ci];
    }

}
```