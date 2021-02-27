package com.leetcode.math.game.q319;
/*
* 319. Bulb Switcher
* 关灯游戏
* https://leetcode.com/problems/bulb-switcher/
* */

/*
第i轮，每隔i个toggle一盏灯。

从整体的角度来思考，最终每盏灯亮或不亮，取决于它被开关多少次。
对于i，一般情况它的因子都是成对出现，例如8，（1，8)(2,4)，这种情况下必然灭掉
除非i本身等于某个平方数，则因子为奇数，例如9,(1,9)(3)
所以，有多少个灯不灭，其实就等于多少个这样的平方数。
Runtime: 0 ms, faster than 100.00% of Java online submissions for Bulb Switcher.
Memory Usage: 35.9 MB, less than 30.51% of Java online submissions for Bulb Switcher.
* */
class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}