import java.util.*;
public class Solution {
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int colors [] = new int [V];
        Arrays.fill(colors, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        colors[i] = 1;
        while(!q.isEmpty()){
            int u = q.poll();
            for(int v : adj.get(u)){
                if(colors[v] == colors[u])
                    return false;
                else if(colors[v] == -1){
                    colors[v] = 1-colors[u];
                    q.offer(v);
                }
            }
        }
        return true;
    }
}
