package Pro1958;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        // 检查上下左右四个对角八个方向
        for(int i = 0; i < directions.length; i++) {
            int endx = rMove + directions[i][0], endy = cMove +directions[i][1], step = 0;
            while(checkLegal(endx, endy)) {
                System.out.println(endx + " " + endy);
                if (board[endx][endy] == color && step > 0) {
                    return true;
                }
                if (board[endx][endy] == oppositeColor(color)) {
                    endx += directions[i][0];
                    endy += directions[i][1];
                    step++;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    private boolean checkLegal(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    private char oppositeColor(char color) {
        if (color == 'B') {
            return 'W';
        }
        return 'B';
    }
}
