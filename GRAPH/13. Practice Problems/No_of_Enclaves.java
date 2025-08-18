// Problem Link : https://leetcode.com/problems/number-of-enclaves/

import java.util.*;
class Solution {
    public int numEnclaves(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    if (board[i][j] == 1) {
                        board[i][j] = 0;
                        q.offer(new int[] { i, j });
                    }
                }
            }
        }

        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] curr = q.poll();
                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];

                    if (x > 0 && x < m - 1 && y > 0 && y < n - 1 && board[x][y] == 1) {
                        board[x][y] = 0;
                        q.offer(new int[] { x, y });
                    }
                }
            }
        }
        int count =0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                    count += board[i][j]; 
            }
        }
        return count;
    }
}