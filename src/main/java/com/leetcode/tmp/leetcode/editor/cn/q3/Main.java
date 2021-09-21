package com.leetcode.tmp.leetcode.editor.cn.q3;

import java.util.Scanner;

/*
AcAer
AcMer
Acaer
eCCaabMqr
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.nextLine().toCharArray();
        char[] target = "AcMer".toCharArray();
        int min = 5;
        for (int i = 0; i <= s.length - 5; i++) {
            min = Math.min(min, check(i, 0, s, target));
        }
        System.out.println(min * 5);
    }

    private static int check(int i, int j, char[] s, char[] t) {
        int res = 0;
        while (j < 5) {
            if (s[i] != t[j]) {
                if (Character.toLowerCase(s[i]) == Character.toLowerCase(t[j])) res += 1;
                else if ((Character.isUpperCase(s[i]) && Character.isUpperCase(t[j]))
                        || (Character.isLowerCase(s[i]) && Character.isLowerCase(t[j]))) res += 1;
                else res += 2;
            }
            i++;
            j++;

        }
        return res;
    }
}
