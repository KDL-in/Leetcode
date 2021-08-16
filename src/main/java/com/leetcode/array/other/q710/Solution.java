package com.leetcode.datastructure.array.other.q710;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
* Z710. Random Pick with Blacklist
* 随机选择[0, n）但不在黑名单中的数字
* https://leetcode.com/problems/random-pick-with-blacklist/
*
* */

/*

Runtime: 43 ms, faster than 47.20% of Java online submissions for Random Pick with Blacklist.
Memory Usage: 49.9 MB, less than 75.47% of Java online submissions for Random Pick with Blacklist.
*/

class Solution {
    private Random random;
    private int N;
    private Map<Integer, Integer> val2idx, idx2val;

    public Solution(int N, int[] blacklist) {
        random = new Random();
        val2idx = new HashMap<>();
        idx2val = new HashMap<>();
        this.N = N;
        for (int x : blacklist) {
            remove(x);
        }
    }

    private void remove(int x) {
        int x_i, last_i, last;
        last_i = N - 1;
        x_i = val2idx.getOrDefault(x, x);
        last = idx2val.getOrDefault(last_i, last_i);
        idx2val.put(x_i, last);
        val2idx.put(last, x_i);
        val2idx.remove(x);
        idx2val.remove(last_i);
        --N;
    }

    public int pick() {
        int idx = random.nextInt(N);
        return idx2val.getOrDefault(idx, idx);
    }
}