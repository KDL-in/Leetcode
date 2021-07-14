package com.leetcode._byte.q468;/*
IPv4 地址由十进制数和点来表示，每个地址包含 4 个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；

同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。

IPv6 地址由 8 组 16 进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。比如,  2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。

然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。

同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/validate-ip-address
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


 */

import java.util.Arrays;

class Solution {
    public String validIPAddress(String IP) {
        if (IP.indexOf('.') != -1) {
            if (validIPv4(IP)) return "IPv4";
        } else if (IP.indexOf(':') != -1) {
            if (validIPv6(IP)) return "IPv6";
        }
        return "Neither";
    }

    private boolean validIPv4(String IP) {
        String[] ips = IP.split("\\.");
        int dot = 0;
        for (int i = 0; i < IP.length(); i++) {
            if (IP.charAt(i) == '.') dot++;
        }
        if (ips.length != 4 || dot != 3) return false;
        int num;
        for (String s : ips) {
            if (s.length() == 0 || s.charAt(0) == '0' && s.length() > 1) return false;
            num = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!Character.isDigit(c)) return false;
                num = num * 10 + (c - '0');
            }
            if (num < 0 || num > 255) return false;
        }
        return true;
    }

    private boolean validIPv6(String IP) {
        String[] ips = IP.split(":");
        int dot = 0;
        for (int i = 0; i < IP.length(); i++) {
            if (IP.charAt(i) == ':') dot++;
        }
        if (ips.length != 8 || dot != 7) return false;
        for (String s : ips) {
            if (s.length() > 4 || s.length() == 0) return false;
            s = s.toLowerCase();
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f'))) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
    }
}