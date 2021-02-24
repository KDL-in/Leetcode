package com.leetcode.bfs.q773;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
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
