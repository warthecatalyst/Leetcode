package Pro675;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    static final int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        int row = forest.size();
        int col = forest.get(0).size();
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j});
                }
            }
        }
        trees.sort(Comparator.comparingInt(a -> forest.get(a[0]).get(a[1])));

        int cx = 0;
        int cy = 0;
        int ans = 0;
        for (int[] tree : trees) {
            int steps = bfs(forest, cx, cy, tree[0], tree[1]);
            if (steps == -1) {
                return -1;
            }
            ans += steps;
            cx = tree[0];
            cy = tree[1];
        }
        return ans;
    }

    public int bfs(List<List<Integer>> forest, int sx, int sy, int tx, int ty){
        if(sx==tx&&sy==ty){
            return 0;
        }
        int row = forest.size(),col = forest.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        queue.offer(new int[]{sx,sy,0});
        visited[sx][sy] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curx = cur[0],cury = cur[1],curStep = cur[2];
            for(int i = 0;i<4;i++){
                int newx = curx+directions[i][0],newy = cury+directions[i][1];
                if(canVisit(newx,newy,row,col,visited)){
                    if(forest.get(newx).get(newy)>0){
                        if(newx==tx&&newy==ty){
                            return curStep+1;
                        }
                        queue.offer(new int[]{newx,newy,curStep+1});
                        visited[newx][newy] = true;
                    }
                }
            }
        }
        return -1;
    }

    public boolean canVisit(int x,int y,int row,int col,boolean[][] visited){
        return x>=0&&x<row&&y>=0&&y<col&&!visited[x][y];
    }
}
