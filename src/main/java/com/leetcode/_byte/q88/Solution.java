package com.leetcode._byte.q88;

/*
* 88. Merge Sorted Array
* 归并数组
* https://leetcode.com/problems/merge-sorted-array/
* */

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i, j, k ;
        k = j = 0;
        i = n;
        System.arraycopy(nums1, 0, nums1, n, m);
        while( i < m && j < n){
            nums1[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        while(j < n) nums1[k++] = nums2[j++];
    }
}