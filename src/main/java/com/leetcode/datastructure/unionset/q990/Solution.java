package com.leetcode.datastructure.unionset.q990;

/*
* 990. Satisfiability of Equality Equations
* 等式是否成立，变量的连通性
* https://leetcode.com/problems/satisfiability-of-equality-equations/
* */

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Union;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*

Runtime: 2 ms, faster than 45.11% of Java online submissions for Satisfiability of Equality Equations.
Memory Usage: 38.3 MB, less than 92.39% of Java online submissions for Satisfiability of Equality Equations.
*/

class Solution {
    private Map<Character, Integer> charToIdx;
    private List<Integer> par;
    public boolean equationsPossible(String[] equations) {
        charToIdx = new HashMap<>();
        par = new ArrayList<>(equations.length*2);
        return solve(equations, par, charToIdx);
    }

    private boolean solve(String[] equations, List<Integer> par, Map<Character, Integer> charToIdx) {
        char a,b; int ar, br, idx = 0;
        for (String eq : equations) {
            a = eq.charAt(0);b = eq.charAt(3);
            if (!charToIdx.containsKey(a)) {
                par.add(idx);
                charToIdx.put(a, idx++);
            }
            if (!charToIdx.containsKey(b)) {
                par.add(idx);
                charToIdx.put(b, idx++);
            }

            if (eq.charAt(1)=='=') union(charToIdx.get(a), charToIdx.get(b));
        }

        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                a = eq.charAt(0);b = eq.charAt(3);
                ar = find(charToIdx.get(a));
                br = find(charToIdx.get(b));
                if (ar == br) return false;
            }
        }
        return true;
    }

    private int find(int cur) {

        while ((par.get(cur)) != cur) {
            par.set(cur, par.get(par.get(cur)));
            cur = par.get(cur);
        }
        return cur;
    }

    private void union(int a, int b) {
        int ar,br;
        ar = find(a);
        br = find(b);
        if (ar == br) return;
        par.set(ar, br);
    }

    public static void main(String[] args) {
        String []eqs = {"f==a","a==b","f!=e","a==c","b==e","c==f"};
        System.out.println(new Solution().equationsPossible(eqs));
    }
}