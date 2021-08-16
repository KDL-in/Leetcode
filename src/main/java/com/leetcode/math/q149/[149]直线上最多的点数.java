package com.leetcode.math.q149;
//给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
//
// 
//
// 示例 1： 
//
// 
//输入：points = [[1,1],[2,2],[3,3]]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= points.length <= 300 
// points[i].length == 2 
// -104 <= xi, yi <= 104 
// points 中的所有点 互不相同 
// 
// Related Topics 几何 哈希表 数学 
// 👍 336 👎 0


import java.util.HashMap;
import java.util.Map;
/*
这个问题没有什么更好的解法，本质上就是遍历所有可能的直线
1. 固定一个点，遍历所有线，注意到，如果该点确实在结果集中，那么以该点出发比如可以找到结果，所以实际上map不需要保存所有斜率，所有直线，只需要保存以当前点出发的线
2. 由于固定的起始点，所以也不用考虑斜率分子分母异号
3. 需要使用最简分数作为key，避免除法精度问题

辗转相除法可以覆盖负数，0除的情况
 */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxPoints(int[][] points) {
        Map<String, Integer> map;
        int max, ans = 1, a, b, gcd, N = (int)(points.length/0.75);
        String key;
        for (int i = 0; i < points.length; i++) {
            map = new HashMap<>(N);
            max = 0;
            for (int j = i + 1; j < points.length; j++) {
                a = points[j][0] - points[i][0]; b = points[j][1] - points[i][1];
                gcd = gcd(a, b);
                key = (a/gcd) + "/" + (b/gcd);
                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);
                max = Math.max(max, count);
            }
            ans = Math.max(ans, max + 1);
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b != 0? gcd(b , a % b) : a;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
