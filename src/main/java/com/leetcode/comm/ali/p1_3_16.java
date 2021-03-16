package com.leetcode.comm.ali;

import java.util.Arrays;
import java.util.Scanner;
/*
2
2 6 5
1 2 3

*/

public class p1_3_16 {
    public static void main(String[] args) {

        int T, a, b, c,count;
        int[] as, bs, cs;
        as = new int[65];
        bs = new int[65];
        cs = new int[65];
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        while (T-- > 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            Arrays.fill(as, 0);
            Arrays.fill(bs, 0);
            Arrays.fill(cs, 0);
            count = 0;
            for (int i = 0; a != 0; i++) {
                as[i] = a & 1;
                a >>= 1;
            }
            for (int i = 0; b != 0; i++) {
                bs[i] = b & 1;
                b >>= 1;
            }
            for (int i = 0; c != 0; i++) {
                cs[i] = c & 1;
                c >>= 1;
            }

            for (int i = 0; i < 65; i++) {
                // System.out.println(as[i] + " " + bs[i] + " " + cs[i]);
                if (cs[i] == 1 && bs[i] == 0 && as[i] == 0) {
                    count++;
                    // System.out.println(count);
                }
                else if(cs[i] == 0) {
                    count += as[i] + bs[i];
                    // System.out.println(count);
                }
            }
            System.out.println(count);

        }
    }
}
