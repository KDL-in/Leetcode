package com.leetcode.comm.lcof.q15;
/*
338. Counting Bits
https://leetcode.com/problems/counting-bits/
0-num包含的1的数量
* */
/*
绞尽脑汁想了一个动态规划的方法，其实还有更简单的形式
i&(i-1)是去掉末尾的一个1.
q191的升级版
class Solution {
public:
    vector<int> countBits(int num) {
        vector<int> ret(num+1, 0);
        for (int i = 1; i <= num; ++i)
            ret[i] = ret[i&(i-1)] + 1;
        return ret;
    }
};
* */



class Solution {
    public int[] countBits(int num) {
        int memo[] = new int[num+1];
        for(int i = 1; i <= num; i *= 2){
            memo[i] = 1;
            for (int j = 1; j < i; j++){
                if (i + j > num) return memo;
                memo[i+j] = memo[i] + memo[j];
                
            }
        }
        return memo;
    }
}