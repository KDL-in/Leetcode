package com.leetcode._byte.q101;

import java.util.ArrayList;
import java.util.List;

class SolutionV3 {
    public boolean isSymmetric(TreeNode root) {
        int i, j;
        List<Integer> list = new ArrayList<>();
        trav(root, list);
        i = 0;
        j = list.size() - 1;
        while (i < j) {
            if (list.get(i++) != list.get(j--)) return false;
        }
        return true;
    }

    private void trav(TreeNode root, List<Integer> list) {

        if (root.left != null) trav(root.left, list);
        else if (root.right != null) list.add(-101);
        list.add(root.val);
        if (root.right != null) trav(root.right, list);
        else if (root.left != null) list.add(-101);
    }

    public static void main(String[] args) {
        new SolutionV3().isSymmetric(null);
    }
}