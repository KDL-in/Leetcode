package com.leetcode._byte.q503下一个更大的数;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public int nextGreaterElement(int n) {
        int length = 0, idx, tmp;
        List<Integer> arr = new ArrayList<>();
        int next[];
        // 提取数字
        while (n > 0){
            arr.add(n % 10);
            n /= 10;
            length++;
        }
        Collections.reverse(arr);
        System.out.println(arr.toString());
        // 找到每个位置上下一个更大的数
        next = new int[length];
        for (int i = 0; i < length; i++) {
            idx = i;
            for (int j = i + 1; j < length; j++) {
                if (arr.get(j) > arr.get(i)){
                    if (idx == i) idx = j;
                    else if (arr.get(idx) >= arr.get(j)) idx = j;
                }
            }
            next[i] = idx;
            System.out.print(next[i] + " ");
        }
        System.out.println();
        // 找到最右边一个有更大数的位置
        for( idx = length - 1; idx >= 0; --idx){
            if (next[idx] != idx) break;
        }
        if (idx == -1) return -1;
        System.out.println(idx);
        // 交换位置
        tmp = arr.get(idx);
        arr.set(idx, arr.get(next[idx]));
        arr.set(next[idx], tmp);
        // 排序剩余位置
        for (int i = idx + 1; i < length; ++i){
            idx = i;
            for (int j = i; j < length; j++) {
                if (arr.get(j) < arr.get(i)) {
                    idx = j;
                }
            }
            tmp = arr.get(i);
            arr.set(i, arr.get(idx));
            arr.set(idx, tmp);
        }
        System.out.println(arr.toString());
        // 组装结果
        long ans = 0;
        for (int i = 0; i < length; i++) {
            ans = ans * 10 + arr.get(i);
        }
        if (ans > Integer.MAX_VALUE) return -1;
        return (int)ans;

    }

    public static void main(String[] args) {
        System.out.println(new Solution().nextGreaterElement(7367294));
    }
}