package interview.Baidu.Pro3;

import javax.annotation.processing.SupportedSourceVersion;
import java.sql.Array;
import java.util.*;

public class Main {
    static Scanner scanner;
    static int N;   //节点数量
    static String colors;   //各个节点的颜色
    static List<Integer>[] graph;
    static int[][] edges;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        N = scanner.nextInt();
        if(N<=2){
            System.out.println(0);
        }
        colors = scanner.next();
        graph = new List[N];
        edges = new int[N-1][2];
        for(int i = 0;i<N;i++){
            graph[i] = new ArrayList<>();
        }
        //构图
        for(int i = 0;i<N-1;i++){
            int from = scanner.nextInt()-1;
            int to = scanner.nextInt()-1;
            edges[i] = new int[]{from,to};
            graph[from].add(to);
            graph[to].add(from);
        }
//        for(int i = 0;i<N;i++){
//            System.out.println(i + ":" + graph[i]);
//        }
        int ans = 0;
        for(int[] edge:edges){
            ans+=findValue(edge[0],edge[1]);
        }
        System.out.println(ans);
    }

    public static int findValue(int from,int to){
        //System.out.println("from = "+from+", to = "+to);
        graph[from].remove(Integer.valueOf(to));
        graph[to].remove(Integer.valueOf(from));
        //计算左右两侧的联通总数(from侧和to侧)
        List<Integer> list = new ArrayList<>();
        boolean[] vis = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(from);
        vis[from] = true;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            list.add(cur);
            for(int neigh:graph[cur]){
                if(!vis[neigh]){
                    queue.add(neigh);
                    vis[neigh] = true;
                }
            }
        }
        //System.out.println("left = "+list);
        int ans1 = countConsecutive(list);
        //System.out.println("leftans = "+ans1);
        list.clear();
        queue.add(to);
        vis[to] = true;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            list.add(cur);
            for(int neigh:graph[cur]){
                if(!vis[neigh]){
                    queue.add(neigh);
                    vis[neigh] = true;
                }
            }
        }
        //System.out.println("right = "+list);
        int ans2 = countConsecutive(list);
        //System.out.println("rightans = "+ans2);
        graph[from].add(to);
        graph[to].add(from);
        int ans = Math.abs(ans1-ans2);
        //System.out.println("ans = "+ans);
        return ans;
    }

    static int countConsecutive(List<Integer> list){
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<list.size();i++){
            map.put(list.get(i),i);
        }
        //System.out.println("map = "+map);
        UnionFind unionFind = new UnionFind(list.size());
        boolean[] vis = new boolean[N];
        for(int i = 0;i<list.size();i++){
            if(vis[list.get(i)]){
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.add(list.get(i));
            vis[list.get(i)] = true;
            while(!queue.isEmpty()){
                int cur = queue.poll();
                for(int neigh:graph[cur]){
                    if(!vis[neigh]&&colors.charAt(cur)==colors.charAt(neigh)){
                        vis[neigh] = true;
                        queue.add(neigh);
                        unionFind.Union(map.get(cur),map.get(neigh));
                    }
                }
            }
        }
        //System.out.println("fathers = "+Arrays.toString(unionFind.fathers));
        return unionFind.getConsecutive();
    }

    //并查集找连通块数量
    static class UnionFind{
        int n;
        int[] fathers;

        UnionFind(int n){
            this.n = n;
            fathers = new int[n];
            for(int i = 0;i<n;i++){
                fathers[i] = i;
            }
        }

        int find(int x){
            if(x == fathers[x]){
                return x;
            }
            return fathers[x] = find(fathers[x]);
        }

        void Union(int x,int y){
            if(x>y){
                int temp = x;
                x = y;
                y = temp;
            }
            int fatherx = find(x);
            int fathery = find(y);
            if(fatherx==fathery){
                return;
            }
            fathers[y] = fatherx;
        }

        int getConsecutive(){
            Set<Integer> set = new HashSet<>();
            for(int i = 0;i<n;i++){
                fathers[i] = find(i);
                set.add(fathers[i]);
            }
            return set.size();
        }
    }
}
