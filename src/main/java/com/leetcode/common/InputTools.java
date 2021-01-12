package com.leetcode.common;

import java.util.ArrayList;
import java.util.List;

public class InputTools {

    public static List<Integer> inputSplit(String input, String regex) {

        String[] sp = input.split(regex);
        List<Integer> data = new ArrayList<>();
        for (String s : sp) {
            s = s.trim();
            if ("null".toUpperCase().equals(s)) {
                data.add(null);
            } else {
                data.add(Integer.parseInt(s));
            }
        }
        return data;
    }
}
