package Pro593;

import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if(Arrays.equals(p1,p2)){
            return false;
        }
        int p1p2 = (p1[0]-p2[0])*(p1[0]-p2[0])+(p1[1]-p2[1])*(p1[1]-p2[1]),p1p3 = (p1[0]-p3[0])*(p1[0]-p3[0])+(p1[1]-p3[1])*(p1[1]-p3[1])
                ,p1p4 = (p1[0]-p4[0])*(p1[0]-p4[0])+(p1[1]-p4[1])*(p1[1]-p4[1]);
        List<Integer> squareList = new ArrayList<>();
        squareList.add(p1p2);
        squareList.add(p1p3);
        squareList.add(p1p4);
        Collections.sort(squareList);
        //System.out.println(squareList);
        if(!isTriangle(squareList)){
            return false;
        }
        squareList.clear();
        int p3p2 = (p3[0]-p2[0])*(p3[0]-p2[0])+(p3[1]-p2[1])*(p3[1]-p2[1]),p3p4 = (p3[0]-p4[0])*(p3[0]-p4[0])+(p3[1]-p4[1])*(p3[1]-p4[1]);
        squareList.add(p1p3);
        squareList.add(p3p2);
        squareList.add(p3p4);
        Collections.sort(squareList);
        if(!isTriangle(squareList)){
            return false;
        }
        int p2p4 = (p4[0]-p2[0])*(p4[0]-p2[0])+(p4[1]-p2[1])*(p4[1]-p2[1]);
        squareList.clear();
        squareList.add(p1p2);
        squareList.add(p3p2);
        squareList.add(p2p4);
        Collections.sort(squareList);
        if(!isTriangle(squareList)){
            return false;
        }
        return isTriangle(squareList);
    }

    private boolean isTriangle(List<Integer> sq){
        return Objects.equals(sq.get(0), sq.get(1)) &&sq.get(2)==(sq.get(0)+sq.get(1));
    }
}
