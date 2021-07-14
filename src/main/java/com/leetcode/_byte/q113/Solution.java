package com.leetcode._byte.q113;

import com.leetcode.common.bintree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
* 113. Path Sum II
* 二叉树路径和
* https://leetcode.com/problems/path-sum-ii/
*
*
* */
/*
需要考虑是否加的时机，到底是走到null才判断呢，还是提前探测
* */
class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        backtrack(root, new ArrayList<Integer>(), targetSum);
        return res;
    }
    private void backtrack(TreeNode root, List<Integer> cur, int target){
        if(root == null) return;
        cur.add(root.val);
        target -= root.val;
        if ((root.left == null && root.right == null) && target == 0)res.add(new ArrayList(cur));
        backtrack(root.left, cur, target );
        backtrack(root.right, cur, target);

        cur.remove(cur.size()-1);
        target += root.val;
    }
}