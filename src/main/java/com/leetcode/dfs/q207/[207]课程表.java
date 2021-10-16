package com.leetcode.dfs.q207;//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 10⁵ 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 977 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
// 拓扑排序两种解题思路


class SolutionV2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        int cnt = 0;
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] pre = new int[numCourses]; // 前置课程数量
        // 构建反向邻接表 adj
        for (int[] edge : prerequisites) {
            adj[edge[1]].add(edge[0]);
            pre[edge[0]]++;
        }
        // 找到0前置的课程集合
        List<Integer> zeroPre = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (pre[i] == 0) zeroPre.add(i);
        }
        // 消除0前置的课程
        while (!zeroPre.isEmpty()) {
            int p = zeroPre.remove(zeroPre.size() - 1);
            // q -> p，消除p的情况下q的前置课程，pre[q]应该减少
            for (int q: adj[p]) {
                pre[q]--;
                if (pre[q] == 0) zeroPre.add(q);
            }
            cnt++;
        }
        return cnt == numCourses;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
