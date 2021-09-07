package com.leetcode.array.q43;//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
// 示例 1: 
//
// 输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 说明： 
//
// 
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
// 
// Related Topics 数学 字符串 模拟 👍 705 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String multiply(String num1, String num2) {
        int n = num1.length(), m = num2.length(), a, b, k = 0, l, tmp, add = 0;
        int res[] = new int[n + m + 1];
        for (int i = m - 1; i >= 0 ; --i) {
            a = num2.charAt(i) - '0';
            l = k;
            for (int j = n - 1; j >= 0; --j) {
                b = num1.charAt(j) - '0';
                tmp = a * b + res[l] + add;
                res[l] = tmp % 10;
                add = tmp / 10;
                l++;
            }
            while (add != 0){
                tmp = res[l] + add;
                res[l] = tmp % 10;
                add = tmp / 10;
            }
            k ++;
        }
        int i = res.length - 1;
        StringBuilder str = new StringBuilder();
        while(res[i] == 0 && i > 0) i--;
        while(i >= 0) str.append((char)(res[i--] + '0'));
        return str.toString();
    }

}
//leetcode submit region end(Prohibit modification and deletion)
