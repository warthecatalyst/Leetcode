package interview.PDD.Pro4;

import javax.print.attribute.standard.Media;
import java.util.*;

public class Main {
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Double> values = new ArrayList<>();
        double sum = 0;
        double[] average = new double[N];
        double[] mid = new double[N];
        for(int i = 0;i<N;i++){
            int temp = scanner.nextInt();
            values.add((double) temp);
            sum += temp;
            average[i] = sum/(i+1);
            values.sort(new Comparator<Double>() {
                @Override
                public int compare(Double o1, Double o2) {
                    return Double.compare(o1,o2);
                }
            });
            if(i%2==0){
                mid[i] = values.get((i+1)/2);
            }else{
                mid[i] = (values.get((i+1)/2-1)+values.get((i+1)/2))/2;
            }
        }
        for(int i = 0;i<N;i++){
            if(i!=0){
                System.out.print(" ");
            }
            System.out.print((int)Math.ceil(average[i]));
        }
        System.out.println();
        for(int i = 0;i<N;i++){
            if(i!=0){
                System.out.print(" ");
            }
            System.out.print((int)Math.ceil(mid[i]));
        }
    }
}

class MedianFinder {
    private int count;
    private PriorityQueue<Integer> maxHeap; //大顶堆
    private PriorityQueue<Integer> minHeap; //小顶堆

    public MedianFinder() {
        count = 0;
        maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2,o1);
            }
        });
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        count += 1;
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        if(count%2==1){
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if(count%2==0){
            return (double)(maxHeap.peek()+minHeap.peek())/2;
        }else{
            return (double)maxHeap.peek();
        }
    }
}

