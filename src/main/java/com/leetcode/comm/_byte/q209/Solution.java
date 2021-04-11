package com.leetcode.comm._byte.q209;
/*
* 209. Minimum Size Subarray Sum
* https://leetcode.com/problems/minimum-size-subarray-sum/
* 符合条件的最短数组
* */
/*
滑动窗口
* */
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        Window w = new Window(target);
        int min = (int)1e6;
        for( int i = 0; i < nums.length; ++i){
            w.add(nums[i]);
            while(w.check()){
                min = Math.min(w.size(), min);
                w.remove(nums[i + 1 - w.size()]);
            }
        }
        return min == (int)1e6 ? 0 : min;
    }
}

class Window{
    private int size, sum;
    private int target;
    
    public Window(int target){
        this.target = target;
        size = sum = 0;
    }
    
    public void add(int v){
        sum += v;
        size++;
    }
    
    public boolean check(){
        return sum >= target;
    }
    
    public void remove(int v){
        sum -= v;
        size--;
    }
    
    public int size(){
        return size;
    }
}