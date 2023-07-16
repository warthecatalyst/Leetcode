package forInter.OppoPro2;

import java.sql.SQLSyntaxErrorException;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = scanner.nextInt();
        List<Integer>[] graph = new List[n];
        for(int i = 0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i < n-1;i++){
            int from = scanner.nextInt()-1,to = scanner.nextInt()-1;
            graph[from].add(to);
            graph[to].add(from);
        }
        boolean[] visited = new boolean[n];
        int root = scanner.nextInt()-1;
        int res = graph[root].size();
        List<Integer> ans = new ArrayList<>();
        visited[root] = true;
        for(int start:graph[root]){
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited[start] = true;
            int cnt = 0;
            while(!queue.isEmpty()){
                int cur = queue.poll();
                cnt++;
                for(int neigh:graph[cur]){
                    if(!visited[neigh]){
                        visited[neigh] = true;
                        queue.add(neigh);
                    }
                }
            }
            ans.add(cnt);
        }
        System.out.println(res);
        Collections.sort(ans);
        for(int i = 0;i<res;i++){
            System.out.print(ans.get(i)+" ");
        }
    }
}
