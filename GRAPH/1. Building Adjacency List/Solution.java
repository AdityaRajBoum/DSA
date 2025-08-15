import java.util.*;

class Solution {
    public Map<Integer, ArrayList<Integer>> BuildList(int [][]edges){
        int V = edges.length;
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        
        for(int i=0; i<V;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            adj.computeIfAbsent(u, k -> new ArrayList<Integer>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<Integer>()).add(u);
        }
        return adj;
    }
}