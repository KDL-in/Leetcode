package com.leetcode.datastructure.q295;

import java.util.PriorityQueue;
/*
大顶堆，小顶堆。
时间复杂度，维护有序性（准确来说，部分有序即可）没问题；插入数据，查找+插入，O（logN），寻找中值，O（1）
寻找中值相比于排序，最重要的优化key——不必全局有序，部分有序，可以考虑堆。
Runtime: 120 ms, faster than 12.66% of Java online submissions for Find Median from Data Stream.
        Memory Usage: 50.9 MB, less than 23.53% of Java online submissions for Find Median from Data Stream.
Runtime: 43 ms, faster than 95.39% of Java online submissions for Find Median from Data Stream.
Memory Usage: 50.4 MB, less than 54.44% of Java online submissions for Find Median from Data Stream.
*/

public class MedianFinderV2 {
    private PriorityQueue<Integer> maxHead, minHead;
    private int left, right;
    public MedianFinderV2() {
        maxHead = new PriorityQueue<>((a, b) -> b - a);
        minHead = new PriorityQueue<>();

    }

    public void addNum(int num) {
        if (right != 0 && num < minHead.peek()) {
            maxHead.offer(num);
            left++;
        } else {
            minHead.offer(num);
            right++;
        }
        int dif = right - left;
        if (dif == 2) {
            int top = minHead.poll();
            right--;
            maxHead.offer(top);
            left++;
        }
        if (dif == -2) {
            int top = maxHead.poll();
            left--;
            minHead.offer(top);
            right++;
        }
    }

    public double findMedian() {
        if (left < right)return minHead.peek();
        else if (left > right) return maxHead.peek();
        return (maxHead.peek() + minHead.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinderV2 mf = new MedianFinderV2();
        mf.addNum(-1);
        mf.addNum(-2);
        mf.addNum(-3);
        mf.addNum(-4);
        mf.addNum(-5);
    }
}
