package com.leetcode.comm.other;

import java.util.Arrays;
import java.util.Scanner;
/*
* 吃葡萄
* 有意思的题目，三个人需要吃完所有三种葡萄，但要求尽可能平均
* 每个人只能吃期中两种
* https://www.nowcoder.com/questionTerminal/14c0359fb77a48319f0122ec175c9ada?f=discussion
* 使用三角形来解
* https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247486666&idx=1&sn=08edde4151b296ae2871ad89142a6ad3&chksm=9bd7f2c2aca07bd4c22cce610c862fc8e4432901bae6f67655e241af5e6940a512bd7216caac&scene=21#wechat_redirect
* */
public class EatFruit {
    public static void main(String[] args) {
        int N;
        long a, b, c;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            N = sc.nextInt();
            while (N-- > 0) {
                a = sc.nextLong();
                b = sc.nextLong();
                c = sc.nextLong();
                System.out.println(eatFruit(new long[]{a, b, c}));
                ;
            }
        }
    }

    private static long eatFruit(long[] num) {
        Arrays.sort(num);
        if (2*(num[0] + num[1]) < num[2] ) return (num[2] + 1) / 2;
        return (num[0]+num[1]+num[2]+2)/3;
    }
}
