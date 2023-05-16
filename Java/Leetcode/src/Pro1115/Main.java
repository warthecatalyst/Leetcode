package Pro1115;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

    }
}

class FooBar {
    private int n;
    private final AtomicInteger index = new AtomicInteger(0);
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(index.get()==1){
                Thread.yield();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            index.incrementAndGet();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(index.get()==0){
                Thread.yield();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            index.decrementAndGet();
        }
    }
}
