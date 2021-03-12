package com.leetcode.comm.ali.q1388;

import java.util.Arrays;

/*
* 1388. Pizza With 3n Slices
* 3n披萨
* https://leetcode-cn.com/problems/pizza-with-3n-slices/
* */
/*
刚刚看这道题有点像打家劫舍二，但是这题有限状态机反而不好做，原因是因为你一旦原则，后续的数组本身会发生变化，这时候
很难决定当前两个状态(选或不选)来源于之前的哪些状态，当然这是我的判断而已啦。

然后这道题需要转换为等价问题 = 给定3n数组，找到n个数不相邻和最大。

为什么可以这么转换呢？
- 对于n=1，即有3个元素的时候，选一个，另外两个必为空，在环型数组上，必定有两个相邻连续的没选的为0.
- 对于n>1，必定能找到一个元素，相邻的连续两个没选，例如xx1x或x1xx，为什么呢，你可以手动模拟一下，
当消去一定元素之后，一定会剩下3个元素的case，这时候同第一个说明，即必定存在xx1x或x1xx这种情形
- 有了上述结论，每次消去x1x或x1x，那么n=n-1，则问题规模变小，一直消去，消去过程一定有，1选中，相邻的两个元素为x
即，选择的数彼此不相邻。

然后动态规划就好做了，以下是无状态优化的递归形式，最好理解了

$$
dp(i, j) = max(dp(i-2, j-1, slices)+slices[j], dp(i-1, j, slices));
$$

然后另外一个问题，环型数组该怎么办，和打家劫舍二一样，考虑首尾不可能同时取到，只需要
计算一遍0:n-1和1:n即可。

Runtime: 7 ms, faster than 46.55% of Java online submissions for Pizza With 3n Slices.
Memory Usage: 38.4 MB, less than 87.07% of Java online submissions for Pizza With 3n Slices.
Next challenges:
* */
class Solution {
    private int [][] memo;
    public int maxSizeSlices(int[] slices) {
        int n, n3, s1, s2;
        n3 = slices.length; n = n3 / 3;
        memo = new int[n3][n+1];
        s1 = dp(n3-2, n, slices);
        for (int i = 0; i < n3; i++) Arrays.fill(memo[i],0);
        s2 = dp(n3-2, n, Arrays.copyOfRange(slices, 1, n3));
        return Math.max(s1, s2);
    }
    private int dp(int i, int j , int[] slices){
        if(i < 0||j < 0) return 0;
        if(memo[i][j]!=0) return memo[i][j];
        memo[i][j] = Math.max(dp(i-2, j-1, slices)+slices[j], dp(i-1, j, slices));
        return memo[i][j];
    }
}