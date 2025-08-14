class Solution {
    class Pair implements Comparable<Pair>{
        int first;
        int second;
        
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
        
        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.first, other.first);
        }
    }
    public int[] dijkstra(int V, int[][] edges, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int []result = new int [V];
        Arrays.fill(result, Integer.MAX_VALUE);
        
        // Build adjacency list
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            adj.get(u).add(new Pair(weight, v));
            adj.get(v).add(new Pair(weight, u)); 
        }
        
        pq.offer(new Pair(0, src));
        result[src] = 0;
        
        
        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            int d = curr.first;
            int node = curr.second;
            
              for (Pair neighbor : adj.get(node)) {
                int dist = neighbor.first;
                int adjNode = neighbor.second;
                
                if(d+dist < result[adjNode]){
                    result[adjNode] = d+dist;
                    pq.offer(new Pair(d+dist, adjNode));
                }
            }
            
        }
        return result;
        
    }
}