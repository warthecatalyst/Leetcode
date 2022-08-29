package Pro6163;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] ans = solution.buildMatrix(3,new int[][]{{1,2},{3,2}},new int[][]{{2,1},{3,2}});
        for(int[] ans1:ans){
            System.out.println(Arrays.toString(ans1));
        }
    }
}

class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] matrix = new int[k][k];
        //create row graph
        List<Integer>[] rowgraph = new List[k];
        Set<Integer> rowstarts = new HashSet<>();
        for(int i = 0;i<k;i++){
            rowgraph[i] = new ArrayList<>();
        }

        for(int[] rowCondition:rowConditions){
            int from = rowCondition[0]-1,to = rowCondition[1]-1;
            if(rowgraph[from].contains(to)){
                continue;
            }
            rowgraph[from].add(to);
            rowstarts.add(from);
            rowstarts.remove(to);
        }

        if(isCircle(k,rowgraph,rowstarts)){
            return new int[0][0];
        }

        //create col graph
        List<Integer>[] colgraph = new List[k];
        Set<Integer> colstarts = new HashSet<>();
        for(int i = 0;i<k;i++){
            colgraph[i] = new ArrayList<>();
        }

        for(int[] colCondition:colConditions){
            int from = colCondition[0]-1,to = colCondition[1]-1;
            if(colgraph[from].contains(to)){
                continue;
            }
            colgraph[from].add(to);
            colstarts.add(from);
            colstarts.remove(to);
        }

        if(isCircle(k,colgraph,colstarts)){
            return new int[0][0];
        }

//        for(int i = 0;i<k;i++){
//            System.out.println(rowgraph[i]);
//            System.out.println(colgraph[i]);
//        }

        int[] rowLines = new int[k];
        Set<Integer> rowSet = new HashSet<>();
        for(int i = 0;i<k;i++){
            rowSet.add(i);
        }
        int index = 0;
        Queue<Integer> queue = new ArrayDeque<>(rowstarts);
        for(int num:rowstarts){
            rowLines[index++] = num+1;
            rowSet.remove(num);
        }
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int num:rowgraph[cur]){
                if(!rowSet.contains(num)){
                    continue;
                }
                queue.add(num);
                rowLines[index++] = num+1;
                rowSet.remove(num);
            }
        }
        for(int num:rowSet){
            rowLines[index++] = num+1;
        }
        //num->row
        Map<Integer,Integer> rowMap = new HashMap<>();
        for(int i = 0;i<k;i++){
            int num = rowLines[i];
            rowMap.put(num,i);
        }
        //System.out.println(rowMap);
        index = 0;
        for(int i = 0;i<k;i++){
            rowSet.add(i);
        }
        queue.addAll(colstarts);
        for(int num:colstarts){
            rowLines[index++] = num+1;
            rowSet.remove(num);
        }
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int num:colgraph[cur]){
                if(!rowSet.contains(num)){
                    continue;
                }
                queue.add(num);
                rowLines[index++] = num+1;
                rowSet.remove(num);
            }
        }

        for(int num:rowSet){
            rowLines[index++] = num+1;
        }
        //num->row
        Map<Integer,Integer> colMap = new HashMap<>();
        for(int i = 0;i<k;i++){
            int num = rowLines[i];
            colMap.put(num,i);
        }

        for(int i = 1;i<=k;i++){
            int row = rowMap.get(i), col = colMap.get(i);
            matrix[row][col] = i;
        }
        return matrix;
    }

    //写错了
    boolean isCircle(int k,List<Integer>[] graph,Set<Integer> starts){
        for(int num:starts){
            boolean[] isVisit = new boolean[k];
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(num);
            while(!queue.isEmpty()){
                int cur = queue.poll();
                isVisit[cur] = true;
                for(int next:graph[cur]){
                    if(isVisit[next]){
                        return true;
                    }
                    queue.add(next);
                }
            }
        }
        return false;
    }
}
