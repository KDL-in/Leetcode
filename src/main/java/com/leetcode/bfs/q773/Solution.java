package com.leetcode.bfs.q773;

import java.util.*;
/*
* 773. Sliding Puzzle
* 八字码问题
* https://leetcode.com/problems/sliding-puzzle/
* */


/*
BFS写得比较繁琐
Runtime: 9 ms, faster than 62.65% of Java online submissions for Sliding Puzzle.
        Memory Usage: 39.4 MB, less than 21.19% of Java online submissions for Sliding Puzzle.
*/


public class Solution {
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

