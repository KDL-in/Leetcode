package com.leetcode.comm.q4399;

import java.util.Scanner;
/*
a b c
a
* */
public class MainV2 {
    public static void main(String[] args) {
        String s;

        boolean map[] = new boolean[255];

        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        for (int i = 0; i < s.length(); i++) {
            char t = s.charAt(i);
            if (t == ' ') continue;
            map[t] = true;
        }
        while (sc.hasNext()) {
            s = sc.nextLine();
            if (map[s.charAt(0)]) System.out.println("true");
            else System.out.println("false");
        }

    }
}
