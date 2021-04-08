package com.leetcode.comm.lcof.q53;
/*
34. Find First and Last Position of Element in Sorted Array
排序数组查找最先最末的元素
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
* */
/*
二次二分搜索
* */
class Solution {
    public int[] searchRange(int[] nums, int key) {
        int l, r, m;
        int []res = {-1, -1};
        l = 0;
        r = nums.length;
        while(l < r){
            m = l + ((r - l) >> 1);
            if (key > nums[m]) l = m + 1;
            else r = m;
        }
        if (l == nums.length || nums[l] != key) return res;

        res[0] = l;
        l = 0;
        r = nums.length;
        while(l < r){
            m = l + ((r - l) >> 1);
            // key point
            if (key >= nums[m]) l = m + 1;
            else r = m;
        }
        res[1] = l - 1;
        return res;
    }
}