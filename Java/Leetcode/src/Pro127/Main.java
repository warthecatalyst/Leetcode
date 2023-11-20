package Pro127;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] wordArray = new String[]{"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.stream(wordArray).collect(Collectors.toList());
        Solution solution = new Solution();
        solution.ladderLength("hit","cog",wordList);

    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)){
            return 0;
        }
        wordSet.add(beginWord);
        wordList = new ArrayList<>(wordSet);
        List<Integer>[] graph = new List[wordList.size()];
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        int start = -1, end = -1;
        for(int i = 0;i<wordList.size();i++){
            if(beginWord.equals(wordList.get(i))){
                start = i;
            }else if(endWord.equals(wordList.get(i))){
                end = i;
            }
            for(int j = i+1;j<wordList.size();j++){
                String wordi = wordList.get(i), wordj = wordList.get(j);
                if(isNeighbour(wordi,wordj)){
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

//        System.out.println(start);
//        for(int i = 0;i<graph.length;i++){
//            System.out.print(wordList.get(i)+":");
//            System.out.println(graph[i]);
//        }

        //bfs寻找从start到end的最短路径
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[wordList.size()];
        queue.add(new int[]{start,1});
        visited[start] = true;
        while(!queue.isEmpty()){
            int[] ints = queue.poll();
            int cur = ints[0], step = ints[1];
            //System.out.println("current word = "+wordList.get(cur)+", step = "+step);
            if(cur == end){
                return step;
            }
            for(int neigh:graph[cur]){
                if(visited[neigh]){
                    continue;
                }
                queue.add(new int[]{neigh,step+1});
                visited[neigh] = true;
            }
        }
        return 0;
    }

    public boolean isNeighbour(String w1,String w2){
        if(w1.length()!=w2.length()){
            return false;
        }
        int cnt = 0;
        for(int i = 0;i<w1.length();i++){
            char c1 = w1.charAt(i);
            char c2 = w2.charAt(i);
            if(c1 != c2){
                cnt++;
                if(cnt>=2){
                    return false;
                }
            }
        }
        return true;
    }
}
