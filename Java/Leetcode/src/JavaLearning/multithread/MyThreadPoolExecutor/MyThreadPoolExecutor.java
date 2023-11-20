package JavaLearning.multithread.MyThreadPoolExecutor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// 自己手撸一个线程池
public class MyThreadPoolExecutor {
    // 线程池是否还能够继续执行现有任务(shutDownNow对线程池所有的Thread都调用Interrupt)
    private volatile boolean RUNNING = true;
    // 线程池是否还能够继续提交任务(shutDown会继续执行目前已经提交的任务，但不能继续提交任务)
    private volatile boolean SHUTDOWN = false;
    // 核心线程数
    private final int corePoolSize;
    // 最大线程数
    private final int maxPoolSize;
    // 空闲线程的存活时间
    private final long survivalTime;
    // 拒绝策略
    private final RejectPolicy rejectPolicy;

    // 当前运行的工作线程数量
    private AtomicInteger poolSize;
    // 工作线程的Set
    private final Set<Worker> workerSet = new HashSet<>();
    // 线程等待队列
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(500);

    public MyThreadPoolExecutor(int corePoolSize, int maxPoolSize, long survivalTime, RejectPolicy rejectPolicy) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.poolSize = new AtomicInteger(0);
        this.survivalTime = survivalTime;
        this.rejectPolicy = rejectPolicy;
    }

    // 提交任务
    public void submit(Runnable runnable) {
        if (SHUTDOWN) {
            return;
        }
        if (runnable == null) throw new NullPointerException();
        //如果当前工作线程数目小于corePoolSize就创建一个线程执行任务
        if (poolSize.get() < corePoolSize) {
            addThread(runnable, false);
        } else {
            boolean result = queue.offer(runnable);
            // blocking queue已经满了，创建新的工作线程执行任务
            if(!result) {
                // 当小于最大线程数的时候
                if (poolSize.get() < maxPoolSize) {
                    addThread(runnable, true);
                } else {
                    //拒绝策略, discard或者discardOldest
                    switch (rejectPolicy) {
                        // 相当于直接丢弃该runnable
                        case Discard -> {
                            return;
                        }
                        case DiscardOldest -> {
                            try {
                                //直接丢弃oldest
                                Runnable oldest = queue.take();
                                queue.offer(runnable);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    // 新增一个工作线程执行当前任务
    private void addThread(Runnable runnable, boolean isMaxThread) {
        poolSize.getAndIncrement();
        Worker worker = new Worker(runnable, isMaxThread);
        workerSet.add(worker);
        worker.start();
    }

    // shutDown会让当前线程池不能够继续提交
    public void shutDown() {
        this.SHUTDOWN = true;
    }

    // shutDownNow相当于中断当前所有正在执行的Thread
    public void shutDownNow() {
        this.RUNNING = false;
        this.SHUTDOWN = true;
        if(!workerSet.isEmpty()){
            for (Worker worker : workerSet){
                worker.interrupt();
            }
        }
    }

    // 工作线程类
    class Worker extends Thread {
        Runnable currentTask;
        final boolean isMaxThread;
        public Worker(Runnable runnable, boolean isMaxThread) {
            currentTask = runnable;
            this.isMaxThread = isMaxThread;
        }

        @Override
        public void run() {
            while(true) {
                if (!RUNNING) {
                    Thread.interrupted();
                    break;
                }
                if (currentTask != null) {
                    currentTask.run();
                    currentTask = null;
                } else {
                    try {
                        Runnable task = getTask();
                        if (Objects.isNull(task)) {
                            workerSet.remove(this);
                            poolSize.getAndDecrement();
                            return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private Runnable getTask() throws InterruptedException {
            Runnable runnable;
            if (this.isMaxThread) {
                runnable = queue.poll(survivalTime, TimeUnit.MICROSECONDS);
            } else {
                runnable = queue.take();
            }
            return runnable;
        }
    }

    // 拒绝策略的enum类
    public enum RejectPolicy {
        Discard,
        DiscardOldest,
    }
}
