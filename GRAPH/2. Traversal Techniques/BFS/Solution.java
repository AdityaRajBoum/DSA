package BFS;

import java.util.*;
class Solution {
    /*
    used When keeping track of the levels is necessary
    For example : finding the distance between two nodes
                  finding the diameter of a tree
    */ 
    ArrayList<Integer> result = new ArrayList<>();
    public void BFSLevel(Map<Integer, ArrayList<Integer>>adj, int source) {
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        result.add(source);
        int level =1;
        while (!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0){
                int curr = q.poll();                
                for (int v : adj.get(curr)) {
                    // Do something
                    q.add(v);
                    result.add(v);

                }
            }
            level++;
        }
        
    }

        /*
         * used when keeping track of levels is not that necessary
         * For example : finding the shortest path between two nodes
        */
    public void BFS(Map<Integer, ArrayList<Integer>>adj, int source) {
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        result.add(source);
        while (!q.isEmpty()) {
            int curr = q.poll();                
            for (int v : adj.get(curr)) {
                // Do something
                result.add(v);
                q.add(v);
            } 
        }
    }
}