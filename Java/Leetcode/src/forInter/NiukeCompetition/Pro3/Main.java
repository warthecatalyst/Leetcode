package forInter.NiukeCompetition.Pro3;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static char[][] map;
    static int n,m;
    public static void main(String[] args) {
        n = scanner.nextInt();
        m = scanner.nextInt();
        map = new char[n][];
        int curx = 0;
        int cury = 0;
        for(int i = 0;i<n;i++){
            map[i] = scanner.next().toCharArray();
            for(int j = 0;j<m;j++){
                if(map[i][j]=='R'){
                    curx = i;
                    cury = j;
                }
            }
        }
        char[] moves = scanner.next().toCharArray();
        int nextx = curx,nexty = cury;
        for(int i = 0;i<moves.length;i++){
            switch (moves[i]){
                case 'W':nextx--; break;
                case 'A':nexty--; break;
                case 'S':nextx++; break;
                case 'D':nexty++; break;
            }
        }
    }

    private static boolean outOfBound(int x,int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
