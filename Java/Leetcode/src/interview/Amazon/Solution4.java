package interview.Amazon;

public class Solution4 {
    public static void main(String[] args) {
        MySolution solution = new MySolution();
        int ans = solution.getMaxPathSum(new int[][]{{1,0,7},
                {2,0,6},
                {3,4,5},
                {0,3,0},
                {9,0,30}});
        System.out.println(ans);
    }
}

class MySolution{
    static final int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    int m,n;
    boolean[][] visited;
    int ans = 0;
    public int getMaxPathSum(int[][] grid){
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(grid[i][j]>0&&!visited[i][j]){
                    dfs(i,j,grid,0);
                }
            }
        }
        return ans;
    }

    void dfs(int x,int y,int[][] grid,int curSum){
        visited[x][y] = true;
        curSum += grid[x][y];
        if(curSum>ans){
            ans = curSum;
        }
        for(int k = 0;k<4;k++){
            int newX = x+directions[k][0],newY = y+directions[k][1];
            if(isIn(newX,newY)&&grid[newX][newY]>0&&!visited[newX][newY]){
                dfs(newX,newY,grid,curSum);
            }
        }
    }

    boolean isIn(int x,int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }
}
