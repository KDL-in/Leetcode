package com.leetcode.dp.game.q1686;

import java.util.Arrays;

/*
 * 1686. Stone Game VI
 * 石头游戏六
 * https://leetcode.com/problems/stone-game-vi/
 *
 * */
/*
贪心，贪心策略为，每次选择v1+v2最大的石头，因为选择某一石头，除了得到v1还使得对方减少v2

从动态规划的角度考虑，最高分数 = 当前选择石子 +　递归求最高分数（剩余石头）
由于当前选择石子有ｎ种选择，该方法复杂度会很高，而且也不好实现。

从贪心角度考虑，不需要遍历ｎ种选择，直接确定最佳选择。

证明：

假设当前分数为　S1, S2，当前贪心选择最高得分v = v1 +　v2，
若最优选择为贪心选择以外的任意选择v'=v1'+v2'，v > v',
则有贪心选择A,B二人得分为，$s1+v1, s2+v2$。最优选择二人得分为$s1'+v1', s2'+v2'$，则两种选择之后A,B获得总值差为
$$
    (s1+v1)-(s2+v2') = (s1-s2) + (v1-v2') //
    (s1+v1')-(s2+v2) = (s1-s2) + (v1'-v2)
$$
上式-下式，得到
$$
    (v1-v2')-(v1'-v2) = (v1+v2)-(v1'+v2')
$$
结果大于0，说明A使用贪心选择得到更优解，则与假设相反，证明完毕。

Runtime: 71 ms, faster than 89.27% of Java online submissions for Stone Game VI.
        Memory Usage: 49.3 MB, less than 88.41% of Java online submissions for Stone Game VI.
*/

class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length, s1 = 0, s2 = 0;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            nodes[i].idx = i;
            nodes[i].val = aliceValues[i] + bobValues[i];
        }
        Arrays.sort(nodes, (a, b) -> (b.val-a.val));
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) s1 += aliceValues[nodes[i].idx];
            else s2 += bobValues[nodes[i].idx];
        }
        return s1 > s2 ? 1 : ((s1 == s2) ? 0 : -1);
    }

    class Node {
        int idx, val;
    }

    // public static void main(String[] args) {
    //     int a[] = {2, 4, 3};
    //     int b[] = {1, 6, 7};
    //     System.out.println(new Solution().stoneGameVI(a, b));
    // }
}