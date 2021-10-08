#### 773. Sliding Puzzle

```
/*
* 773. Sliding Puzzle
* 八字码问题
* https://leetcode.com/problems/sliding-puzzle/
* */
```

经典问题，八字码问题，bfs求最小步数。

bfs模板，状态表示（字符串），双向bfs模板。

**解法一，类+覆盖方法**

```java
/*
BFS写得比较繁琐
Runtime: 9 ms, faster than 62.65% of Java online submissions for Sliding Puzzle.
        Memory Usage: 39.4 MB, less than 21.19% of Java online submissions for Sliding Puzzle.
*/


public class com.leetcode.dp.q91.Solution {
    private int N, M;

    public int slidingPuzzle(int[][] board) {
        int x, y;
        N = board.length; M = board[0].length;
        int[][] direction = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        Set<String> vis = new HashSet<>();
        Node start = new Node(board);
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        vis.add(start.toString());
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if ("123450".equals(cur.toString())) return cur.step;
                for (int j = 0; j < 4; j++) {
                    x = cur.i + direction[j][0];
                    y = cur.j + direction[j][1];
                    if (x < 0 || x == N || y < 0 || y == M)continue;
                    Node n = cur.move(x, y);
                    if (vis.contains(n.toString())) continue;
                    vis.add(n.toString());
                    q.offer(n);
                }
            }
        }
        return -1;
    }



    class Node implements Cloneable {
        public int step;
        char[][] state;
        int i, j;
        private String str;

        public Node(int[][] state) {
            this.state = new char[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (state[i][j] == 0) {
                        this.i = i;
                        this.j = j;
                    }
                    this.state[i][j] = (char) ('0'+state[i][j]);
                }
            }
        }

        Node move(int x, int y) {
            Node newNode = (Node) this.clone();
            newNode.state[i][j] = state[x][y];
            newNode.state[x][y] = state[i][j];
            newNode.i = x;
            newNode.j = y;
            newNode.step++;
            return newNode;
        }

        @Override
        protected Object clone(){
            Node node = null;
            try {
                node = node = (Node) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            assert node != null;
            node.str = null;
            node.state = new char[N][];
            for (int k = 0; k < N; k++) {
                node.state[k] = Arrays.copyOf(this.state[k], M);
            }
            return node;
        }

        @Override
        public String toString() {
            if (str!=null) return str;
            StringBuilder s = new StringBuilder();
            for (int k = 0; k < N; k++) {
                s.append(state[k]);
            }
            return str = s.toString();
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }
    }
}
```

**解法二，简洁的实现，模板**

```java
/*
简洁的实现
Runtime: 4 ms, faster than 98.61% of Java online submissions for Sliding Puzzle.
        Memory Usage: 38.4 MB, less than 81.83% of Java online submissions for Sliding Puzzle.
*/

public class SolutionV2 {
    public int slidingPuzzle(int[][] board) {
        int step = 0, size;
        String target = "123450", str;
        int neb[][] = {
                {1, 3},{2,4,0},{5,1},{4,0},{5,3,1},{4,2}
        };
        Queue<Node> q = new LinkedList<>();
        Set<String> vis = new HashSet<>();
        StringBuilder s = new StringBuilder();
        for (int[] row : board) for (int c : row) s.append(c);
        Node cur = new Node(0, s.toString());
        cur.i = cur.s.indexOf('0');

        q.add(cur);
        vis.add(cur.s);
        while (!q.isEmpty()) {
            size = q.size();
            for (int i = 0; i < size; i++) {
                cur = q.poll();
                if (target.equals(cur.s)) return step;
                for (int next : neb[cur.i]) {
                    str = swap(cur.i, next, cur.s);
                    if (!vis.contains(str)) {
                        Node n = new Node(next, str);
                        vis.add(str);
                        q.offer(n);
                    }
                }
            }
            ++step;
        }

        return -1;
    }

    private String swap(int i, int j, String s) {
        char[] chars = s.toCharArray();
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
        return new String(chars);
    }

    class Node {
        int i;
        String s;

        public Node(int i, String s) {
            this.i = i;
            this.s = s;
        }
    }

}
```

**解法三，双向队列**

```java
/*
双向队列，双队列版本，注意访问时机的确定（即访问标记）,该版本容易出错
Runtime: 11 ms, faster than 55.84% of Java online submissions for Sliding Puzzle.
Memory Usage: 38.9 MB, less than 43.08% of Java online submissions for Sliding Puzzle.
*/

public class SolutionV3 {
    public int slidingPuzzle(int[][] board) {
        int step = -1, size;
        String target = "123450", start, str;
        int neb[][] = {
                {1, 3},{2,4,0},{5,1},{4,0},{5,3,1},{4,2}
        };

        StringBuilder s = new StringBuilder();
        for (int[] row : board) for (int c : row) s.append(c);
        start = s.toString();
        Queue<Node> q1 = new LinkedList<>(), q2 = new LinkedList<>(), qt;
        Map<String, Boolean> vis = new HashMap<>();
        q1.add(new Node(start.indexOf('0'), start));
        q2.add(new Node(5, target));

        boolean curFlag = true; // 标志是原q1加入的还是原q2加入的
        while (!q1.isEmpty() && !q2.isEmpty()) {
            size = q1.size();
            System.out.println("==========");
            for (int i = 0; i < size; ++i) {
                Node cur = q1.poll();
                System.out.println(cur.s);
                if (vis.containsKey(cur.s) && vis.get(cur.s) != curFlag) return step;
                vis.put(cur.s, curFlag);
                for (int next : neb[cur.i]) {
                    str = swap(cur.i, next, cur.s);
                    if (vis.containsKey(str)) continue;
                    q1.offer(new Node(next, str));
                }
            }
            ++step;
            curFlag = !curFlag;
            qt = q1;
            q1 = q2;
            q2 = qt;
        }

        return -1;
    }

    public static void main(String[] args) {
        int input[][] = {
                // {1, 2, 3}, {4, 0, 5}
                {4, 1, 2}, {5, 0, 3}
        };
        System.out.println(new SolutionV3().slidingPuzzle(input));
    }

    private String swap(int i, int j, String s) {
        char[] chars = s.toCharArray();
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
        return new String(chars);
    }

    class Node {
        int i;
        String s;

        public Node(int i, String s) {
            this.i = i;
            this.s = s;
        }
    }

}
```

**解法四，双向队列，模板**

```java
/*
双hash表版本，q1，q2记录的是探测队列，vis记录的是访问过的节点，防止重复访问
tip: 如果访问时机难以抉择，可以用一个两步的状态树手动debug
Runtime: 5 ms, faster than 85.23% of Java online submissions for Sliding Puzzle.
Memory Usage: 38.1 MB, less than 92.50% of Java online submissions for Sliding Puzzle.
* */
public class SolutionV4 {
    public int slidingPuzzle(int[][] board) {
        int step = 0, size;
        String target = "123450", start, str;
        int neb[][] = {
                {1, 3},{2,4,0},{5,1},{4,0},{5,3,1},{4,2}
        };

        StringBuilder s = new StringBuilder();
        for (int[] row : board) for (int c : row) s.append(c);
        start = s.toString();
        Set<Node> q1 = new HashSet<>(), q2 = new HashSet<>();
        Set<String> vis = new HashSet<>();
        q1.add(new Node(start.indexOf('0'), start));
        q2.add(new Node(5, target));

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<Node> tq = new HashSet<>();
            for (Node cur : q1) {
                if (q2.contains(cur)) return step;
                vis.add(cur.s);
                for (int next : neb[cur.i]) {
                    str = swap(cur.i, next, cur.s);
                    if (vis.contains(str)) continue;
                    tq.add(new Node(next, str));
                }
            }
            ++step;
            if (q2.size() < tq.size()) {
                q1 = q2;
                q2 = tq;
            } else {
                q1 = tq;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int input[][] = {
                // {1, 2, 3}, {4, 0, 5}
                {4, 1, 2}, {5, 0, 3}
        };
        System.out.println(new SolutionV4().slidingPuzzle(input));
    }

    private String swap(int i, int j, String s) {
        char[] chars = s.toCharArray();
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
        return new String(chars);
    }

    class Node {
        int i;
        String s;

        public Node(int i, String s) {
            this.i = i;
            this.s = s;
        }

        @Override
        public int hashCode() {
            return s.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return s.equals(((Node)obj).s);
        }
    }

}
```