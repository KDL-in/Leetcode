package com.leetcode.comm._byte.q162;
/*
*
* 162. Find Peak Element
* 峰值搜索，二分搜索
* https://leetcode.com/problems/find-peak-element/
* */
class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1, m;
        while(l < r){
            // 注意加好括号，>>优先级很慢。
            m = l + ((r - l) >> 1);
            //System.out.println(l + " " + r + " " + m + " " + nums[m]);
            if (nums[m] > nums[m + 1]) r = m;
            else l = m + 1;
            
        }
        return l;
    }
}