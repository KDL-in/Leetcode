package com.leetcode.dp.lis.q354;

import java.util.Arrays;


/*
lis棋牌游戏二分解法
此题的关键是，当你将w按升序排序，h按降序排序，问题就直接可以转换成字符串最长字序列
——不改变元素相对位置，直接找h升序序列。
Runtime: 8 ms, faster than 98.25% of Java online submissions for Russian Doll Envelopes.
        Memory Usage: 40.2 MB, less than 38.40% of Java online submissions for Russian Doll Envelopes.
*/

public class SolutionV2 {
    public int maxEnvelopes(int[][] envelopes) {
        int left, right, mid, N;
        int bucket[] = new int[envelopes.length];
        N = 0;
        Arrays.sort(envelopes, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        for (int i = 0; i < envelopes.length; i++) {
            left = 0;
            right = N;
            while (left < right) {
                mid = (left + right) >> 1;
                if (envelopes[i][1] > bucket[mid]) left = mid + 1;
                else right = mid;
            }
            bucket[left] = envelopes[i][1];
            if (left == N) ++N;
        }
        return N;
    }
}
