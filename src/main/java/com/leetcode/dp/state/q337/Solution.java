package com.leetcode.dp.state.q337;

import com.leetcode.common.bintree.TreeNode;

/*
* 337. House Robber III
* 偷东西，二叉树
* https://leetcode.com/problems/house-robber-iii/
*
* */

/*
有限状态机，当前节点的值和左右子树能得到的的值有关。比起动态规划更像是后序遍历，复杂度O(N)
- dp(root)定义为当前节点选或不选两个状态能够得到的当前树的最大值
    $$
    dp(root)[0] = max(dp(left)[0], dp(left)[1]) + max(dp(right)[0], dp(right)[1])\\
    dp(root)[1] = dp(left)[0] + dp(right)[0]
    $$
- 状态，树的结构不必再引入状态
- 选择，当前节点选或不选的情况下，当前树能够得到的最大值
- base，当root = null时，表明树为空，return {0，0}
Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber III.
        Memory Usage: 38.9 MB, less than 42.80% of Java online submissions for House Robber III.
*/

class Solution {
    public int rob(TreeNode root) {
        int[] res = trav(root);
        return Math.max(res[0], res[1]);
    }

    private int[] trav(TreeNode root) {
        if (root == null) return new int[2];
        int[] left = trav(root.left);
        int[] right = trav(root.right);
        int[] cur = new int[2];
        cur[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        cur[1] = left[0] + right[0] + root.val;
        return cur;
    }
}