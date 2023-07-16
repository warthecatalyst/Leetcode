package interview.Amazon;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        //int ans = getMinRecoverTime(4,new int[][]{{0, 1, 1}, {1, 2, 5}, {1, 3, 7}, {0, 2, 8}});
        int ans = getMinRecoverTime(4,new int[][]{{0, 1, 1}, {2, 3, 5}, {1, 3, 6}, {0, 2, 8}});
        System.out.println(ans);
    }

    public static int getMinRecoverTime(int N,int[][] logs){
        UnionFind unionFind = new UnionFind(N);
        for(int[] log:logs){
            int x = log[0],y = log[1];
            unionFind.union(x,y);
            //System.out.println(Arrays.toString(unionFind.fathers));
            if(unionFind.isUnioned()){
                return log[2];
            }
        }
        return -1;
    }
}

class UnionFind{
    int N;
    int[] fathers;
    Set<Integer>[] sets;

    UnionFind(int N){
        this.N = N;
        this.fathers = new int[N];
        for(int i = 0;i<N;i++){
            fathers[i] = i;
        }
    }

    int find(int x){
        if(fathers[x]==x){
            return x;
        }
        return fathers[x]=find(fathers[x]);
    }

    void union(int x,int y){
        int fatherX = find(x),fatherY = find(y);
        if(fatherX==fatherY){
            return;
        }
        if(x>y){
            for(int i = 0;i<N;i++){
                if(fathers[i]==fatherX){
                    fathers[i] = fatherY;
                }
            }
        }else{
            for(int i = 0;i<N;i++){
                if(fathers[i]==fatherY){
                    fathers[i] = fatherX;
                }
            }
        }
    }

    boolean isUnioned(){
        for(int i = 0;i<N;i++){
            if(find(i)!=0){
                return false;
            }
        }
        return true;
    }
}
