package interview.Jingdong.Pro3;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n,m;
    static char[][] chessBoard;
    static List<int[]> chessPositions;
    static Set<Integer> set;
    public static void main(String[] args) {
        n = scanner.nextInt();
        m = scanner.nextInt();
        chessBoard = new char[n][];
        chessPositions = new ArrayList<>();
        set = new HashSet<>();
        for(int i = 0;i<n;i++) {
            chessBoard[i] = scanner.next().toCharArray();
            for(int j = 0;j<chessBoard[i].length;j++) {
                if(chessBoard[i][j] == 'X') {
                    chessPositions.add(new int[]{i, j});
                    set.add(getHash(i, j));
                }
            }
        }
        int count = 0;
        for(int i = 0; i < chessPositions.size();i++) {
            int[] p1 = chessPositions.get(i);
            for(int j = i+1; j < chessPositions.size(); j++) {
                int[] p2 = chessPositions.get(j);
                int dx = p2[0]-p1[0];
                int dy = p2[1]-p1[1];
                int x3 = p2[0] + dy;
                int y3 = p2[1] - dx;
                int x4 = p1[0] + dy;
                int y4 = p1[1] - dx;
                if (set.contains(getHash(x3, y3)) && set.contains(getHash(x4, y4))) {
                    count++;
                }
            }
        }
        System.out.println(count/2);
    }

    static int getHash(int x,int y) {
        return x * 51 + y;
    }
}
