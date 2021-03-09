package com.leetcode.comm.ali.q815;

import java.util.*;

/*
 * 815. Bus Routes
 * 图， bfs
 * https://leetcode.com/problems/bus-routes/
 *
 *
 * */

/*
超时，O(N^3)
* */
class Solution {

    private void connect(int i, int j, Map<Integer, Set<Integer>> adj) {
        Set<Integer> set;
        set = adj.getOrDefault(i, null);
        if (set == null) {
            set = new HashSet<>();
            adj.put(i, set);
        }
        set.add(j);
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        Set<Integer> vis = new HashSet();
        Queue<Integer> q = new LinkedList();
        Map<Integer, Set<Integer>> adj = new HashMap();
        int step = 0;
        for (int[] route : routes) {

            for (int i = 1; i < route.length; ++i) {
                for (int j = i - 1; j >= 0; --j) {
                    connect(route[j], route[i], adj);
                    connect(route[i], route[j], adj);
                }
            }
        }
        q.offer(source);
        vis.add(source);
        while (!q.isEmpty()) {
            ++step;
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                int k = q.poll();
                if (k == target) return step;
                if (!adj.containsKey(k)) continue;
                Set<Integer> curAdj = adj.get(k);
                for (int cur : curAdj) {
                    if (!vis.contains(cur)) {
                        vis.add(cur);
                        q.add(cur);
                    }
                }
            }
        }
        return -1;
    }
}