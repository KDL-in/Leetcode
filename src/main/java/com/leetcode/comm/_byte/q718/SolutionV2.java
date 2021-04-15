package com.leetcode.comm._byte.q718;
/*
另类滑动窗口，两把尺子相互移动
https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/solution/wu-li-jie-fa-by-stg-2/
* */
class SolutionV2 {
    public int findLength(int[] nums1, int[] nums2) {
        int i,j,max;
        if(nums2.length > nums1.length){
            int[]t = nums1;
            nums2 = nums1;
            nums1 = t;
        }
        max = 0;
        for (int len = 1; len <= nums2.length; ++len){
            i = 0;
            j = nums2.length - len;
            max = Math.max(find(i, j, nums1, nums2), max);
        }

        for(i = 1; i < nums1.length; ++i){
            max = Math.max(find(i, 0, nums1, nums2), max);
        }
        return max;
    }

    private int find(int i, int j, int[]nums1, int[]nums2){
        int max = 0;
        int r = 0;
        for(; i < nums1.length && j < nums2.length; ++i, ++j){
            if(nums1[i] != nums2[j]) {r = 0; continue;}
            r++;
            max = Math.max(max, r);
        }
        return max;
    }
}