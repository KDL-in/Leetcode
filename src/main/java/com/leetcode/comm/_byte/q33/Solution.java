package com.leetcode.comm._byte.q33;
/*
* 33. Search in Rotated Sorted Array
* 在旋转数组中查找
* https://leetcode.com/problems/search-in-rotated-sorted-array/
*
* */
/*
二分搜索查找旋转边界：左边一定比右边低，然后二分搜索之

然后二分搜索，简单偏移一下即可

* */
class Solution {
    public int search(int[] nums, int target) {
        int l, r, mid, k, n;
        l = 0;
        n = nums.length;
        r = n;
        while(l != r){
            mid = l + ((r - l) >> 1);
            if (nums[l] < nums[mid]) l = mid;
            else r = mid;
        }
        k = l + 1;
        l = 0;
        r = n;
        while(l < r){
            mid = l + ((r - l) >> 1);
            if (nums[(mid + k) % n] < target) l = mid+1;
            else r = mid;
        }
        return nums[(l + k)%n] == target ?(l+k)%n:-1;  
    }
}