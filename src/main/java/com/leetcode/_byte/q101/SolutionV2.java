package com.leetcode._byte.q101;
// 超时
class SolutionV2 {
    public boolean isSymmetric(TreeNode root) {

        String left = trav(root.left);
        String right = trav(root.right);
        
        if (left.length() == right.length()){
            if (check(left, right)) return false;
            return true;
        }
        return false;
    }

    private boolean check(String left, String right) {
        int i;
        int j;
        i = 0;
        j = left.length()-1;
        while(j>=0){
            if(left.charAt(i) != right.charAt(j)) return true;
        }
        return false;
    }

    private String trav(TreeNode root){
        if(root == null)return "#";
        String left = trav(root.left);
        String right = trav(root.right);
        if (check(left, right)) return "$";
        return left + root.val + right;
    }
}