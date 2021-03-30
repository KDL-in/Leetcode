package com.leetcode.comm.lcof.q39;

import java.util.ArrayList;
import java.util.List;
/*
* 229. Majority Element II
* 进阶版，找次数多于n/3的所有数
* https://leetcode.com/problems/majority-element-ii/
*
*
* */
/*
这个方法叫做摩尔投票法，和n/2版本是非常类似的，只不过现在有2个候选人。

对于该方法的理解，将其视为每次消掉a，b，c不同的三个数，最多只能消除n/3次。

假定消除的确实是目标数，即大于n/3的，那么最终必然剩下他们。

假定消除的确实不是目标书，那么最终剩下的必然也有他们。

所以目标必在剩下的候选人中，但不一定全部都是，所以仍然需要再一次遍历判断过程。
例如所有数都不一样的情况，这个时候剩下的数出现次数就不会大于n/3。
* */
class SolutionV3 {
    public List<Integer> majorityElement(int[] nums) {
        int cand1, cand2, c1, c2;
        List<Integer> res = new ArrayList<>();
        cand1 = cand2 = (int)(1e9+1);
        c1 = c2 = 0;
        for(int i = 0; i < nums.length; ++i){
            //System.out.println(cand1 + " " + cand2);
            if(nums[i] == cand1){
                ++ c1;
                continue;
            }
            if(nums[i] == cand2){
                ++ c2;
                continue;
            }
            if(c1 == 0){
                cand1 = nums[i];
                ++c1;
                continue;
            }
            if(c2 == 0){
                cand2 = nums[i];
                ++c2;
                continue;
            }
            --c1;
            --c2;
        }
        int t1 = 0, t2 = 0;
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] == cand1) ++t1;
            else if(nums[i] == cand2) ++t2;
        }
        if (t1 > (nums.length / 3)) res.add(cand1);
        if (t2 > (nums.length / 3)) res.add(cand2);
        return res;
    }
}