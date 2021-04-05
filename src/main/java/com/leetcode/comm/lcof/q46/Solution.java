package com.leetcode.comm.lcof.q46;
/*
* 91. Decode Ways
* 解码的手段
* https://leetcode.com/problems/decode-ways/
* */
/*
动态规划
$$
f(n) = f(n - 1）+ f(n - 2)
$$
f(n)表示前1到n个字符能够解码的次数，有两种可能，一种是n位置单独解码（合法的情况下），则子问题-1，f(n) += f(n-1)
另一种则是n和n-1的位置合并一起解码（合法的情况下），子问题-2，f(n) += f(n-2)

上述转移公式实际上是简化了各种合法检查的，还需要注意边界情况，f(0) = 1，才能正确求出所有解，可以使用f(1) f(2)进行
归纳推理
* */
class Solution {
    public int numDecodings(String s) {
        boolean flag = true;
        int n = s.length();
        int f[] = new int[n+1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i){
            flag = true;
            if (check(i - 2, i - 1, s)){
                f[i] += f[i-2];
                flag = false;
            }
            if(check(i - 1, i - 1, s)){
                f[i] += f[i-1];
                flag = false;
            }
            if(flag) break;
        }
        return flag ? 0 : f[n];
    }
    
    private boolean check(int i, int j, String s){
        if (i < 0) return false;
        int t = Integer.parseInt(s.substring(i, j+1));
        return s.charAt(i) != '0' && t <= 26 && t >= 1;
    }
}