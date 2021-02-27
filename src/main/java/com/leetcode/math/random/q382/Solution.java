package com.leetcode.math.random.q382;

/*
 * 382. Linked List Random Node
 * 链表获取随机数
 * https://leetcode.com/problems/linked-list-random-node/
 *
 * */
/*
见398
如果是为了getRandom为O(1)，则可以花费O(N)的空间，建立array保存所有值，用于随机。
如果为了不使用额外的空间，则必须遍历得知N的值，然后以O(N)进行链表随机节点获取
Runtime: 16 ms, faster than 21.99% of Java online submissions for Linked List Random Node.
Memory Usage: 40.8 MB, less than 78.85% of Java online submissions for Linked List Random Node.
* */


import java.util.Random;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    private Random random;
    private int i, j, res;
    private ListNode head,p;

    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public Solution(ListNode head) {
        random = new Random();
        this.head = head;
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        i = 0;
        p = head;
        while (p != null) {
            j = random.nextInt(++i);
            if (j == 0) res = p.val;
            p = p.next;
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */