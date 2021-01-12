package com.leetcode.linklist.q92;

import com.leetcode.common.linklist.LinkListTools;
import com.leetcode.common.linklist.ListNode;


/*
迭代实现
前后指针，遍历翻转，难点是细节控制。添加适当的哨兵可以简化编程逻辑。
Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List II.
Memory Usage: 38.5 MB, less than 6.56% of Java online submissions for Reverse Linked List II.
*/
public class SolutionV2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode pre, cur, start, t, b;
        cur = head; b = new ListNode(); b.next = head; pre = b;
        int i = 1;
        // find start node
        while (i < m) {
            pre = cur;
            cur = cur.next;
            i++;
        }
        start = pre;
        while (i <= n) {
            t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
            i++;
        }
        t = start.next;
        start.next = pre;
        t.next = cur;

        return b.next;
    }

    public static void main(String[] args) {
        String input = "3->5";
        ListNode head = LinkListTools.revBuild(input, "->");
        LinkListTools.trav(head);
        head = new SolutionV2().reverseBetween(head, 1, 2);
        LinkListTools.trav(head);
    }
}
