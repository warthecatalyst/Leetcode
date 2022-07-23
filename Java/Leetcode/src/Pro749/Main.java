package Pro749;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    static final int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public int containVirus(int[][] isInfected) {
        int m = isInfected.length, n = isInfected[0].length;
        int ans = 0;
        while(true){
            List<Set<Integer>> neighbours = new ArrayList<>();
            List<Integer> firewalls = new ArrayList<>();
            for(int i = 0;i<m;i++){
                for(int j = 0;j<n;j++){
                    if(isInfected[i][j]==1){
                        Queue<int[]> queue = new ArrayDeque<>();
                        queue.offer(new int[]{i,j});
                        Set<Integer> neighbour = new HashSet<>();
                        int firewall = 0, idx = neighbours.size() + 1;
                        isInfected[i][j] = -idx;

                        while(!queue.isEmpty()){
                            int[] arr = queue.poll();
                            int x = arr[0], y = arr[1];
                            for (int d = 0; d < 4; ++d) {
                                int nx = x + directions[d][0], ny = y + directions[d][1];
                                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                    if (isInfected[nx][ny] == 1) {
                                        queue.offer(new int[]{nx, ny});
                                        isInfected[nx][ny] = -idx;
                                    } else if (isInfected[nx][ny] == 0) {
                                        ++firewall;
                                        neighbour.add(getHash(nx, ny));
                                    }
                                }
                            }
                        }
                        neighbours.add(neighbour);
                        firewalls.add(firewall);
                    }
                }
            }
            if (neighbours.isEmpty()) {
                break;
            }

            int idx = 0;
            for (int i = 1; i < neighbours.size(); ++i) {
                if (neighbours.get(i).size() > neighbours.get(idx).size()) {
                    idx = i;
                }
            }
            ans += firewalls.get(idx);
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (isInfected[i][j] < 0) {
                        if (isInfected[i][j] != -idx - 1) {
                            isInfected[i][j] = 1;
                        } else {
                            isInfected[i][j] = 2;
                        }
                    }
                }
            }
            for (int i = 0; i < neighbours.size(); ++i) {
                if (i != idx) {
                    for (int val : neighbours.get(i)) {
                        int x = val >> 16, y = val & ((1 << 16) - 1);
                        isInfected[x][y] = 1;
                    }
                }
            }
            if (neighbours.size() == 1) {
                break;
            }
        }
        return ans;
    }

    private int getHash(int x,int y){
        return x<<16^y;
    }
}