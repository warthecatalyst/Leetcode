package interview.threadPool;

import java.util.*;
import java.util.concurrent.*;

public class MyThreadPool {
    static Random random = new Random();
    public static void main(String[] args) {
        MyThreadPool threadPool = new MyThreadPool(4, 10);
        for(int i = 0;i<=10;i++) {
            int n = random.nextInt(100)+1;
            int sum = n * (n+1)/2;
            threadPool.submit(() -> {
                int sum1 = 0;
                for (int i1 = 1; i1 <= n; i1++) {
                    sum1 += i1;
                }
                return sum1;
            }, result -> System.out.printf("任务执行完毕，结果为%d, 预期结果为%d\n", result, sum));
        }

    }

    private int poolSize;   //线程池的大小
    private BlockingQueue<FutureTask<Void>> taskQueue;  //执行的任务队列
    private WorkThread[] workThreads;
    public MyThreadPool(int poolSize, int queueSize) {
        this.poolSize = poolSize;
        this.taskQueue = new ArrayBlockingQueue<>(queueSize);
        //创建工作线程
        this.workThreads = new WorkThread[poolSize];
        for(int i = 0;i<poolSize;i++) {
            workThreads[i] = new WorkThread();
            workThreads[i].start(); //启动这些工作线程
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

    //工作线程类
    private class WorkThread extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    FutureTask<Void> futureTask = taskQueue.take();
                    futureTask.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 回调接口
    public interface CallBack<T> {
        void onComplete(T result);
    }
}
