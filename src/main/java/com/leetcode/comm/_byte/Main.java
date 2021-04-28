package com.leetcode.comm._byte;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s, t1, t2;
        int c1, c2;
        t1 = "cat";
        t2 = "pig";
        c1 = c2 = 0;
        s = sc.nextLine();
        if (s.length() > 3) System.out.println("horse");
        else {
            for (int i = 0; i < 3; i++) {
                if (t1.charAt(i) == s.charAt(i)) c1++;
                if (t2.charAt(i) == s.charAt(i)) c2++;
            }
            if (c1 > c2) System.out.println(t1);
            else System.out.println(t2);
        }
    }
}
