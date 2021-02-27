package com.leetcode.math.game.q292;
/*
292. Nim Game
石头游戏
https://leetcode.com/problems/nim-game/
* */
/*
关键，胜负是有规律的
111011101110
Runtime: 0 ms, faster than 100.00% of Java online submissions for Nim Game.
Memory Usage: 35.5 MB, less than 82.92% of Java online submissions for Nim Game.
* */
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}