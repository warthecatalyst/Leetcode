package interview.Mayi.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int N;
    static int[] idColors;
    static List<Integer>[] graph;
    public static void main(String[] args) {
        N = scanner.nextInt();
        String colors = scanner.next();
        idColors = new int[N];
        for(int i = 0;i<colors.length();i++){
            idColors[i] = colors.charAt(i) == 'R'?1:0;
        }
        graph = new List[N];
        for(int i = 0;i<N;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i<N-1;i++){
            int from = scanner.nextInt()-1;
            int to = scanner.nextInt()-1;
            graph[from].add(to);
            graph[to].add(from);
        }
        int costRed = changeColorsCost(1);
        int costWhite = changeColorsCost(0);
        //System.out.println("costRed = "+costRed+", costWhite = "+costWhite);
        System.out.println(Math.min(costRed,costWhite));
    }

    public static int changeColorsCost(int rootColor){
        int ans = 0;
        boolean[] visited = new boolean[N];
        //BFS遍历修改颜色
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,rootColor});
        visited[0] = true;
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int curIdx = cur[0], curColor = cur[1];
            if(idColors[curIdx]!=curColor){
                ans++;
            }
            int nextColor = 1-curColor;
            for(int neigh:graph[curIdx]){
                if(visited[neigh]){
                    continue;
                }
                queue.add(new int[]{neigh,nextColor});
                visited[neigh] = true;
            }
        }
        return ans;
    }
}
