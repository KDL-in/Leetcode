package com.leetcode._byte.q440字典序第k小;

/**
 * 计算cur下的字典树有多少个节点
 * - 如果超过k，则说明在该cur下的子树中，向下移动
 * - 如果不超过，则说明在cur右边的子树中，向右移动
 */
class Solution {

    public int findKthNumber(int n, int k) {
        long cur = 1;
        int m = 0;
        while (k != 1){
            m = getNodes(cur, n);
//            System.out.println("cur: " + cur + " m: " + m);
            // left
            if (m < k){
                k -= m;
                cur ++;
//                System.out.println("move right " + k);
            } else {
                // down
                cur *= 10;
                k -= 1;
//                System.out.println("move down " + k);
            }
        }
        return (int)cur;
    }
    
    public int getNodes(long cur, int n){
        long next = cur + 1;
        int m = 0;
        while(cur <= n){
            m += (int)(Math.min(next, n + 1) - cur);
            cur *= 10;
            next *= 10;
        }
        return m;
    }

//    public static void main(String[] args) {
//        System.out.println(new Solution().findKthNumber(119, 23));
//    }


}