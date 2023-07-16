package forInter.NiukeCompetition2.Pro3;

import java.util.*;

public class Main {
    private static final int[][] directions = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
    static Scanner scanner = new Scanner(System.in);
    static char[][] grid;
    static boolean[][] vis;
    static int n;
    public static void main(String[] args) {
        n = scanner.nextInt();
        grid = new char[n][];
        vis = new boolean[n][];
        for(int i = 0;i<n;i++){
            grid[i] = scanner.next().toCharArray();
            vis[i] = new boolean[grid[i].length];
        }
        boolean flag = false;
        for(int j = 0;j<grid[0].length;j++){    //0行j列
            if(grid[0][j]=='1'||vis[0][j]){
                continue;
            }
            if(dfs(0,j)){
                flag = true;
                break;
            }
        }
        if(flag){
            System.out.println(1);
        }else{
            System.out.println(-1);
        }
    }

    private static boolean dfs(int sx,int sy){
        vis[sx][sy] = true;
        if(sx>=n-1){
            return true;
        }
        for(int i = 0;i<directions.length;i++){
            int newx = sx+directions[i][0],newy = sy+directions[i][1];
            if(isIn(newx,newy)&&grid[newx][newy]!='1'&&!vis[newx][newy]){
                boolean res = dfs(newx,newy);
                if(res){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isIn(int x,int y){
        return x>=0&&x<n&&y>=0&&y<grid[x].length;
    }


}
