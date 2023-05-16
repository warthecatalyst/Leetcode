package interview.fudan;
import java.util.*;


public class Solution3 {
    static final int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};  //上下左右四个方向
    static int N;   //N*N的网格
    static int[][] graph;
    static int minLength = Integer.MAX_VALUE;
    static boolean[][] visited; //有没有去过
    public static void main(String[] args) {
        N = 3;
        graph = new int[][]{{1,2,3},{0,0,4},{7,6,5}};
        visited = new boolean[N][N];
    }

    // 当前位置，
    static void dfs(int[] currentPosition,int curtHeight,int curLength){
        int curx = currentPosition[0],cury = currentPosition[1];
        for(int i = 0;i<4;i++){
            int nextx = curx+directions[i][0], nexty = cury+directions[i][1];
            if(canVisit(nextx,nexty)){

            }
        }


    }

    static boolean canVisit(int x,int y){
        return x>=0&&x<N&&y>=0&&y<N&&graph[x][y]!=0&&!visited[x][y];
    }
}
