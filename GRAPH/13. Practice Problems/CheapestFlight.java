// Problem Link : https://leetcode.com/problems/cheapest-flights-within-k-stops/

import java.util.*;

// Why not Dijkstra's Algorithm ?
/*
    * In this problem, we have two constraints: weighted edges and at most k stops.
    Dijkstra’s greedy assumption 
        — “first time I reach a node, it’s the cheapest” 
            — breaks if the cheapest path to the destination requires more stops.
    
            BFS doesn’t assume cost ordering, but can’t handle weighted edges directly.
*/ 

// Mathod - 1 
/* ************************ BFS ************************ */ 
/*
class Solution {
    class State {
        int node;
        int cost;
        int stops;

        public State(int node, int cost, int stops) {
            this.node = node;
            this.cost = cost;
            this.stops = stops;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Build adjacency list
        Map<Integer, List<int[]>> adj = new HashMap<>();

        for (int[] f : flights) {
            adj.computeIfAbsent(f[0], x -> new ArrayList<>()).add(new int[]{f[1], f[2]});
        }

        // Queue for BFS: (node, costSoFar, stopsSoFar)
        Queue<State> q = new LinkedList<>();
        q.offer(new State(src, 0, 0));

        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        int ans = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            State curr = q.poll();
            int node  = curr.node;
            int cost  = curr.cost;
            int stops = curr.stops;

            if (node == dst) {
                ans = Math.min(ans, cost);
            }

            if (stops > k) continue;

            if (!adj.containsKey(node)) continue;

            for (int[] neighbor : adj.get(node)) {
                int nextNode = neighbor[0];
                int price = neighbor[1];
                int nextCost = cost + price;

                if (nextCost < minCost[nextNode]) {
                    minCost[nextNode] = nextCost;
                    q.offer(new State(nextNode, nextCost, stops + 1));
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
*/

/* ************************ Bellman-Ford ************************ */ 

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src] = 0;

        for(int i = 0; i <= k; i++){
            int[] temp = Arrays.copyOf(dist, n);
            for(int[] flight : flights){
                int u  = flight[0];
                int v  = flight[1];
                int wt = flight[2];
                if(dist[u] != Integer.MAX_VALUE && dist[u] + wt < temp[v]){
                    temp[v] = dist[u] + wt;
                }
            }
            dist = temp;
        }

     

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst] ;
    }
}

