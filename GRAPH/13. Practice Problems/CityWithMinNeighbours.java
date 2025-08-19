// Problem Link : https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/

import java.util.*;

// Mathod - 1 : Optimal Solution (Floyd_Warshall)

class Solution {
    public int findTheCity(int n, int[][] edges, int k) {

    // Creating the distance matrix
    int INF = (int)1e9; 
    int[][] dist = new int[n][n];

    // Initialize distances
    for (int i = 0; i < n; i++) {
        Arrays.fill(dist[i], INF); 
        dist[i][i] = 0;            
    }

    // Fill in the given edges
    for (int[] edge : edges) {
        int u = edge[0];
        int v = edge[1];
        int w = edge[2];
        dist[u][v] = w;
        dist[v][u] = w; 
    }

        // Implementing Floyd-Warshall
       for(int via = 0; via < n; via++){
           for(int i =0; i < n ; i++){
               for(int j=0; j < n ; j++){
                if(dist[i][via] != Integer.MAX_VALUE && dist[via][j] != Integer.MAX_VALUE)
                   dist[i][j] = Math.min(dist[i][j], dist[i][via]+dist[via][j]);
               }
           }
       }

       // Finding the city with minimum neighbours
        int resultCity = -1;
        int minCount = n+1;

        for(int i = 0; i < n ; i++){
            int count = 0;
            for(int j =0; j< n; j++){
                if( i!= j && dist[i][j] <= k)
                    count++;
            }
            if(count <= minCount){
                minCount = count;
                resultCity = i;
            }
        }
        return resultCity;
    }
}