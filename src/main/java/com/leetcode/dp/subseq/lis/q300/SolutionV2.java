package com.leetcode.dp.subseq.lis.q300;


/*
这是一种巧妙的算法，和一种纸牌游戏的原理一样

> 根据题目的意思，我都很难想象这个问题竟然能和二分查找扯上关系。
其实最长递增子序列和一种叫做 patience game 的纸牌游戏有关。
甚至有一种排序方法就叫做 patience sorting（耐心排序）。
为了简单起见，后文跳过所有数学证明，通过一个简化的例子来理解一下思路。
首先，给你一排扑克牌，我们像遍历数组那样从左到右一张一张处理这些扑克牌，最终要把这些牌分成若干堆。
处理这些扑克牌要遵循以下规则：
只能把点数小的牌压到点数比它大的牌上。
如果当前牌点数较大没有可以放置的堆，则新建一个堆，把这张牌放进去。
如果当前牌有多个堆可供选择，则选择最左边的堆放置。
ref：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484498&idx=1&sn=df58ef249c457dd50ea632f7c2e6e761&source=41#wechat_redirect

这种游戏的特点是，按照上述规则，在游戏任意时刻排队顶总是递增的——因为，若当前值比某排队顶小，则成为
该牌堆的牌堆顶，否则放到右侧的牌堆顶。
有意思的结论是，按这种方式得到的最终牌堆，必然包含最长递增子序列（a，b，……）
而且a必定在堆一，b必定在堆二……
证明：
若a不在堆1，则堆1顶x必定有x < a，且x的索引在a之前，则可以将a替换为x，最长递增子序列为（x，b，……）
同理可以证明，b必定在堆2，c……
从整体来看，若某序列存在最长递增序列，那么这个序列必然会从左到右存放于上述堆中
Runtime: 4 ms, faster than 80.50% of Java online submissions for Longest Increasing Subsequence.
        Memory Usage: 41.4 MB, less than 12.61% of Java online submissions for Longest Increasing Subsequence.
*/

public class SolutionV2 {
    public int lengthOfLIS(int[] nums) {
        int left, right, mid, N;
        int bucket[] = new int[nums.length];
        N = 0;
        for (int x : nums) {
            left = 0;
            right = N;
            while (left < right) {
                mid = (left + right) >> 1;
                if (x > bucket[mid]) left = mid + 1;
                else right = mid;
            }
            bucket[left] = x;
            if (left == N) ++N;
        }
        return N;
    }
}
