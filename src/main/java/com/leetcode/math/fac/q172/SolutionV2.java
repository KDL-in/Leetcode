package com.leetcode.math.fac.q172;

/*
* 172. Factorial Trailing Zeroes
* 阶乘尾0的个数
* https://leetcode.com/problems/factorial-trailing-zeroes/
* */
/*
以125为例

5，10，15，20... 5的倍数都能提供 1个5， 总共有125/5个

25,50,75,100,125... 25的倍数还能额外提供1个人5，总共125/25个

125，... 125的倍数还能额外提供一个5，总共125/125个

相加即可

算法复杂度分析：

取决于while循环的次数，k次，5^k = n，则k = log n，所以算法复杂度为O(log n)
Runtime: 0 ms, faster than 100.00% of Java online submissions for Factorial Trailing Zeroes.
Memory Usage: 35.7 MB, less than 80.01% of Java online submissions for Factorial Trailing Zeroes.
* */

class SolutionV2 {
    public int trailingZeroes(int n) {
        int d = 5, res = 0;
        while (d <= n) {
            res += n / d;
            d *= 5;
        }
        return res;
    }
}