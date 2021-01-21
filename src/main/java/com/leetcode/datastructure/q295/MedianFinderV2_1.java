package com.leetcode.datastructure.q295;

import java.util.PriorityQueue;
/*
非常简洁的实现，但是在时间复杂度上，会稍微多一个系数，原因是每次add必定是三次的O（log N）操作
Runtime: 48 ms, faster than 72.61% of Java online submissions for Find Median from Data Stream.
Memory Usage: 50.1 MB, less than 83.21% of Java online submissions for Find Median from Data Stream.
*/

public class MedianFinderV2_1 {
    private PriorityQueue<Integer> maxHead, minHead;
    public MedianFinderV2_1() {
        maxHead = new PriorityQueue<>((a, b) -> b - a);
        minHead = new PriorityQueue<>();

    }

    public void addNum(int num) {
        if (maxHead.size() < minHead.size()) {
            minHead.offer(num);
            maxHead.offer(minHead.poll());
        } else {
            maxHead.offer(num);
            minHead.offer(maxHead.poll());
        }
        System.out.println(findMedian());
    }

    public double findMedian() {
        if (maxHead.size() == minHead.size())return (maxHead.peek() + minHead.peek()) / 2.0;
        return minHead.peek();
    }

    public static void main(String[] args) {
        MedianFinderV2_1 mf = new MedianFinderV2_1();
        mf.addNum(-1);
        mf.addNum(-2);
        mf.addNum(-3);
        mf.addNum(-4);
        mf.addNum(-5);
    }
}
