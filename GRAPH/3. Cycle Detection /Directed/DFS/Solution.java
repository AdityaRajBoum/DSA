import java.util.*;
public class Solution {

    public boolean isCyclicUtil(int u, boolean[] visited, boolean[] recStack, ArrayList<ArrayList<Integer>> adj){
        visited[u] = true;
        recStack[u] = true;
        for(int v : adj.get(u)){
            if(!visited[v] && isCyclicUtil(v, visited, recStack, adj))
                return true;
            else if(recStack[v])
                return true;
        }
        recStack[u] = false;
        return false;
    }

    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        
        for(int i=0; i<V; i++){
            if(!visited[i]){
                if(isCyclicUtil(i, visited, recStack, adj))
                    return true;
            }
        }
        return false;
    }
    
}
