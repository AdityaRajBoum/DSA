 class Solution {

    public boolean isCycleBFS(int u, Map<Integer, ArrayList<Integer>> adj, boolean[] visited, int parent) {
        Queue<Integer> q = new LinkedList<>();
        q.add(u);
        visited[u] = true;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int v : adj.get(curr)) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                } else if (v != parent) {
                    return true;
                }
            }
        }
        return false;
    }
     
}
