package com.leetcode._byte.q128;

import java.util.HashSet;
import java.util.Set;
/*
* 128. Longest Consecutive Sequence
* 最长连续子序列，不需要顺序
* https://leetcode.com/problems/longest-consecutive-sequence/
* */
/*
能想到最快的方式就是排序，效率O(NlogN)；

以下一种利用hash达成的O(N)的思路。数组建立hashset，遍历数组，在每一次的x处，查找是否
存在x+1，但这种方式实际上是O(N^2)。
考虑到5 4 3 2 1这种极限的情况，实际上只需要查找1就好，因此这里一个非常强大的trick
是检查x-1，如果x-1存在，那么表示当前数不必进行搜索，因为后面搜索到x-1的时候必然不会错过它
效率降到O(N）
* */
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int r = 0, rt;
        for(int x: nums){
            set.add(x);
        }
        for(int x: nums){
            if(!set.contains(x-1)){
                rt = 1;
                while(set.contains(x+1)){
                    x ++;
                    rt ++;
                }
                r = Math.max(rt, r);
            }
        }
        return r;
    }
}