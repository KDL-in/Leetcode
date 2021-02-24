package com.leetcode.bfs.q773;

import java.util.*;
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
