import java.util.ArrayList;

public class Solution {

    public boolean isCycleDFS(int u, Map<Integer, ArrayList<Integer>> adj, boolean[] visited, int parent) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                if (isCycleDFS(v, adj, visited, u)) {
                    return true;
                }
            } else if (v != parent) {
                return true;
            }
        }
        return false;
    }
      
}
