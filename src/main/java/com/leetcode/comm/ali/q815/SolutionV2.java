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
BFS
多个循环被折叠起来，算法复杂度不是很好算。
每个bus都只会访问一次，每个bus能到达的城市至少访问一次，所以整体上应该是O(MN)
Runtime: 76 ms, faster than 31.09% of Java online submissions for Bus Routes.
Memory Usage: 93.1 MB, less than 11.33% of Java online submissions for Bus Routes.
* */
class SolutionV2 {


    public int numBusesToDestination(int[][] routes, int source, int target) {
        int step = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> vis = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < routes.length; ++i){
            for(int j = 0; j < routes[i].length; ++j){
                Set<Integer> cur = map.getOrDefault(routes[i][j], null);
                if(cur == null){
                    cur = new HashSet<>();
                    map.put(routes[i][j], cur);
                }
                cur.add(i);
            }
        }
        q.add(source);
        while(!q.isEmpty()){
            int size = q.size();
            while( size-- > 0 ){
                int city = q.poll();
                if(city == target) return step;
                Set<Integer> buses = map.getOrDefault(city, null);
                if(buses==null) continue;
                for (int bus : buses){
                    if(vis.contains(bus)) continue;
                    for(int arrCity : routes[bus]){
                        q.add(arrCity);
                    }
                    vis.add(bus);
                }

            }
            ++step;
        }

        return -1;
    }
}