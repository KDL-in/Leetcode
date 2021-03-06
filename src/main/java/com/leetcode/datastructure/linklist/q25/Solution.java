package com.leetcode.datastructure.linklist.q25;

import com.leetcode.common.linklist.LinkListTools;
import com.leetcode.common.linklist.ListNode;

/*
 * 25. Reverse Nodes in k-Group
 * 按组翻转链表
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * */
/*

迭代实现，连接细节多
Runtime: 1 ms, faster than 22.79% of Java online submissions for Reverse Nodes in k-Group.
Memory Usage: 39.5 MB, less than 29.24% of Java online submissions for Reverse Nodes in k-Group.

*/
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode b, pre, cur, start, end, t;int i;
        b = new ListNode();b.next = head;pre = b;end = head;
        while (true) {
            cur = start = end;
            for (i = 0; i < k && end != null; i++) end = end.next;

            if (i != k) break;
            while (cur != end) {
                t = cur.next;
                cur.next = pre;
                pre = cur;
                cur = t;
            }
            start.next.next = pre;
            start.next = end;
            pre = start;
        }
        return b.next;
    }

    public static void main(String[] args) {
        String input = "1->2->3->4->5->NULL";
        ListNode head = LinkListTools.revBuild(input, "->");
        LinkListTools.trav(head);
        ListNode last = new Solution().reverseKGroup(head, 2);
        LinkListTools.trav(last);
    }
}
