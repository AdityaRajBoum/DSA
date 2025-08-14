class Solution {
    public int spanningTree(int V, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj.get(u).add(new int[] {v, w});
            adj.get(v).add(new int[] {u, w});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[] visited = new boolean[V];
        int res = 0;

        pq.add(new int[] {0, 0}); // {weight, vertex}

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int wt = p[0];
            int u = p[1];

            if (visited[u]) {
                continue;
            }

            res += wt;
            visited[u] = true;

            for (int[] v : adj.get(u)) {
                if (!visited[v[0]]) {
                    pq.add(new int[] {v[1], v[0]});
                }
            }
        }

        return res;
    }
}
