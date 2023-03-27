package interview.Mihayo.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n,m;
    static final int[][] Directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};   //上下左右
    public static void main(String[] args) {
        n = scanner.nextInt();
        m = scanner.nextInt();
        scanner.nextLine();
        char[][] trueColors = new char[n][];
        char[][] seenColors = new char[n][m];
        for(int i = 0;i<n;i++){
            String line = scanner.nextLine();
            trueColors[i] = line.toCharArray();
            for(int j = 0;j<m;j++){
                if(line.charAt(j)=='R'){
                    seenColors[i][j] = 'R';
                }else{
                    seenColors[i][j] = 'B';
                }
            }
        }
        int trueConnections = getConnections(trueColors);
        int seenConnections = getConnections(seenColors);
        //System.out.println(trueConnections+" "+seenConnections);
        System.out.println(trueConnections-seenConnections);
    }

    static int getConnections(char[][] colors){
        int[][] fathers = new int[n][m];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                fathers[i][j] = i*m+j;
            }
        }
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(fathers[i][j]!=i*m+j){
                    continue;
                }
                Queue<int[]> queue = new LinkedList<>();
                boolean[][] vis = new boolean[n][m];
                queue.add(new int[]{i,j});
                vis[i][j] = true;
                while(!queue.isEmpty()){
                    int[] cur = queue.poll();
                    int curx = cur[0], cury = cur[1];
                    fathers[curx][cury] = i*m+j;
                    for(int k = 0;k<4;k++){
                        int newx = curx+Directions[k][0], newy = cury+Directions[k][1];
                        if(isIn(newx,newy)&&!vis[newx][newy]&&colors[newx][newy]==colors[curx][cury]){
                            queue.add(new int[]{newx,newy});
                            vis[newx][newy] = true;
                        }
                    }
                }
            }
        }
        Set<Integer> seenFathers = new HashSet<>();
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                seenFathers.add(fathers[i][j]);
            }
        }
        return seenFathers.size();
    }

    static boolean isIn(int x,int y){
        return x>=0&&x<n&&y>=0&&y<m;
    }
}
