package com.leetcode._byte.q93;

import java.util.ArrayList;
import java.util.List;
/*
* 93. Restore IP Addresses
* 有效的IP
* https://leetcode.com/problems/restore-ip-addresses/
* */
/*
回溯解决
* */
class Solution {
    private List<String> res;

    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        backtrack(0, 0, s, new String[4]);
        return res;
    }

    private void backtrack(int i, int k, String s, String[] cur) {
        if (i == s.length() && k == 4) {
            res.add(String.join(".", cur));
            return;
        } else if (i == s.length() || k == 4) return;

        for (int j = 1; j <= 3 && i + j <= s.length(); j++) {
            if (s.charAt(i) == '0' && j != 1) break;
            String t = s.substring(i, i + j);
            if (Integer.parseInt(t) > 255) break;
            cur[k] = t;
            backtrack(i + j, k + 1, s, cur);

        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().restoreIpAddresses("25525511135"));
        // System.out.println(String.join(".", new String[]{"255", "11"}));
    }
}