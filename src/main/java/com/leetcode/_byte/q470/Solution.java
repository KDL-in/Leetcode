package com.leetcode._byte.q470;
/*
* 470. Implement Rand10() Using Rand7()
* rand7生成rand10
* https://leetcode.com/problems/implement-rand10-using-rand7/
* */
/*
经典问题：核心是，如何保证概率相同。以前总结过，6生产7的。
1. 奇偶生产01
2. 多次产生多个结果，编码

在这个问题上，就是使用策略2，不过它不需要那么复杂，再加一条。
3. 拒绝策略，直接把不需要的拒绝掉即可。
* */
/*
class Solution extends SolBase {
    public int rand10() {
        int idx, r, c;
        do{
            r = rand7();
            c = rand7();
            idx = (r - 1) * 7 + c;
        } while(idx > 40);
        return idx % 10 + 1;
    }
}*/
