package com.leetcode.comm.q4399;

import java.util.Scanner;
/*
1.2.3.4
0.a.2.3

* */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s;
        s = sc.nextLine();
        System.out.println(check(s));
    }

    private static boolean check(String s) {
        int t = 0;
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '.') {
                // System.out.println(t);
                if (t < 0 || t > 255) return false;
                t = 0;
                continue;
            }
            if ('0' <= c && c <= '9') t = t * 10 + (c - '0');
            else return false;
        }
        return true;
    }
}
