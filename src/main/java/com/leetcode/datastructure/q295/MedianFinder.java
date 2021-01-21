package com.leetcode.datastructure.q295;

/*
 * 295. Find Median from Data Stream
 * 维护流数据的中值
 * https://leetcode.com/problems/find-median-from-data-stream/
 * */
/*
维护有序的链表
Runtime: 507 ms, faster than 9.73% of Java online submissions for Find Median from Data Stream.
Memory Usage: 51.3 MB, less than 19.56% of Java online submissions for Find Median from Data Stream.
*/

public class MedianFinder {
    private MidOrderLinkedList list;
    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        list = new MidOrderLinkedList();
    }

    public void addNum(int num) {
        list.insert(num);
    }

    public double findMedian() {
        return list.getMidValue();
    }


}

class MidOrderLinkedList {
    private final Node head;
    private final Node last;
    private Node mid;
    private int size;

    public MidOrderLinkedList() {
        head = new Node(-Integer.MAX_VALUE);
        last = new Node(Integer.MAX_VALUE);
        head.next = last;
        last.pre = head;
        mid = head;
    }

    public void insert(int value) {
        // insert value
        Node cur, node;
        cur = mid; node = new Node(value);
        if (value < mid.val) while (value < cur.val) cur = cur.pre;
        else while (value >= cur.next.val) cur = cur.next;
        node.pre = cur;
        node.next = cur.next;
        cur.next.pre = node;
        cur.next = node;
        size++;
        // update mid
        if ((size & 1) == 1) {
            if (value >= mid.val) mid = mid.next;
        } else {
            if (value < mid.val) mid = mid.pre;
        }
        disp();
    }

    public double getMidValue() {
        return ((size & 1) == 1) ? mid.val : (double) ((mid.val + mid.next.val) / 2.0);
    }
    private void disp() {
        Node cur = head.next;
        while (cur != last) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }

        System.out.println("" +
                "mid: " + mid.val + "mid val: " + getMidValue());
    }

    public int size() {
        return size;
    }
}

class Node {
    int val;
    Node pre, next;

    public Node(int val) {
        this.val = val;
    }

    public Node() {
    }
}