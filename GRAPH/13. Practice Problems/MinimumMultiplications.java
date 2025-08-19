// Problem Link : https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1

import java.util.*;

class Solution {
    int minimumMultiplications(int[] arr, int start, int end) {

        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[100000];

        q.offer(new int[]{start, 0});
        visited[start] = true;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int val = curr[0];
            int steps = curr[1];
            
            if (val == end) return steps;
            
            for (int num : arr) {
                int next = (val * num) % 100000;
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[]{next, steps + 1});
                }
            }
        }

        return -1; 
    }
}