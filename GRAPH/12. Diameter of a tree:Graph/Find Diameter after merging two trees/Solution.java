import java.util.*;

class Solution {
    int maxDiameter;
    
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int size1 = edges1.length + 1;
        int size2 = edges2.length + 1;
        Map<Integer, List<Integer>> tree1 = new HashMap<>();
        Map<Integer, List<Integer>> tree2 = new HashMap<>();

        for (int[] edge : edges1) {
            tree1.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            tree1.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        for (int[] edge : edges2) {
            tree2.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            tree2.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        int diameter1 =  findDiameter(tree1);
        int diameter2 =  findDiameter(tree2);

        int candidateDiameter = (diameter1 + 1) / 2 + (diameter2 + 1) / 2 + 1;
        return Math.max(candidateDiameter, Math.max(diameter1, diameter2));
    }

    public int findDiameter(Map<Integer, List<Integer>> adjList) {
        // First BFS to find the farthest node from any arbitrary node (e.g., 0)
        List<Integer> farthestNode = BFS(adjList, 0);

        // Second BFS from the farthest node to determine the diameter
        farthestNode = BFS(adjList, farthestNode.get(0));
        return farthestNode.get(1);
    }

    // BFS helper function to find the farthest node and its distance from the source
    public List<Integer> BFS(Map<Integer, List<Integer>> adjList, int sourceNode) {
        Queue<Integer> que = new LinkedList<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        
        // Push source node into the queue
        que.add(sourceNode);
        visited.put(sourceNode, true);

        int maxDistance = 0, farthestNode = sourceNode;

        // Explore neighbors
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int currentNode = que.poll();
                // Update farthest node
                farthestNode = currentNode;

                for (int neighbor : adjList.getOrDefault(currentNode, new ArrayList<>())) {
                    // Explore neighbors
                    if (!visited.getOrDefault(neighbor, false)) {
                        visited.put(neighbor, true);
                        que.add(neighbor);
                    }
                }
            }
            if (!que.isEmpty()) {
                maxDistance++;
            }
        }
        return Arrays.asList(farthestNode, maxDistance);
    }
}