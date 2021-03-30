package com.leetcode.comm.lcof.q39;
/*
* 229. Majority Element II
* 经典解法
* https://leetcode.com/problems/majority-element-ii/
* */
/*
想象同时删除掉不相同的一对数字，最后剩下的一定是众数
* */
class Solution {
    public int majorityElement(int[] nums) {
        int cur = 0, count = 0;
        for(int i = 0; i <  nums.length; ++ i){
            if (count == 0){
                cur = nums[i];
                ++count;
                continue;
            }
            if (cur == nums[i]) ++count;
            else --count;
        }
        return cur;
    }
}