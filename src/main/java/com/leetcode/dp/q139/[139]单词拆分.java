package com.leetcode.dp.q139;//给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
//
// 说明： 
//
// 
// 拆分时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
// 
//
// 示例 2： 
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//     注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 👍 1207 👎 0


import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        set.addAll(wordDict);
        boolean[] memo = new boolean[s.length() + 1];
        memo[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (memo[j] && set.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[s.length()];

    }


}
// 超时
class SolutionV2 {
    private Set<String> set;
    private boolean [] memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>();
        set.addAll(wordDict);
        memo = new boolean[s.length() + 1];
        return dfs(s, 0);
    }

    private boolean dfs(String s, int k) {
        if (k >= s.length()) return true;
        if (memo[k]) return memo[k];
        for (int i = k; i < s.length(); i++) {
            String substring = s.substring(k, i + 1);
            if (set.contains(substring) && dfs(s, i + 1)) {
                memo[i + 1] = true;
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
