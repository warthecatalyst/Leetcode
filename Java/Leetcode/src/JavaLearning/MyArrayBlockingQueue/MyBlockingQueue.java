package JavaLearning.MyArrayBlockingQueue;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {
    static Random random = new Random();
    public static void main(String[] args) {
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(10);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        int[] vals = new int[400];
        for(int i = 0;i < 400;i++) {
            vals[i] = random.nextInt();
        }

        //提交4个任务给线程池
        for (int i = 0;i < 2;i++) {
            int finalI = i;
            executorService.submit(() -> {
                for(int l = 0;l < 200;l++) {
                    myBlockingQueue.put(vals[l*2+ finalI]);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            executorService.submit(() -> {
                while(true) {
                    int result = myBlockingQueue.take();
                    System.out.println(result);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }


            });
        }

        executorService.shutdown();
    }

    private Queue<T> elements;
    private Lock lock;
    private Condition notEmpty;
    private Condition notFull;
    int capacity;

    public MyBlockingQueue(int capacity) {
        this.elements = new LinkedList<>();
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
        this.capacity = capacity;
    }

    public void put(T item) {
        try {
            lock.lock();
            while (elements.size() == capacity) {
                notFull.await();
            }
            elements.add(item);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        try {
            lock.lock();
            while(elements.isEmpty()) {
                notEmpty.await();
            }
            T item = elements.poll();
            notFull.signalAll();
            return item;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
