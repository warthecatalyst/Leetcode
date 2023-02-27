package interview.ali_shenlong;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//# 题目：实现一个安检排队的简单模型的模拟，需要满足以下约束条件：
//#1. 队首安检通过，则将安检通过人员数据删除即可；
//#2. 安检未通过则将队首重新排到队尾；
//#3. 年龄大于60岁可以插队到比他年龄小的排队者之前，孕妇,残疾人 有最高优先级，直接到队首进行安检, 优先级：孕妇 > 残疾人 > 老人；
//#4. 开始安检时已经有 10 人排队 (请随机生成)；
//#5. 安检人员每 3 秒完成一次检查；
//#6. 被检人员有 1/10 概率不能通过，然后他返回队尾重新安检；
//#7. 每 1～6 秒会有一名新的人员加入到安检队伍；
//#8. 任何一个人在排队的生命周期内最多插队一次；
//#模拟3分钟内，当队伍内人员顺序有变化时候，打印队内所有人的信息（注意代码风格/注意程序的可拓展性）。
public class SimpleQueue implements Runnable{
    static final int FINAL_TIME = 180;
    int curTime;
    int addTime;
    LinkedList<Person> queue;
    Random random;
    Runnable addToQueue;
    Lock lock;
    SimpleQueue(){
        curTime = 0;
        queue = new LinkedList<>();
        random = new Random(System.currentTimeMillis());
        lock = new ReentrantLock();
        addToQueue = new AddToQueue();
        //随机往queue里面加10个人
        for(int i = 0;i<10;i++){
            int isPreg = random.nextInt(2);
            int isBroke = random.nextInt(2);
            queue.add(new Person(random.nextInt(100),isPreg==1,isBroke==1));
        }
    }

    @Override
    public void run() {
        while(curTime<FINAL_TIME){
            try {
                proceedOneSecond();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            curTime++;
        }
    }

    void proceedOneSecond() throws InterruptedException {
        System.out.println("curTime = "+curTime+", AddTime = "+addTime);
        System.out.println(queue);
        //先处理插队的情况，通过冒泡排序进行插队，然后将插过队的都将插过队项置为0
        lock.lock();
        try{
            for(int i = 0;i<queue.size();i++){
                boolean flag = false;
                for(int j = queue.size()-1;j>i;j--){
                    flag = true;
                    if(queue.get(j).compareTo(queue.get(j-1))<0){
                        Person temp = queue.get(j);
                        queue.set(j,queue.get(j-1));
                        queue.set(j-1,temp);
                    }
                }
                queue.get(i).isProceed = flag;
            }
            System.out.println(queue);
        }finally {
            lock.unlock();
        }

        if(curTime%3==2){
            if(!queue.isEmpty()){
                lock.lock();
                try {
                    int pass = random.nextInt(10);
                    Person first = queue.peek();
                    queue.poll();
                    if(pass == 0){
                        queue.offer(first);
                    }
                    System.out.println(queue);
                }finally {
                    lock.unlock();
                }
            }
        }
        Thread.sleep(10);

    }

    class AddToQueue implements Runnable{

        AddToQueue(){
            addTime = 1+random.nextInt(6);
        }

        @Override
        public void run() {
            while(curTime<FINAL_TIME){
                if(curTime>=addTime){
                    lock.lock();
                    int isPreg = random.nextInt(2);
                    int isBroke = random.nextInt(2);
                    queue.add(new Person(random.nextInt(100),isPreg==1,isBroke==1));
                    addTime = curTime+1+random.nextInt(6);
                    System.out.println("Adding Person to queue");
                    lock.unlock();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Person implements Comparable<Person>{
        static int IS_PREGNANT = 1 << 5;
        static int IS_BROKEN = 1 << 4;
        int age;
        int priority;
        boolean isProceed;

        public Person(int age,boolean isPregnant,boolean isBroken){
            this.age = age;
            priority = 0;
            if(isPregnant){
                priority = priority|IS_PREGNANT;
            }
            if(isBroken){
                priority = priority|IS_BROKEN;
            }
            isProceed = false;
        }

        @Override
        public int compareTo(Person o) {
            if(isProceed){
                return 0;
            }
            if(priority!=o.priority){
                return priority-o.priority;
            }
            if(age > 60){
                return age-o.age;
            }
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && priority == person.priority && isProceed == person.isProceed;
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, priority, isProceed);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", priority=" + priority +
                    ", isProceed=" + isProceed +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        SimpleQueue simpleQueue = new SimpleQueue();
        es.submit(simpleQueue);
        es.submit(simpleQueue.addToQueue);
    }
}
