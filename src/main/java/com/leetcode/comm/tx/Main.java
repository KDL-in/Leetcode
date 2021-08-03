package com.leetcode.comm.tx;

public class Main {
    public static void main(String[] args) {
        int [] data = {1, 2, 3, 2, 1};
        ListNode root = input(data);
        System.out.println(new Solution().isPalindrome(root));
    }

    private static ListNode input(int[] data) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (int i = 0; i < data.length; i++) {
            cur.next = new ListNode(data[i]);
            cur = cur.next;
        }
        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}
class Solution{
    public boolean isPalindrome(ListNode root) {
        ListNode p, q, t, dummy;
        dummy = new ListNode(-1);
        dummy.next = root;
        p = q = dummy;
        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
        }
        //System.out.println(p.val + " " + q.val); // 2 1
        t = q;
        q = p.next;
        revRec(root, p);
        if (t == null) p = p.next;
        //System.out.println(p.val + " " + q.val); // 2 2
        while (q != null) {
            if (p.val != q.val) return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }

    private ListNode rev(ListNode root, ListNode end) {
        if (root == end) return end;
        ListNode p, q, t;
        p = root;
        q = p.next;
        // System.out.println("rev");
        while (true) {
            // System.out.println(p.val + "<-" + q.val);
            t = q.next;
            q.next = p;
            if (q == end) break;
            p = q;
            q = t;
        }
        root.next = null;
        return end;
    }

    private ListNode revRec(ListNode root, ListNode end) {
        if (root == end) return root;
        ListNode t = revRec(root.next, end);
        root.next.next = root;
        root.next = null;
        return t;
    }
}