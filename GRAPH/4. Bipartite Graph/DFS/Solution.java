import java.util.*;

/*
 * Only odd cyclic graphs are bipartite
*/
class Solution {
        public boolean isBipartiteDFS(int u, int currColor, int [] colors,  Map<Integer, ArrayList<Integer>> adj){
        colors[u] = currColor;

         if(adj.containsKey(u)) {
            for(int v : adj.get(u)){
                if(colors[v] == currColor) 
                    return false;
                else if(colors[v] == -1){
                    if(!isBipartiteDFS(v, 1-currColor, colors, adj))
                        return false;
                }
            }
         }
        return true;
    }
    
    public boolean isBipartite(int V, int[][] edges) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        
        int colors [] = new int [V];
        Arrays.fill(colors, -1);
        for(int i=0; i<V; i++){
            if(colors[i] == -1){
                if(!isBipartiteDFS(i,0, colors, adj))
                    return false;
            }
        }
        return true;
    }
}