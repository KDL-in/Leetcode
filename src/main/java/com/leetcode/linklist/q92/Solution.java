package com.leetcode.linklist.q92;

import com.leetcode.common.linklist.LinkListTools;
import com.leetcode.common.linklist.ListNode;

/*
 * 92. Reverse Linked List II
 * 链表翻转
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * */
/*
非常巧的递归实现，主要是return以及连接修正需要处理好
Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List II.
Memory Usage: 36.7 MB, less than 48.15% of Java online submissions for Reverse Linked List II.
 */
public class Solution {
    ListNode suc;

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // ListNode last;
        // int i = 1;
        // if (m == 1) {
        //     last = reverseLinkList(head, n - m + 1);
        //     head.next = suc;
        //     return last;
        // } else {
        //     ListNode cur = head, pre = head;
        //     while (i != m) {
        //         pre = cur;
        //         cur = cur.next;
        //         i++;
        //     }
        //     last = reverseLinkList(cur, n - m + 1);
        //     pre.next.next = suc;
        //     pre.next = last;
        //     return head;
        // }
        ListNode b, pre, last, cur; int i;
        b = new ListNode(); pre = b; b.next = head; i = 1;
        while (i != m) {
            pre = head;
            head = head.next;
            i++;
        }
        last = reverseLinkList(head, n - m + 1);
        pre.next.next = suc;
        pre.next = last;
        return b.next;
    }

    private ListNode reverseLinkList(ListNode head, int n) {
        if (n == 1) {
            suc = head.next;
            return head;
        }
        ListNode last = reverseLinkList(head.next, n - 1);
        head.next.next = head;
        head.next = null;

        return last;
    }

    private ListNode reverseLinkList(ListNode head) {
        // if(head.next == null) return head;
        //
        // ListNode last = reverseLinkList(head.next);
        // last.next = head;
        // head.next = null;
        if (head.next == null) return head;

        ListNode last = reverseLinkList(head.next);
        head.next.next = head;
        head.next = null;

        return last;
    }

    public static void main(String[] args) {
        String input = "1->2->3->4->5->NULL";
        ListNode head = LinkListTools.revBuild(input, "->");
        LinkListTools.trav(head);
        ListNode last = new Solution().reverseBetween(head, 2, 4);
        LinkListTools.trav(last);
    }
}
