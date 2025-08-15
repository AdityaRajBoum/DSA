// User function Template for Java

class Solution {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int [] result = new int [V];
        Arrays.fill(result, 100000000);
        result[src] = 0;
        
        for(int i=1; i<=V-1; i++){
            for(int []edge : edges){
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];
                
                if(result[u] != Integer.MAX_VALUE && result[u]+ weight < result[v]){
                    result[v] = result[u]+ weight;
                }
                
            }
        }
        
        int []copy = Arrays.copyOf(result, V);
        for(int []edge : edges){
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];
                
                if(copy[u] != Integer.MAX_VALUE && copy[u]+ weight < copy[v]){
                    copy[v] = copy[u]+ weight;
                }
            }
        
        for(int i=0; i< V; i++){
            if(copy[i] != result[i]) return new int []{-1};
        }
        return result;
    }
}
