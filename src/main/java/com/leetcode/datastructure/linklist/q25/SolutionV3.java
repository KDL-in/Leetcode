package com.leetcode.datastructure.linklist.q25;

import com.leetcode.common.linklist.ListNode;

public class SolutionV3 {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(), start = dummy, end;
            dummy.next = head;
            while (start != null) {
                int i = 0;
                end = start.next;
                for(; i < k && end != null; ++ i){
                    end = end.next;
                }
                if (i < k) break;
                start = rev(start, end);
            }
            return dummy.next;
        }

        private ListNode rev(ListNode start, ListNode end) {
            ListNode p = start, q = start.next, t;
            while (q != end) {
                t = q.next;
                q.next = p;
                p = q;
                q = t;
            }
            t = start.next;
            t.next = end;
            start.next = p;
            return t;
        }

}
