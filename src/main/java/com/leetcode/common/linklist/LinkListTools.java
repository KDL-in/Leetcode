package com.leetcode.common.linklist;

import java.util.ArrayList;
import java.util.List;

import static com.leetcode.common.InputTools.inputSplit;

public class LinkListTools {

    public static ListNode revBuild(String input, String splitStr) {
        List<Integer> data = inputSplit(input, splitStr);
        if (data.size() <= 0) return null;
        ListNode head = new ListNode(data.get(0));
        ListNode cur = head;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) != null) {
                cur.next = new ListNode(data.get(i));
                cur = cur.next;

            }
        }
        return head;
    }

    public static List<ListNode> trav(ListNode head) {
        List<ListNode> result = new ArrayList<>();
        while (head != null) {
            System.out.print(head + " ");
            result.add(head);
            head = head.next;
        }
        System.out.println();
        return result;
    }
}
