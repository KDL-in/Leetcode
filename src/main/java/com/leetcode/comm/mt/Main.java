package com.leetcode.comm.mt;

import java.util.Scanner;
public class Main{
    public static void main(String[]args){
        int m,n;
        boolean flag;
        int flower[] = new int[1000];
        int q3[] = new int[10];
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 10; ++i) q3[i] = i*i*i;
        for(int i = 100; i <= 999; ++i) flower[i] = cal(i, q3);
        while(sc.hasNext()){
            m = sc.nextInt();
            n = sc.nextInt();
            flag = true;
            for(int i = m; i<=n; ++i)
                if(flower[i] == i){
                    if(flag) {
                        System.out.print(i);
                        flag = false;
                    }else System.out.print(" " + i);
                }
            if(flag) System.out.println("no");
            else System.out.println();
        }
    }
    private static int cal(int n, int []q3){
        int sum = 0, d = 0;
        while(n>0){
            sum += q3[n%10];
            n /= 10;
        }
        return sum;
    }
}