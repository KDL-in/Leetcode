package com.leetcode.comm.q26;

import java.util.HashSet;
import java.util.Set;

/*
572. Subtree of Another Tree
重复子树，前序和后序是唯一的（叶子节点#）
https://leetcode.com/problems/subtree-of-another-tree/
* */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

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