package com.leetcode.datastructure.unionset.q130;
/*

正规并查集实现
Runtime: 6 ms, faster than 13.25% of Java online submissions for Surrounded Regions.
Memory Usage: 41.3 MB, less than 44.12% of Java online submissions for Surrounded Regions.
*/

public class SolutionV3 {
    public void solve(char[][] board) {
        if (board.length == 0) return;
        int h, w, dummy, x, y, D[][]; UF ns;
        h = board.length; w = board[0].length; dummy = h * w;
        ns = new UF(dummy + 1); D = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int r = 0; r < h; r++) {
            if(board[r][0] == 'O') ns.union(r * w, dummy);
            if(board[r][w-1]=='O') ns.union(r * w + w - 1, dummy);
        }
        for (int c = 0; c < w; c++) {
            if(board[0][c] == 'O') ns.union(c, dummy);
            if(board[h - 1][c] == 'O') ns.union((h-1) * w + c, dummy);
        }

        for (int r = 1; r < h - 1; r++) {
            for (int c = 1; c < w - 1; c++) {
                if(board[r][c] == 'O')
                    for (int i = 0; i < 4; i++) {
                        x = r + D[i][0]; y = c + D[i][1];
                        if( board[x][y] == 'O') ns.union(r * w + c, x * w + y);
                    }
            }
        }

        for (int r = 1; r < h - 1; r++)
            for (int c = 1; c < w - 1; c++)
                if (board[r][c] == 'O' && !ns.connected(r * w + c, dummy)) {
                    System.out.println(r+ " " +  c + " " +board[r][c]);
                    board[r][c] = 'X';
                }
    }


    class UF {
        // 记录连通分量个数
        private int count;
        // 存储若干棵树
        private int[] parent;
        // 记录树的“重量”
        private int[] size;

        public UF(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /* 将 p 和 q 连通 */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;

            // 小树接到大树下面，较平衡
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        /* 判断 p 和 q 是否互相连通 */
        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            // 处于同一棵树上的节点，相互连通
            return rootP == rootQ;
        }

        /* 返回节点 x 的根节点 */
        private int find(int x) {
            while (parent[x] != x) {
                // 进行路径压缩
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public int count() {
            return count;
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X','O','X','X'},
                {'O','X','O','X'},
                {'X','O','X','O'},
                {'O','X','O','X'},
        };
        for (char[] c : board) {
            System.out.println(c);
        }
        new SolutionV3().solve(board);
        for (char[] c : board) {
            System.out.println(c);
        }
    }
}
