package com.leetcode.comm.lcof.q45;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.CountDownLatch;

/*
* 179. Largest Number
* 排序合并成最大的数
* https://leetcode.com/problems/largest-number/
*
* */
/*
这排序的Comparator比较精彩，利用字符串简化了实现——本来就需要按位对比。

按最高位对比，如果最高位一直，位数小的大。
* */
class Solution {
    public String largestNumber(int[] nums) {
        String numStrs[] = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numStrs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String ab = a + b;
                String ba = b + a;
                return ba.compareTo(ab);
            }
        });
        if (numStrs[0].equals("0")) {
            return "0";
        }
        String res = "";
        for (String s : numStrs) {
            res += s;
        }

        return res;
    }
}