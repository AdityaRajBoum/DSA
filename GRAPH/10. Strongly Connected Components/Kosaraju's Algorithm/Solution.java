import java.util.*;
class Solution {
    // DFS to fill the Stack in Topological Order
    void DFS_fill(int u, boolean []visited, 
                  Stack<Integer>st,ArrayList<ArrayList<Integer>> adj){
        if(visited[u]) return ;
        
        visited[u] = true;
        
        for(int v : adj.get(u)){
            if(!visited[v]){
                DFS_fill(v, visited, st, adj);
            }
        }
        st.push(u);
    }
    
    // DFS on reversed Graph in Topological Order
    void DFS(int u, boolean []visited, 
        ArrayList<ArrayList<Integer>> adjReverse){
        if(visited[u]) return ;
        
        visited[u] = true;
        
        for(int v : adjReverse.get(u)){
            if(!visited[v]){
                DFS(v, visited, adjReverse);
            }
        }
    }

    // Main function 
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {

        int V = adj.size();
        int Scc =0;
        
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean [V];
        
        //Fill the Stack in Topological Order
        for(int i=0; i<V  ; i++){
            if(!visited[i]){
                DFS_fill(i, visited, st, adj);
            }
        }
        
        
        //Reverse the List
        ArrayList<ArrayList<Integer>> adjReverse = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjReverse.add(new ArrayList<>()); 
        }
        
        for(int u=0; u< V; u++){
            for(int v : adj.get(u)){
                adjReverse.get(v).add(u);
            }
        }
        
        //DFS in Topological order;
        visited = new boolean [V];
        while(!st.isEmpty()){
            int curr = st.pop();
            
            if(!visited[curr]){
                DFS(curr, visited,adjReverse);
                Scc++;
            }
        }
        
        
        return Scc;
    }
}