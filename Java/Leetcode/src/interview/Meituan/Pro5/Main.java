package interview.Meituan.Pro5;

import java.util.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int N = scanner.nextInt();
        int[] energy = new int[N];
        for(int i = 0;i<N;i++){
            energy[i] = scanner.nextInt();
        }
        List<Integer>[] graph = new List[N];
        for(int i = 0;i<N;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i<N-1;i++){
            int from = scanner.nextInt()-1;
            int to = scanner.nextInt()-1;
            graph[from].add(to);
            graph[to].add(from);
        }
        int[] ans = new int[N];
        Arrays.fill(ans,0);
        for(int i = 0;i<N;i++){
            Queue<int[]> queue = new LinkedList<>();
            boolean[] visited = new boolean[N];
            queue.add(new int[]{i,0});
            visited[i] = true;
            while(!queue.isEmpty()){
                int[] temp = queue.poll();
                int cur = temp[0], dist = temp[1];
                ans[cur]++; //i节点为cur提供了1点能量
                if(dist>=energy[i]){    //它的邻居无法再提供能量了
                    continue;
                }
                for(int neigh:graph[cur]){
                    if(!visited[neigh]){
                        visited[neigh] = true;
                        queue.add(new int[]{neigh,dist+1});
                    }
                }
            }
        }
        for(int i = 0;i<ans.length;i++){
            if(i>0){
                System.out.print(" ");
            }
            System.out.print(ans[i]);
        }
    }
}
