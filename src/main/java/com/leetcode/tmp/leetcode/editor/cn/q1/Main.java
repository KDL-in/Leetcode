package com.leetcode.tmp.leetcode.editor.cn.q1;

import java.util.*;
/*
8
BBRBRBBR
4
RRRR
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        char[] s = sc.nextLine().toCharArray();
        int r, b, cur;
        r = b = cur = 1;
        for (int i = 1; i < n; i++) {
            if (s[i] == s[i-1]) continue;
            cur = Math.min(r, b) + 1;
            if (s[i] == 'R') r = cur;
            else b = cur;
            //System.out.println(i + " " + cur);
        }
        System.out.println(cur);
    }
}