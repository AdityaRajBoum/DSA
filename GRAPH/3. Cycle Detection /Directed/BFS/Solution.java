/*
* The topological sort is a linear ordering of the vertices such that for every edge u->v, u comes before v in the ordering.
* The topological sort for a directed acyclic graph (DAG) is possible if and only if the graph is acyclic.
 * Topological Sort using BFS is called Kahn's algorithm.
*/

class Solution {
    public boolean TopologicalSort(int V, int [][] edges) {
        int []indegree = new int [V];
        List<Integer> result = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for(int []edge : edges){
            int u = edge[0];
            int v = edge[1];
            adj.computeIfAbsent(u, k -> new ArrayList<Integer>()).add(v);
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i< V; i++){
            if(indegree[i] == 0) 
            q.offer(i);
        }

        while (!q.isEmpty()) {
            int curr = q.poll();
            if(adj.containsKey(curr)){
            for (int v : adj.get(curr)) {
                    indegree[v]--;
                    if(indegree[v] == 0)
                        q.offer(v);
            }
        }
            result.add(u);
        }
        // Result contanins the topological sorted order of vertices
        return result.size() == V;
    }
}