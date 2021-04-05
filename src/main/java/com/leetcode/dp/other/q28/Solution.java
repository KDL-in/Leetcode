package com.leetcode.dp.other.q28;

/*
* 28. Implement strStr()
* 字符串匹配，KMP
* https://leetcode.com/problems/implement-strstr/
*
* */
/*
KMP算法
https://www.bilibili.com/video/BV18k4y1m7Ar?from=search&seid=15700617897526094886
Runtime: 3 ms, faster than 34.27% of Java online submissions for Implement strStr().
Memory Usage: 39.4 MB, less than 6.59% of Java online submissions for Implement strStr().
* */
class Solution {
    public int strStr(String haystack, String needle) {
        int i, j, n, m;
        i = j = 0; n = haystack.length();m = needle.length();
        int[] next = genNext(needle);
        while (i < n && j < m) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;j++;
            } else {
                if (j==0) i++;
                else j = next[j - 1];
            }
        }
        if (j==m) return i - m;
        return -1;
    }

    public int[] genNext(String pat) {
        int n, i, j;
        n = pat.length(); i = 0; j = 1;
        int next[] = new int[n];
        while (j < n) {
            if (pat.charAt(i) == pat.charAt(j)) {
                next[j] = i + 1;
                i++;
                j++;
            } else {
                if (i == 0) {
                    next[j++] = 0;
                    i = 0;
                }else
                    i = next[i - 1];
            }
        }
        return next;
    }
}