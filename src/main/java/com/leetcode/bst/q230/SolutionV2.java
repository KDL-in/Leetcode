package com.leetcode.bst.q230;




import com.leetcode.common.bintree.BinTreeTools;
import com.leetcode.common.bintree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
* 迭代实现,利用栈模拟中序
* 复杂度：
* K个节点入栈，空间复杂度O(K), 时间复杂度O(K)，统计意义上O(N/2)
* 改进，空间复杂度O(H)，事实上，由于中序遍历的特点，栈的深度其实等于中树的深度，因为左右子树不会同时进栈（右子树被访问，当且仅当左子树，root出栈），
* 时间复杂度为O(H+K)，其中H为树高度，开始pop前首先要到达leaf节点。
* 其中H最差是N最好是logN
* */
public class SolutionV2 {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int i = 0;
        while (true) {
            // left trav
            while (cur!= null) {
                stack.push(cur);
                cur = cur.left;
            }
            // visit and pop
            cur = stack.peek();
            // System.out.println(cur);
            if (++i == k) return cur.val;
            stack.pop();
            // move to right
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        String input = "5, 3, 6, 2, 4, null, null, 1";
        TreeNode root = BinTreeTools.levRebuild("5, 3, 6, 2, 4, null, null, 1");
        // List<TreeNode> list = BinTreeTools.trav(root,"lev");

        System.out.println(new SolutionV2().kthSmallest(root, 4));


    }
}


