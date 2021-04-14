package com.leetcode.comm.other;

import java.util.Scanner;
import java.util.Stack;
/*
3+2*2
* */
public class Main {

    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        System.out.println(new Main().calculate(s));
    }

    public int calculate(String s) {
        String tmp[] = {
                "=<<<<",
                ">>><<",
                ">>><<",
                ">>>>>",
                ">>>>>"
        };
        String ops = "#+-*/";
        char prio[][] = new char[256][256];
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                prio[ops.charAt(i)][ops.charAt(j)] = tmp[i].charAt(j);
            }
        }
        return postCal("#" + s + "#", prio);
    }

    private int postCal(String s, char[][] prio) {
        int i = 0, n = s.length(), t, a, b;
        char o;
        Stack<Character> op = new Stack();
        Stack<Integer> num = new Stack<>();
        op.push(s.charAt(i++));
        while (i < n) {
            o = s.charAt(i);
            if (o == ' ') {
                ++i;
                continue;
            }
            if (Character.isDigit(o)) {
                t = 0;
                do {
                    t = t * 10 + (s.charAt(i) - '0');
                } while (Character.isDigit(s.charAt(++i)));
                num.push(t);
                continue;
            }
            if (prio[op.peek()][o] == '=') {
                op.pop();
                ++i;
            } else if (prio[op.peek()][o] == '>') {
                b = num.pop();
                a = num.pop();
                num.push(cal(a, b, op.pop()));
            } else {
                op.push(o);
                ++i;
            }
        }
        return num.empty() ? 0 : num.peek();
    }

    private int cal(int a, int b, char o) {
        // System.out.println(a + " " + o + " " + b);
        switch (o) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return -1;
    }

}
