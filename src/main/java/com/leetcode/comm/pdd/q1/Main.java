package com.leetcode.comm.pdd.q1;
/*
3
1 1 1
1 1 2
3 4 5

 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N, x, y, r, res;
        double d;
        N = in.nextInt();
        while (N-- > 0) {
            x = Math.abs(in.nextInt());
            y = Math.abs(in.nextInt());
            r = Math.abs(in.nextInt());
            res = 0;
            d = Math.sqrt(x * x + y * y);
            if (r > x) res += 2;
            else if(r == x) res += 1;
            if (r > y) res += 2;
            else if(r == y) res += 1;
            if (d == r) res--;
            System.out.println(res);
        }
    }
}