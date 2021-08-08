package com.leetcode.array.twosum.q653;//给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
//
// 
//
// 示例 1： 
//
// 
//输入: root = [5,3,6,2,4,null,7], k = 9
//输出: true
// 
//
// 示例 2： 
//
// 
//输入: root = [5,3,6,2,4,null,7], k = 28
//输出: false
// 
//
// 示例 3： 
//
// 
//输入: root = [2,1,3], k = 4
//输出: true
// 
//
// 示例 4： 
//
// 
//输入: root = [2,1,3], k = 1
//输出: false
// 
//
// 示例 5： 
//
// 
//输入: root = [2,1,3], k = 3
//输出: true
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [1, 104]. 
// -104 <= Node.val <= 104 
// root 为二叉搜索树 
// -105 <= k <= 105 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 哈希表 双指针 二叉树 
// 👍 260 👎 0


//leetcode submit region begin(Prohibit modification and deletion)



import java.util.HashSet;
import java.util.Set;


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
    private Set<Integer> set;
    private boolean flag;
    public boolean findTarget(TreeNode root, int k) {
        set = new HashSet<>();
        flag = false;
        inTrav(root, k);
        return flag;
    }

    private void inTrav(TreeNode root, int k) {
        if (flag) return;
        if (root == null) return;
        inTrav(root.left, k);
        if (set.contains(k - root.val)) {
            flag = true;
            return;
        }
        set.add(root.val);
        inTrav(root.right, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
