package com.leetcode.comm.wy.q3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
4 4 5
out
4
2 4 8 2 1 4 2 3
out:
14
 */
class Point {
    int v;
    int i;

    public Point(int i, int v) {
        this.v = v;
        this.i = i;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Point> data = new ArrayList<>();
        int n = 0, sum = 0;
        String[] input = sc.nextLine().split(" ");
        n = input.length;
        int[] arr = new int[n + 2];
        arr[0] = arr[n + 1] = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = Integer.valueOf(input[i]);
            data.add(new Point(i + 1, arr[i + 1]));
        }
        data.sort((a, b) -> a.v - b.v);
        int pages[] = new int[n + 2];
        for (int i = 0; i < n; i++) {
            Point cur = data.get(i);
            pages[cur.i] = 1;
            if (arr[cur.i] == arr[cur.i - 1]) pages[cur.i] = pages[cur.i - 1];
            else if (arr[cur.i] > arr[cur.i - 1]) pages[cur.i] = pages[cur.i - 1] + 1;
            if (arr[cur.i] == arr[cur.i + 1]) pages[cur.i] = Math.max(pages[cur.i], pages[cur.i + 1]);
            else if(arr[cur.i] > arr[cur.i + 1]){
                if (pages[cur.i] <= pages[cur.i + 1]) pages[cur.i] = pages[cur.i + 1] + 1;
            }
        }
        for (int page : pages) {
            //System.out.print(page + " ");
            sum += page;
        }
        System.out.println(sum);
    }
}
