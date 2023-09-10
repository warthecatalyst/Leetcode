package interview.threadPool.TestThreadPool;

import java.util.*;
import java.util.concurrent.*;

public class TestThreadPool {
    static Random random = new Random();
    public static void main(String[] args) {
        TestThreadPool testThreadPool = new TestThreadPool(4, 10);
        for (int loop = 0;loop<10;loop++) {
            int n = random.nextInt(100)+1;
            int sumAll = (n+1) * n / 2;
            testThreadPool.submit( ()-> {
                int sum = 0;
                for(int i = 1;i <= n;i++) {
                    sum += i;
                }
                return sum;
            }, result -> System.out.printf("任务运行结束，期待得到 %d, 实际得到%d\n", sumAll, result));
        }
    }

    private int poolSize;   //线程池的大小
    private BlockingQueue<FutureTask<Void>> taskQueue;  //线程队列
    private WorkThread[] workThreads;

    public TestThreadPool(int poolSize, int maxThreadSize) {
        this.poolSize = poolSize;
        this.taskQueue = new ArrayBlockingQueue<>(maxThreadSize);
        workThreads = new WorkThread[poolSize];
        for(int i = 0;i <workThreads.length; i++) {
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
    }

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
                    FutureTask<Void> task = taskQueue.take();
                    task.run();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public interface CallBack<T> {
        void onComplete(T result);
    }

}
