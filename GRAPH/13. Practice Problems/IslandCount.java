// Problem Link : https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1
class Solution {
    int m, n;
    
    int [][] dirs = {{1,0},{1,1},{0,1},{-1,0},{0,-1},{1,-1},{-1,1},{-1,-1}};
    
    void dfs(char [][] grid, int i, int j){
        if(grid[i][j] == 'W') return;
        
        grid[i][j] = 'W';
        
        for(int []dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            
            if(x < m && x >= 0 && y < n && y >= 0 && grid[x][y] == 'L'){
                dfs(grid, x, y);
            }
        }
        
    }
    public int countIslands(char[][] grid) {
        int IslandCount = 0;
        m = grid.length;
        n = grid[0].length;
 
        for(int i = 0; i < m; i++){
            for(int j=0; j < n; j++){
                
                if(grid[i][j] == 'L'){
                    dfs(grid, i , j);
                    IslandCount += 1;
                }
                
            }
        }        
        return IslandCount;
    }
}