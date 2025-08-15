import java.util.*;
// Topological Sort using DFS
public class Solution {
    public void DFS(Map<Integer, ArrayList<Integer>> adj, int u, boolean[]visited, Stack<Integer>st){
        visited[u] = true ;

        for(int v : adj.get(u)){
            if(!visited[v]){
                DFS(adj,v, visited, st);
            }
        }
        st.push(u);
    }
    public List<Integer> TopologicalSort(int V,Map<Integer, ArrayList<Integer>> adj){
        List<Integer> result = new ArrayList<>();
        boolean []visited = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<V; i++){
            if(!visited[i])
                DFS(adj, i, visited, st);
        }

        while (!st.isEmpty()) {
            result.add(st.pop());
        }
        return result;
    }
    
}
