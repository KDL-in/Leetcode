package com.leetcode.comm.pdd.q3;

import java.util.Scanner;

class Node {
    Node pre, next;
    char c;

    Node(char c) {
        this.c = c;
    }
}
/*
4
()DD
 */
public class Main {
    public static void main(String[] args) {
        int N;
        char c;
        String blank = "";
        int last = 0;
        String str;
        Node head = new Node('#'), cur = head;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();
        str = sc.nextLine();
        for (int i = 0; i < N; i++) {
            if (i != 0){
                blank = " ";
            }
            c = str.charAt(i);
            if (c == 'R') {
                if (cur.next != null) {
                    cur = cur.next;
                }
                System.out.print(blank + last);
            }
            else if (c == 'L') {
                if (cur.pre != null) {
                    cur = cur.pre;
                }
                System.out.print(blank + last);
            }
            else if(c == 'D') {
                if (cur == head) {
                    System.out.print(blank + last);
                } else {
                    cur = delete(cur);
                    System.out.print(blank + (last = cal(head)));
                }
            } else {
                cur = insert(cur, c);
                System.out.print(blank + (last = cal(head)));
            }
        }
        System.out.println();

    }

    private static int cal(Node head) {
        int max = 0, r = 0;
        boolean flag = true;
        Node cur = head.next;
        while (cur != null) {
            if (cur.c == '(') r++;
            else r--;
            if (r < 0) {
                flag = false;
            }
            max = Math.max(Math.abs(r), max);
            cur = cur.next;
        }
        return flag && r == 0 ? max : -Math.abs(r);
    }

    private static Node insert(Node cur, char c) {
        Node newNode = new Node(c);
        Node next = cur.next;
        cur.next = newNode;
        newNode.pre = cur;
        if (next != null){
            newNode.next = next;
            next.pre = newNode;
        }
        return newNode;
    }

    private static Node delete(Node cur) {
        Node pre = cur.pre;
        pre.next = cur.next;
        if (cur.next != null){
            cur.next.pre = pre;
        }
        return pre;
    }
}
