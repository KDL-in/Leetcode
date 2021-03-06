package com.leetcode.datastructure.linklist.q25;

import com.leetcode.common.linklist.LinkListTools;
import com.leetcode.common.linklist.ListNode;

/*
递归解法，该问题具备明显的递归性质
Runtime: 1 ms, faster than 22.79% of Java online submissions for Reverse Nodes in k-Group.
        Memory Usage: 42.7 MB, less than 5.42% of Java online submissions for Reverse Nodes in k-Group.
*/

public class SolutionV2 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre, cur, end, t; int i;
        // 检查长度
        for(end = head,i = 0;i < k && end != null;i++)end = end.next;
        if(i != k) return head;
        // 开始翻转
        cur = head.next; pre = head;
        while (cur != end) {
            t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        // 翻转剩余链
        head.next = reverseKGroup(end, k);
        return pre;
    }

    public static void main(String[] args) {
        String input = "1->2->3->4->5->NULL";
        ListNode head = LinkListTools.revBuild(input, "->");
        LinkListTools.trav(head);
        ListNode last = new SolutionV2().reverseKGroup(head, 2);
        LinkListTools.trav(last);
    }
}
