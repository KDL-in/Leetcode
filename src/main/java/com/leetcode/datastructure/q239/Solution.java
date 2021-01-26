package com.leetcode.datastructure.q239;

import java.util.LinkedList;
import java.util.List;

/*
* 239. Sliding Window Maximum
* 返回滑动窗口中的最大值
* https://leetcode.com/problems/sliding-window-maximum/
*
* */
/*
分析：
题目的核心是实现O(1)的效率找到窗口中的最大值。从直觉的角度，窗口移动，一个元素出一个元素进，并不需要重新对比窗口的所有元素，
事实上，若是只考虑每次增加一个元素，则最大值必定是原来的最大值或者当前元素。而这里的核心问题是，窗口最左的元素会出队。那么，
当前的最大值就可能离开，而不得不重新对比窗口所有元素。
为了避免这一点，可以使用单调队列，即队列中的元素严格的单调。
单调队列，维护单调队列解决问题，队列必须从大到小，如果队列最后的元素比当前元素更小，则出队。然后当前元素入队
若当前元素为cur，cur之前，比cur小的元素其实并没有什么用，唯一的用处是窗口滑动的时候出队——因为它们不可能是最大值，在当前窗口，
index小于cur，值小于cur，这意味着即使窗口向左移动，当前最大值cur都会覆盖这所有的元素竞选最大值。所以它们是无用的。

时间效率：O(N)
考虑所有元素都会仅会一次入队出队，所以整体效率一定是O(N)，平均每次操作就都是O(1)

对比单调栈，单调队列，会发现它们非常非常类似，它们保证单调的基本手段是——元素进入前，首先移除不符合单调条件的集合中的元素。
Runtime: 62 ms, faster than 20.36% of Java online submissions for Sliding Window Maximum.
        Memory Usage: 129.7 MB, less than 6.15% of Java online submissions for Sliding Window Maximum
*/

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        ManoQueue q; int [] res; int idx;
        q = new ManoQueue();
        res = new int[nums.length - k + 1];
        idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                q.push(nums[i]);
            } else {
                q.push(nums[i]);
                res[idx++] = q.max();
                q.pop(nums[i - k + 1]);
            }
        }
        return res;
    }
}


class ManoQueue{
    private LinkedList<Integer> list;

    public ManoQueue() {
        list = new LinkedList<>();
    }

    public void push(int cur) {
        while (!list.isEmpty() && cur > list.getLast()) list.removeLast();
        list.addLast(cur);
    }

    public int max() {
        return list.getFirst();
    }

    public void pop(int cur) {
        if (list.getFirst() == cur)  list.removeFirst();
    }
}