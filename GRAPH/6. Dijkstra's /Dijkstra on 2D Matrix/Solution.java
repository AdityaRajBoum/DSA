class Solution {
    int[][] dirs = {
            { -1, 0 }, // up
            { 1, 0 }, // down
            { 0, -1 }, // left
            { 0, 1 }, // right
            { -1, -1 }, // up-left
            { -1, 1 }, // up-right
            { 1, -1 }, // down-left
            { 1, 1 } // down-right
    };

    class Pair implements Comparable<Pair> {
        int dist;
        int[] coord;

        public Pair(int dist, int[] coord) {
            this.dist = dist;
            this.coord = coord;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
    int n = grid.length;

    if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
            
    int [][] result = new int [n][n];
    for(int i=0; i< n; i++)
    Arrays.fill(result[i], Integer.MAX_VALUE);

    result[0][0] = 1;


    PriorityQueue<Pair> pq = new PriorityQueue<>();
    pq.offer(new Pair(1, new int []{0,0}));

    while(!pq.isEmpty()){
        Pair curr = pq.poll();

        int d = curr.dist;
        int []coord = curr.coord;
        int x_ = coord[0];
        int y_ = coord[1];
        if(x_ == n-1 && y_ == n-1) return d ;

        for(int []dir : dirs){
        int x = dir[0] + x_;
        int y = dir[1] + y_;


        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0  && 1 + d <  result[x][y]) {
                result[x][y] = 1 + d;
                pq.offer(new Pair(result[x][y],new int[] { x, y}));
            }
        }  
    }
        return -1;
    }
}