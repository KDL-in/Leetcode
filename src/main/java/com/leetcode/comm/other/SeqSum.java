package com.leetcode.comm.other;
import java.util.Scanner;
/*
* 序列和
* https://www.nowcoder.com/practice/46eb436eb6564a62b9f972160e1699c9?tab=answerKey
*
* */
public class SeqSum {
    public static void main(String[] args){
        int N, L;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        N -= (L-1)*(L-2)/2;
        for (; N > 0 || L <= 100; ++L){
            N -= L-1;
            if((N % L )== 0)break;
        }

        N /= L;
        if(N < 0 || L > 100) System.out.println("No");
        else{
            System.out.print(N);
            for (int i = 1; i < L; ++i) { System.out.print(" " + (N + i)); }

            System.out.println();
        }
    }

}
