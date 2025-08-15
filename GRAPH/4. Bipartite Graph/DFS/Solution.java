import java.util.*;

/*
 * Only odd cyclic graphs are bipartite
*/
class Solution {
    public boolean isBipartiteDFS(int u, int currColor, int [] colors, ArrayList<ArrayList<Integer>> adj){
        colors[u] = currColor;

        for(int v : adj.get(u)){
            if(colors[v] == currColor) 
                return false;
            else if(colors[v] == -1){
                if(!isBipartiteDFS(v, 1-currColor, colors, adj))
                    return false;
            }
        }
        return true;
    }
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int colors [] = new int [V];
        Arrays.fill(colors, -1);
        for(int i=0; i<V; i++){
            if(colors[i] == -1){
                if(!isBipartiteDFS(i,-1, colors, adj))
                    return false;
            }
        }
        return true;
    }
}
