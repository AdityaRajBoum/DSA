package DFS;
import java.util.*;

public class Solution {
    ArrayList<Integer> result = new ArrayList<>();
    public void DFS(Map<Integer, ArrayList<Integer>>adj, int u, boolean []visited) {
        if(visited[u]) return;
        visited[u] = true;
        result.add(u);

        for (int v : adj.get(u)) {
            if(!visited[v]){
                // Do something
            DFS(adj, v, visited);
            }
        }
    }
    
}
