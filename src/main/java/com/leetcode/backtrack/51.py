""" 
==========================================
Task:

51. N-Queens
N 皇后
https://leetcode.com/problems/n-queens/

==========================================
Notes:
1. version 1速度瓶颈主要来源于check，剪枝函数，而check后的节点数其实很少
2. 全局变量大多数时候比较好，空间换时间也通常是划算的

==========================================
Examples:

Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]

===========================================
"""


# version 1
class Solution(object):
    def solveNQueens(self, n):
        class Q:
            def __init__(self, row, col):
                self.row = row
                self.col = col

        def check(row, col):
            for i in range(row):
                Q = Qs[i]
                if (Q.col == col) or \
                    ((Q.row + Q.col) == (row + col)) or \
                        ((Q.row - Q.col) == (row - col)):
                    return True
            return False

        def backtrack(row):
            if row == n:
                sol = ['.'*Q.col + 'Q' + '.'*(n-1-Q.col) for Q in Qs]
                sols.append(sol)
                return
            for col in cols:
                if check(row, col):
                    continue
                Qs[row].row, Qs[row].col = row, col
                backtrack(row + 1)
                # remove
        Qs, cols, sols = [Q(-1, -1) for _ in range(n)], list(range(n)), []

        backtrack(0)
        return sols

# version 2
# Runtime: 32 ms, faster than 99.20% of Python online submissions for N-Queens.
# Memory Usage: 13.9 MB, less than 13.23% of Python online submissions for N-Queens.

class Solution(object):
    def solveNQueens(self, n):
        def backtrack(row, cols, xy_sum, xy_sub):
            if row == n:
                sol = ['.'*col + 'Q' + '.'*(n-1-col) for col in cols]
                sols.append(sol)
                return
            for col in range(n):
                if col in cols or (row + col) in xy_sum or (row - col) in xy_sub:
                    continue
                backtrack(row + 1, cols + [col], xy_sum +
                          [row + col], xy_sub + [row - col])
                # remove
        sols = []
        backtrack(0, [], [], [])
        return sols

# version 3
# Runtime: 32 ms, faster than 99.20% of Python online submissions for N-Queens.
# Memory Usage: 13.7 MB, less than 63.93% of Python online submissions for N-Queens.
class Solution(object):
    def solveNQueens(self, n):
        def backtrack(row):
            if row == n:
                sols.append(['.'*col + 'Q' + '.'*(n-1-col)for col in cols])
                return
            for col in range(n):
                if col_flag[col] or xy_sum[row + col] or xy_sub[(row - col)%(n<<1)]: continue
                # select mark
                cols.append(col)
                col_flag[col], xy_sum[row + col], xy_sub[(row - col)%(n<<1)] = True, True, True
                # backtrack
                backtrack(row + 1)
                # remove
                cols.pop()
                col_flag[col], xy_sum[row + col], xy_sub[(row - col)%(n<<1)] = False, False, False
        sols, cols, col_flag, xy_sum, xy_sub = [], [], [False] * n , [False] * (n*2), [False] * (n*2)
        backtrack(0)
        return sols


s = Solution()
s.solveNQueens(4)
