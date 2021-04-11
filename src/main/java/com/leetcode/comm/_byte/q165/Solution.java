package com.leetcode.comm._byte.q165;
/*
* 165. Compare Version Numbers
* https://leetcode.com/problems/compare-version-numbers/
* 比较版本号
*
* */
/*
字符串数字转换
* */
class Solution {
    public int compareVersion(String v1, String v2) {
        int i = 0, j = 0, n = v1.length(), m = v2.length(), t1 = 0, t2 = 0;
        while(i < n || j < m){
            t1 = t2 = 0;
            while(i < n && v1.charAt(i) != '.') t1 = t1 * 10 + (v1.charAt(i++) - '0');
            while(j < m && v2.charAt(j) != '.') t2 = t2 * 10 + (v2.charAt(j++) - '0');
            ++i;
            ++j;
            if (t1 > t2) return 1;
            if (t1 < t2) return -1;
        }
        return 0;
    }
}