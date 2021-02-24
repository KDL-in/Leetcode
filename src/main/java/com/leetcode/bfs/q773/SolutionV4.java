package com.leetcode.bfs.q773;

import java.util.HashSet;
import java.util.Set;
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