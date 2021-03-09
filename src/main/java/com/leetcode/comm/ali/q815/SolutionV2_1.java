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
切换访问时机，探测时访问
Runtime: 71 ms, faster than 32.80% of Java online submissions for Bus Routes.
Memory Usage: 97.2 MB, less than 8.98% of Java online submissions for Bus Routes.
* */
class SolutionV2_1 {
    
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
        if(source == target) return step;
        while(!q.isEmpty()){
            int size = q.size();
            ++step;
            while( size-- > 0 ){
                int city = q.poll();
                
                Set<Integer> buses = map.getOrDefault(city, null);
                if(buses==null) continue;
                for (int bus : buses){
                    if(vis.contains(bus)) continue;
                    for(int arrCity : routes[bus]){
                        if(arrCity == target) return step;
                        q.add(arrCity);
                    }
                    vis.add(bus);
                }
                
            }
            
        }
        
        return -1;
    }
}