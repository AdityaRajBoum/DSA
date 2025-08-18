// Problem Link : https://leetcode.com/problems/surrounded-regions/
import java.util.*;
class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    if (board[i][j] == 'O') {
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

                    if (x > 0 && x < m - 1 && y > 0 && y < n - 1 && board[x][y] == 'O') {
                        board[x][y] = '*';
                        q.offer(new int[] { x, y });
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1)
                    continue;
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }

    }
}