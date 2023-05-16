package Pro1116;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(50);
        Runnable printZero = new PrintZero(zeroEvenOdd);
        Runnable printOdd = new PrintOdd(zeroEvenOdd);
        Runnable printEven = new PrintEven(zeroEvenOdd);
        printZero.run();
        printEven.run();
        printOdd.run();
    }
}

class ZeroEvenOdd {
    private int n;
    private final AtomicInteger odd = new AtomicInteger(0); //输出0的线程向两个管道发送数据(变为1)，然后由奇线程和偶线程分别消费
    private final AtomicInteger even = new AtomicInteger(0);
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            while (odd.get() != 0 || even.get() != 0) {
                Thread.yield();
            }

            printNumber.accept(0);
            if (i % 2 != 0) {
                odd.incrementAndGet();
            } else {
                even.incrementAndGet();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                while (even.get() != 1) {
                    Thread.yield();
                }
                printNumber.accept(i);
                even.decrementAndGet();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1;i<=n;i++){
            if(i%2==1){
                while(odd.get()!=1){
                    Thread.yield();
                }
                printNumber.accept(i);
                odd.decrementAndGet();
            }
        }
    }
}

class PrintZero implements Runnable{
    private ZeroEvenOdd zeroEvenOdd;
    public PrintZero(ZeroEvenOdd zeroEvenOdd){
        this.zeroEvenOdd = zeroEvenOdd;
    }

    @Override
    public void run() {
        try {
            zeroEvenOdd.zero(System.out::println);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class PrintEven implements Runnable{
    private ZeroEvenOdd zeroEvenOdd;
    public PrintEven(ZeroEvenOdd zeroEvenOdd){
        this.zeroEvenOdd = zeroEvenOdd;
    }

    @Override
    public void run() {
        try {
            zeroEvenOdd.even(System.out::println);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class PrintOdd implements Runnable{
    private ZeroEvenOdd zeroEvenOdd;
    public PrintOdd(ZeroEvenOdd zeroEvenOdd){
        this.zeroEvenOdd = zeroEvenOdd;
    }

    @Override
    public void run() {
        try {
            zeroEvenOdd.odd(System.out::println);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
