package com.leetcode.datastructure.unionset.q990;

import java.util.Arrays;

/*
简洁的是实现
Runtime: 0 ms, faster than 100.00% of Java online submissions for Satisfiability of Equality Equations.
        Memory Usage: 38.4 MB, less than 81.88% of Java online submissions for Satisfiability of Equality Equations.
*/

public class SolutionV2 {
    private int[] par;

    public boolean equationsPossible(String[] equations) {

        par = new int[26];
        for (int i = 0; i < par.length; ++i) par[i] = i;

        for (String eq : equations)
            if (eq.charAt(1) == '=')
                par[find(eq.charAt(0) - 'a')] = find(eq.charAt(3) - 'a');
        for (String eq : equations)
            if (eq.charAt(1) == '!' && find(eq.charAt(0) - 'a') == find(eq.charAt(3) - 'a')) return false;
        return true;
    }

    private int find(int x) {
        System.out.println(x);
        if (x != par[x]) par[x] = find(par[x]);
        return par[x];
    }

    public static void main(String[] args) {
        String[] eqs = {"f==a", "a==b", "f!=e", "a==c", "b==e", "c==f"};
        System.out.println(new SolutionV2().equationsPossible(eqs));
    }
}
