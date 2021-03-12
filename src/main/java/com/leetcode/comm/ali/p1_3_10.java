package com.leetcode.comm.ali;

import java.util.Scanner;
/*
模拟题，只能朝着一个方向走，撞墙停止，四个方向
读取输入不太习惯，nextInt完需要nextLine读掉\n，-_-
input:
3 4 4
@...
.#..
....
EAST
SOUTH
WEST
NORTH
output:
1 3
* */
public class p1_3_10 {

    public static void main(String[] args) {
        String dir;
        int k, n, m, s, x = 0, y = 0;
        char map[][];
        int dirs[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        map = new char[n][m];
        sc.nextLine();
//        System.out.println(n +" "+m+" ");
        for (int i = 0; i < n; i++) {
            dir = sc.nextLine();
            for (int j = 0; j < m; ++j) {
                map[i][j] = dir.charAt(j);
                if (map[i][j] == '@') {
                    x = i;
                    y = j;
                }
            }
        }

        while (k-- > 0) {
            dir = sc.nextLine();
            s = getIdx(dir);
//            System.out.println(dir +" "+s);
            while (check(x + dirs[s][0], y + dirs[s][1], n, m,map)) {
                x += dirs[s][0];
                y += dirs[s][1];
//                System.out.println(x +" " +y);
            }
        }
//        System.out.println((x + 1) + " " + (y + 1));
    }

    private static boolean check(int x, int y, int n, int m, char[][] map) {
        return x >= 0 && x < n && y >= 0 && y < m && map[x][y] != '#';
    }

    private static int getIdx(String dir) {
        if (dir.equals("EAST")) return 0;
        if (dir.equals("SOUTH")) return 1;
        if (dir.equals("WEST")) return 2;
        if (dir.equals("NORTH")) return 3;
        return -1;
    }
}
