package com.leetcode.comm.lcof.q39;
/*
利用partition来查找位于（n + 1）/ 2 + 1位置。好的情况下O(N)可以解决，差的情况下需要O(1)

此处可以作为partition的模板，这里写起来非常对称和直观。
* */
class SolutionV2 {
    public int majorityElement(int[] nums) {
        int i = 0, j = nums.length - 1, target = ((nums.length + 1) >> 1) - 1, idx;
        
        while((idx = partition(i, j, nums)) != target){
            //System.out.println(idx + " " + nums[idx]);
            if (idx > target) j = idx - 1;
            if (idx < target) i = idx + 1;
        }
        
        return nums[idx];
    }
    
    private int partition(int i, int j, int[] nums){
        int key = i;
        while(i < j){
            while(i < nums.length && nums[i] <= nums[key])i++;
            while(j >= 0 && nums[j] > nums[key])j--;
            if(i < j) swap(i, j, nums);
        }
        swap(key, j, nums);
        return j;
    }
    
    private void swap(int i, int j, int[] nums){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}