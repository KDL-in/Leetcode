package com.leetcode.dp.subseq.q72;


/*
* 72. Edit Distance
* 编辑距离问题
* https://leetcode.com/problems/edit-distance/
*
* */
/*
https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484731&idx=3&sn=aa642cbf670feee73e20428775dff0b5&chksm=9bd7fb33aca0722568ab71ead8d23e3a9422515800f0587ff7c6ef93ad45b91b9e9920d8728e&scene=21#wechat_redirect
动态规划经典问题。属于子序列问题之一，但是难点在于想明白匹配算法
这里先试试纯递归。
子序列反向匹配的框架
base：当i或j为-1的时候，说明另一个字符串已经结束，这时候只需要全部插入或全部删除步即可完成
状态：i，j
选择：插入，删除，替换
dp：dp（i， j）返回解决i，j状态需要的最小步数
Time Limit Exceeded
*/

class Solution {

    public int dp(int i, int j, String word1, String word2) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        return word1.charAt(i) != word2.charAt(j) ?
            min(dp(i-1, j-1, word1, word2) + 1, dp(i, j - 1, word1, word2) + 1,
                    dp(i - 1, j, word1, word2) + 1) : dp(i - 1, j - 1, word1, word2);
    }

    private int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    public int minDistance(String word1, String word2) {
        return dp(word1.length() - 1, word2.length() - 1, word1, word2);
    }
}