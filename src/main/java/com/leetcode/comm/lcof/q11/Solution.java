package com.leetcode.comm.lcof.q11;
/*
154. Find Minimum in Rotated Sorted Array II
找到旋转数组的最小值
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

* */
/*
这道题二分很难写对，主要是存在相同的情况，你搞不清楚它是哪个方向。

所以遇到这种情况，只能一点一点往左诺。

* */
class Solution {
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid = 0;
        
        while(lo < hi) {
            mid = lo + (hi - lo) / 2;
            
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            }
            else if (nums[mid] < nums[hi]) {
                hi = mid;
            }
            else { // when num[mid] and num[hi] are same
                hi--;
            }
        }
        return nums[lo];
    }
}