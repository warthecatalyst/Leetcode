package jianzhi041;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

    }
}

class MovingAverage {

    /** Initialize your data structure here. */
    private int size;
    List<Integer> queue = new LinkedList<>();
    public MovingAverage(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

    public double next(int val) {
        queue.add(val);
        while(queue.size()>size){
            queue.remove(0);
        }
        double sum = 0;
        for(int num:queue){
            sum+=num;
        }
        return sum/queue.size();
    }
}


