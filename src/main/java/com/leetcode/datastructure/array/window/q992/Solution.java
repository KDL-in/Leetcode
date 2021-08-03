package com.leetcode.datastructure.array.window.q992;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int l, r, m;
        Set<Integer> set = new HashSet<>();
        l = r = m = 0;
        while (r < nums.length) {
            set.add(nums[r++]);
            if (set.size() < k) continue;
            else if(set.size() == k) m++;
            else {
                while (set.size() > k){
                    set.remove(nums[l++]);
                }
                m++;
            }
        }
        return m;
    }
}