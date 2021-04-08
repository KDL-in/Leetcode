package com.leetcode.comm.lcof.q56;
/*
* 260. Single Number III
* 除了一组数字之外其他都出现两次，如何在线性复杂度，常数空间解决问题
* https://leetcode.com/problems/single-number-iii/
* */
/*
使用抑或得到目标数字的的异或值
找到异或值中为1的比特位（意味着原来两个数字在该位置上不同）
然后使用该位对原数组进行分组抑或
* */
class Solution {
    public int[] singleNumber(int[] nums) {
        int n = 0;
        for(int i = 0; i < nums.length; ++i) n ^= nums[i];
        int flag = 1;
        while((flag & n) == 0){
            flag <<= 1;
        }
        int a = 0, b = 0;
        for(int i = 0; i < nums.length; ++i){
            if((nums[i] & flag) == 0){
                a ^= nums[i];
            }else{
                b ^= nums[i];
            }
        }
        return new int [] {a, b};
    }
}