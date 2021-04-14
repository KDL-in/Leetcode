package com.leetcode.comm.other;

import java.util.ArrayList;
import java.util.List;

public class Mainv2 {
    public static void main(String[] args) {
        List<Yang> list = new ArrayList<>();
        list.add(new Yang());
        for (int i = 0; i < 15; i++) {
            int size = list.size();
            for (int j = 0; j < size; ++j) {
                Yang yang = list.get(j);
                yang.timePass();
                if (yang.getAge() == 2 || yang.getAge() == 4) list.add(new Yang());
            }
            // int r = 0;
            // for (Yang yang : list) {
            //     // System.out.print(yang.getAge() + " ");
            //     if (yang.isAlive()) r++;
            // }
            // // System.out.println();
            // System.out.println((i + 1) + " " + r);
        }
        int r = 0;
        for (Yang yang : list) {
            if (yang.isAlive()) r++;
        }
    }

}

class Yang {
    int age;
    boolean alive;

    public Yang() {
        alive = true;
    }

    public int getAge() {
        return age;
    }

    public boolean isAlive() {
        return alive;
    }

    public void timePass() {
        if (!alive) return;
        age++;
        if (age == 5) alive = false;
    }
}