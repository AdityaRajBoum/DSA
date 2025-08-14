class Solution {
    class Pair implements Comparable<Pair> {
        int distance;
        int node;
        public Pair(int distance, int Node){
            this.distance = distance;
            this.node = Node; // fixed
        }
        
        @Override
        public int compareTo(Pair other){
            if (this.distance == other.distance) {
                return Integer.compare(this.node, other.node); // tie-break
            }
            return Integer.compare(this.distance, other.distance);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return this.distance == p.distance && this.node == p.node;
        }

        @Override
        public int hashCode() {
            return Objects.hash(distance, node);
        }
    }
    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        int[]result = new int [V];
        Arrays.fill(result, Integer.MAX_VALUE);
        List<List<Pair>> adj = new ArrayList<>();
          for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int dist= edge[2];
            adj.get(u).add(new Pair(dist, v));
            adj.get(v).add(new Pair(dist, u));
        }
        
        TreeSet<Pair> st = new TreeSet<>();
        result[src] = 0 ; 
        st.add(new Pair(0, src));
        
        while(!st.isEmpty()){
            Pair curr = st.pollFirst(); 
            int d = curr.distance;
            int node = curr.node;
            
            for(Pair v : adj.get(node)){
                int dist = v.distance;
                int adjNode = v.node;
                
                if(d > result[adjNode]) continue;
                
                if(d+ dist < result[adjNode]){
                    if(result[adjNode] != Integer.MAX_VALUE){
                        st.remove(new Pair(result[adjNode], adjNode));
                    }
                    result[adjNode] = d+dist;
                    st.add(new Pair(result[adjNode], adjNode));
                }
            }
        }
        return result;
    }
}