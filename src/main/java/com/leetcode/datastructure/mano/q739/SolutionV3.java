package com.leetcode.datastructure.mano.q739;


/*
https://leetcode.com/problems/daily-temperatures/discuss/788835/Java-100-2ms-or-No-extra-memory-or-Explanation
表现最好的解法，但其实是O(N^2)复杂度。不需要使用栈，想法非常简洁直接，实现也很好。
Runtime: 2 ms, faster than 100.00% of Java online submissions for Daily Temperatures.
        Memory Usage: 47.1 MB, less than 79.92% of Java online submissions for Daily Temperatures.
*/

public class SolutionV3 {
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        for (int day = T.length - 1; day >= 0; day--)
            for (int i = day - 1; i >= 0 && T[i] < T[day]; i--)
                result[i] = day - i;
        return result;
    }
}