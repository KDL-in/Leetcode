package com.leetcode.tmp.leetcode.editor.cn.q3;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/*
meituan
uta
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char s[] = sc.nextLine().toCharArray();
        //sc.nextLine();
        char a[] = sc.nextLine().toCharArray();
        //sc.nextLine();
        int i = 0, j = 0, res = 0;
        Set<Character> set = new HashSet<>();
        for (int k = 0; k < s.length; k++) {
            set.add(s[k]);
        }
        while (j < a.length) {
            i = i % s.length;
            if (!set.contains(a[j])){
                res = -1;
                break;
            }
            if (s[i] == a[j]){
                i++;
                j++;
            }else {
                res ++;
                i++;
            }
        }
        System.out.println(res);
    }
}
