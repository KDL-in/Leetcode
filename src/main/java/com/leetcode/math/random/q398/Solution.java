package com.leetcode.math.random.q398;
/*
* 398. Random Pick Index
* 获取目标值随机下标
* https://leetcode.com/problems/random-pick-index/
*
* */

/*
直观的解法是利用map<int, list<int>>直接解决，空间O(N)时间O(1)

题目要求节省空间，那唯一想到的就是排序+二分搜索确定上下边界，排序O(N log N)，空间O(1)时间O(NlogN）

看起来似乎都能接受，实际的问题是，由于数组极大，O(N)的额外空间消耗是不被接受的，O(N logN)的排序算法
在数据量极大的情况下会非常慢，也无法接受。

能否有一种one pass的O(N)，空间O(1)的算法呢？水塘抽样算法（Reservoir Sampling）

这是一种数学方法，假设当前位置为i，RS算法的核心是让随后i到N的多次采样，对于i整体概率刚好为1/N

$$
\begin{aligned}
& \frac{1}{i} \times\left(1-\frac{1}{i+1}\right) \times\left(1-\frac{1}{i+2}\right) \times \ldots \times\left(1-\frac{1}{n}\right) \\
=& \frac{1}{i} \times \frac{i}{i+1} \times \frac{i+1}{i+2} \times \ldots \times \frac{n-1}{n} \\
=& \frac{1}{n}
\end{aligned}
$$

````java

int getRandom(ListNode head) {
        Random r = new Random();
        int i = 0, res = 0;
        ListNode p = head;
        // while 循环遍历链表
        while (p != null) {
        // 生成一个 [0, i) 之间的整数
        // 这个整数等于 0 的概率就是 1/i
        if (r.nextInt(++i) == 0) {
        res = p.val;
        }
        p = p.next;
        }
        return res;
        }
````

还有一种随机取k个元素的版本，遍历一次，随机取到k个元素

$$
\begin{aligned}
& \frac{k}{i} \times\left(1-\frac{k}{i+1} \times \frac{1}{k}\right) \times\left(1-\frac{k}{i+2} \times \frac{1}{k}\right) \times \ldots \times\left(1-\frac{k}{n} \times \frac{1}{k}\right) \\
=& \frac{k}{i} \times\left(1-\frac{1}{i+1}\right) \times\left(1-\frac{1}{i+2}\right) \times \ldots \times\left(1-\frac{1}{n}\right) \\
=& \frac{k}{i} \times \frac{i}{i+1} \times \frac{i+1}{i+2} \times \ldots \times \frac{n-1}{n} \\
=& \frac{k}{n}
\end{aligned}
$$

实现比较清晰，每次都有k / (i+1)的概率修改前k个元素中的某一个

````java
int[] getRandom(ListNode head, int k) {
        Random r = new Random();
        int[] res = new int[k];
        ListNode p = head;

        // 前 k 个元素先默认选上
        for (int j = 0; j < k && p != null; j++) {
        res[j] = p.val;
        p = p.next;
        }

        int i = k;
        // while 循环遍历链表
        while (p != null) {
        // 生成一个 [0, i) 之间的整数
        int j = r.nextInt(++i);
        // 这个整数小于 k 的概率就是 k/i
        if (j < k) {
        res[j] = p.val;
        }
        p = p.next;
        }
        return res;
        }
````

Runtime: 47 ms, faster than 87.11% of Java online submissions for Random Pick Index.
Memory Usage: 47 MB, less than 96.75% of Java online submissions for Random Pick Index.
* */

import java.util.Random;

class Solution {
    private int[] nums;
    private Random random;
    private int j, res, dup;

    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();

    }
    
    public int pick(int target) {
        dup = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i]!=target) continue;
            j = random.nextInt(++dup);
            if (j == 0) {
                res = i;
            }
        }
        return res;
    }

}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */