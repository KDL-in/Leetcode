package com.leetcode.comm._byte.q148;

/*
* 148. Sort List
* 链表归并排序
* https://leetcode.com/problems/sort-list/
*
*
* */


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
    public ListNode sortList(ListNode head) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        return mergeSort(head, n);
    }

    private ListNode mergeSort(ListNode head, int n) {
        if (n == 1) return head;
        // 划分链表并断链
        ListNode m = getKTh(n / 2 + 1, head);
        ListNode p = mergeSort(head, n / 2);
        ListNode q = mergeSort(m, n - n / 2);
        // merge
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (p != null && q != null) {
            if (p.val > q.val) {
                cur.next = q;
                q = q.next;
                cur = cur.next;
            } else {
                cur.next = p;
                p = p.next;
                cur = cur.next;
            }
        }
        if (p!=null) cur.next = p;
        else if (q!=null) cur.next = q;
        return dummy.next;
    }

    private ListNode getKTh(int k, ListNode head) {
        ListNode pre = null;
        while (k != 1) {
            pre = head;
            head = head.next;
            --k;
        }
        if (pre != null) pre.next = null;
        return head;
    }

}