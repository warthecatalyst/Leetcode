package interview.alibaba;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(5);
        Runnable threadPut = new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<5;i++){
                    try {
                        Thread.sleep(1000);
                        myBlockingQueue.put(i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Runnable threadGet = new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<5;i++) {
                    int val = myBlockingQueue.get();
                    System.out.println(val);
                }
            }
        };
        threadPut.run();
        threadGet.run();

        while(true) {

        }

    }

    // 输出和为n的连续正整数序列
    public static List<List<Integer>> getContinuousIntsSumOfN(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if(n%2 == 1) {
            //是奇数的情况下肯定会有
            List<Integer> tmp = new ArrayList<>();
            tmp.add(n/2);
            tmp.add(n/2+1);
            ans.add(tmp);
        }
        for(int i = 3;i < n;i++) {
            if (i%2 == 0 && n%i==i/2) { //奇数偶数判定不同
                int l = n/i;
                List<Integer> tmp = new ArrayList<>();
                if(l-(i/2-1) <= 0) {
                    break;
                }
                for(int j = l-(i/2-1);j<=l+i/2;j++) {
                    tmp.add(j);
                }
                ans.add(tmp);
            } else if(i%2 == 1 && n%i==0){
                int l = n/i;
                List<Integer> tmp = new ArrayList<>();
                if(l-i/2 <= 0) {
                    break;
                }
                for(int j = l-i/2;j<=l+i/2;j++) {
                    tmp.add(j);
                }
                ans.add(tmp);
            }
        }
        return ans;
    }
}

class MyBlockingQueue {
    Queue<Integer> elements;
    int capacity;
    int size;
    Lock lock;

    MyBlockingQueue(int capacity) {
        elements = new LinkedList<>();
        this.capacity = capacity;
        this.size = 0;
        lock = new ReentrantLock();
    }

    public boolean put(int value) {
        try {
            lock.lock();
            while(size == capacity) {
                Thread.yield(); //队列满，让出执行权利
            }
            elements.add(value);
            size++;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        lock.lock();
        while(size == 0) {
            lock.unlock();
            Thread.yield(); //队列为空，等待加入
        }
        int val = elements.poll();
        size--;
        lock.unlock();
        return val;
    }
}
