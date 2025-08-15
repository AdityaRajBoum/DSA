
class Solution {

    boolean isCycle (int n, int [][]dist){
        //cycle check 
       for(int i=0; i<n; i++){
           if(dist[i][i] < 0)
               return true; 
       }
       return false;
    }
    public void floydWarshall(int[][] dist) {
       
       int n = dist.length;
       int m = dist[0].length;
       
       for(int via =0; via < n; via++){
           for(int i =0; i< n ; i++){
               for(int j=0; j< m ; j++){
                if(dist[i][via] != Integer.MAX_VALUE && dist[via][j] != Integer.MAX_VALUE)
                   dist[i][j] = Math.min(dist[i][j], dist[i][via]+dist[via][j]);
               }
           }
       }

       
       
    }
}