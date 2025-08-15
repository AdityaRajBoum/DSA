import java.util.*;

class Solution {
    int[] parent;
    
    int find (int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
    void union(int a, int b){
        int a_parent = find(a);
        int b_parent = find(b);
        
        if(a_parent == b_parent) return ;
        
        else parent[b_parent] = a_parent;
    }
    
    public int spanningTree(int V, int[][] edges) {

        parent = new int [V];
        
        for(int i=0; i< V ; i++)
        parent[i] = i;

        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        
        int netMinWt = 0;
        
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            if(find(u) == find(v)) continue;
            
            union(u,v);
            netMinWt += w;
        }
 
        return netMinWt;
    }
}

