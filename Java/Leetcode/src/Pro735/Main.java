package Pro735;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans = solution.asteroidCollision(new int[]{-2,-1,1,2});
        System.out.println(Arrays.toString(ans));
    }
}

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> asteroidsList = new ArrayList<>();
        for(int ast:asteroids){
            asteroidsList.add(ast);
        }
        int index = -1;
        while((index = isOver(asteroidsList))!=-1){
            int indexNum = asteroidsList.get(index),indexNum_1 = asteroidsList.get(index+1);
            if(Math.abs(indexNum)==Math.abs(indexNum_1)){
                //删除两个
                asteroidsList.remove(index);
                asteroidsList.remove(index);
            }else if(Math.abs(indexNum)<Math.abs(indexNum_1)){
                //删除左边
                asteroidsList.remove(index);
            }else{
                asteroidsList.remove(index+1);
            }
        }
        return asteroidsList.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int isOver(List<Integer> asteroidList){
        for(int i = 0;i<asteroidList.size()-1;i++){
            if(asteroidList.get(i)>0&&asteroidList.get(i+1)<0){
                return i;
            }
        }
        return -1;
    }
}
