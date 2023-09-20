package JavaLearning.MyThreadPool;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyThreadPool {
    static Random random = new Random();
    // 核心线程
    private WorkThread[] threads;
    // 核心线程数
    private int poolSize;

    private BlockingQueue<FutureTask<Void>> taskQueue;

    public MyThreadPool(int poolSize, int queueSize) {
        this.poolSize = poolSize;
        this.taskQueue = new ArrayBlockingQueue<>(queueSize);
        this.threads = new WorkThread[poolSize];
        for(int i = 0; i < poolSize;i++) {
            threads[i] = new WorkThread();
            threads[i].start(); //启动这些工作线程
        }
    }

    //提交任务
    public <T> void submit(Callable<T> task, CallBack<T> callBack) {
        FutureTask<Void> futureTask = new FutureTask<>(() -> {
            T result = task.call();
            callBack.onComplete(result);
            return null;
        });
        taskQueue.add(futureTask);
    }

    private class WorkThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    FutureTask<Void> futureTask = taskQueue.take();
                    futureTask.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public interface CallBack<T> {
        void onComplete(T result);
    }
}
