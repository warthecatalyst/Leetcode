package JavaLearning.multithread.MyBlockingQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {
    private final int capacity;
    private final Queue<T> queue;
    private volatile int size;
    private final Lock lock;
    private final Condition isEmpty;
    private final Condition isFull;

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.size = 0;
        this.lock = new ReentrantLock();
        this.isEmpty = lock.newCondition();
        this.isFull = lock.newCondition();
    }

    public void put(T value) throws InterruptedException {
        try {
            lock.lock();
            if (size == capacity) {
                isFull.await();
            }
        } finally {
            lock.unlock();
        }
    }
}
