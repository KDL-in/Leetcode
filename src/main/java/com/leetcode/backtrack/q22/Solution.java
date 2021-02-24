package com.leetcode.backtrack.q22;

import java.util.ArrayList;
import java.util.List;

/*
* 22. Generate Parentheses
* 括号生成
* https://leetcode.com/problems/generate-parentheses/
* */



/*
本质上是一个子集数问题，每个位置可以是“(”或“)”。到达目标长度，则检查合法性。
然后就是剪枝。
- l规定为n
- r规定为n
- 添加")"时必须保证，l<r
Runtime: 0 ms, faster than 100.00% of Java online submissions for Generate Parentheses.
Memory Usage: 39.4 MB, less than 31.65% of Java online submissions for Generate Parentheses.
* */

class Solution {
    private StringBuilder s;
    private List<String> res;
    public List<String> generateParenthesis(int n) {
        s = new StringBuilder();
        res = new ArrayList<>();
        backtrack(n, n);
        return res;
    }

    private void backtrack(int l, int r) {
        if (l == 0 && r == 0) {
            res.add(s.toString());
        }
        if (l > 0) {
            s.append("(");
            backtrack(l - 1, r);
            s.deleteCharAt(s.length() - 1);
        }
        if (l < r && r > 0) {
            s.append(")");
            backtrack(l, r - 1);
            s.deleteCharAt(s.length() - 1);
        }
    }

}