package Pro622;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        boolean flag = circularQueue.enQueue(1);
        System.out.println(flag);
        flag = circularQueue.enQueue(2);
        System.out.println(flag);
        flag = circularQueue.enQueue(3);
        System.out.println(flag);
        System.out.println(Arrays.toString(circularQueue.data));
        flag = circularQueue.enQueue(4);
        System.out.println(flag);
        int rear = circularQueue.Rear();
        System.out.println(rear);
        System.out.println(circularQueue.isFull());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.enQueue(4));
        System.out.println(circularQueue.Rear());
    }
}

class MyCircularQueue {
    int length;
    int[] data;
    int front,rear;
    public MyCircularQueue(int k) {
        this.length = k+1;
        data = new int[k+1];
        front = rear = 0;
    }

    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        data[rear] = value;
        rear = (rear+1)%length;
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        front = (front+1)%length;
        return true;
    }

    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return data[front];
    }

    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return data[(rear-1+length)%length];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return front == (rear+1)%length;
    }
}
