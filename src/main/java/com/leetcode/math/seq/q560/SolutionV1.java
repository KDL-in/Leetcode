package com.leetcode.math.seq.q560;

import java.util.HashMap;
import java.util.Map;


/*
$$
sum[i,j] = sum[0, j] - sum[0,i] = k
$$
其中sum[i,j]指[i, j)
那么，每次访问k，只要把当前sum[0,k]当成sum[0,j]，查看map中是否记录有sum[0,i]使得上述公式成立即可。

因为有出现重复累加和，所以还需要用map来记录次数。另外，一开始就需要加入0，表示i=0时的值。
Runtime: 18 ms, faster than 73.01% of Java online submissions for Subarray Sum Equals K.
Memory Usage: 42.1 MB, less than 38.48% of Java online submissions for Subarray Sum Equals K.
* */

class SolutionV1 {
    public int subarraySum(int[] nums, int k) {
        int sum = 0, count = 0;
        Map <Integer, Integer>map = new HashMap();
        for (int num : nums) {
            sum += num;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}