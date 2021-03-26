package com.leetcode.comm.lcof._q26;

import java.util.HashSet;
import java.util.Set;

/*
572. Subtree of Another Tree
https://leetcode.com/problems/subtree-of-another-tree/
二叉树的相同子树

这里用的是二叉树序列化的方法,前后序是唯一的.
 */

class Solution {
    Set<String> set;
    public boolean isSubtree(TreeNode s, TreeNode t) {
        set = new HashSet<>();
        String sstr = trav(s, true);
        String tstr = trav(t, false);
        //System.out.println(tstr);
        set.add(sstr);
        return set.contains(tstr);
    }
    private String trav(TreeNode root, boolean ifAddSet){
        if (root == null) return "#";
        String cur = "";
        String left = trav(root.left, ifAddSet);
        String right = trav(root.right, ifAddSet);
        cur = left +"," +  right + "," + root.val;
        if(ifAddSet) set.add(cur);
        //System.out.println(cur);
        return cur;
    }
}