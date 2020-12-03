package com.leo.concurrent_tool.thread_pool_customer;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Pool {
    public static void main(String[] args) {
        //死等
        ThreadPool threadPool = new ThreadPool(2, 1, TimeUnit.SECONDS,
                (queue, task) -> {
                    //死等
//                    queue.put(task);
                    //带超时等待
//                    queue.offer(task,1500,TimeUnit.MILLISECONDS);
                    //放弃
//                    log.debug("任务被放弃！"); //一共会有7个任务被执行，5个任务长度，2个线程
                    //让调用者抛出异常
//                    throw new RuntimeException("超时啦！");
                    //让调用者自己执行任务
                    task.run();
                });

        for (int i = 0; i < 15; i++) {
            int finalI = i;
            threadPool.execute(
                    () -> {
                        System.out.println("运行： " + finalI);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        log.debug("main线程结束");
    }
}

@FunctionalInterface
        //使用lambda表达式
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T t);
}

@Slf4j
class ThreadPool {
    //线程池，管理固定数量的线程，临界资源是线程。需要一个集合类管理线程
    HashSet<Worker> workers = new HashSet<>();

    //线程池需要固定的数量，capacity
    private int capacity;

    //需要延迟时间，以及单位
    private long timeout;
    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    //一个阻塞的队列，用于管理任务
    private BlockingQueue<Runnable> taskQueue;

    public ThreadPool(int capacity, long timeout, TimeUnit timeUnit, RejectPolicy<Runnable> rejectPolicy) {
        this.capacity = capacity;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue = new BlockingQueue<>();
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if (capacity > workers.size()) {
                Worker worker = new Worker(task);
                log.debug("新增workder: {} ", worker);
                workers.add(worker);
                worker.start();
            } else {
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }


    //一个继承Thread的任务类
    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //线程运行
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    log.debug("=====正在执行====");
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                log.debug("移除worker:{}", this);
                workers.remove(this);
            }
        }
    }
}

@Slf4j
class BlockingQueue<T> {
    //队列用来平衡线程池和任务生产者之间关系
    //首先需要一个队列来存放任务
    private Deque<T> taskQueue = new ArrayDeque<>();

    //队列作为公共资源，take以及put时需要进行加锁
    ReentrantLock lock = new ReentrantLock();

    //队列需要有一定的负载
    private int capacity;

    //当take时，队列为空时，线程需要进入等待阻塞，设定一个condition 为 emptySet
    private Condition emptySet;

    //当put时，队列满时，线程需要进入等待阻塞，直到任务加入，设定一个condition为 fullSet
    private Condition fullSet;

    public BlockingQueue() {
        this.capacity = 5;
        this.emptySet = lock.newCondition();
        this.fullSet = lock.newCondition();
    }

    //put操作
    public void put(T t) {
        lock.lock();
        try {
            while (taskQueue.size() >= capacity) {
                log.debug("任务队列已经满了，进入等待！");
                fullSet.await();
            }
            log.debug("加入队列！");
            taskQueue.addLast(t);
            emptySet.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //take操作,如果任务队列为看
    public T take() {
        lock.lock();
        try {
            while (taskQueue.isEmpty()) {
                try {
                    emptySet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = taskQueue.removeFirst();
            fullSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //pool延时操作
    public T poll(long timeout, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(timeout);
        lock.lock();
        try {
            while (taskQueue.isEmpty()) {
                try {
                    //耗时为0
                    //这个时间点犯错了，这里应该是小于等于0，因为时间不可能凑得那么准，判断小于等于一定是超时了
                    if (nanos <= 0) {
                        return null;
                    }
                    //继续等待
                    nanos = emptySet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = taskQueue.removeFirst();
            fullSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //put获取延时操作
    public boolean offer(T t, long timeout, TimeUnit timeUnit) {
        lock.lock();
        long nanos = timeUnit.toNanos(timeout);
        try {
            while (taskQueue.size() >= capacity) {
                if (nanos <= 0) {
                    log.debug("已经超时，返回");
                    return false; //超时 添加失败
                }
                log.debug("任务队列已经满了，进入等待！");
                try {
                    nanos = fullSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入队列！");
            taskQueue.addLast(t);
            emptySet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    //添加拒绝策略的put
    public void tryPut(RejectPolicy rejectPolicy, T t) {
        lock.lock();
        try {
            if (taskQueue.size() >= capacity) {
                rejectPolicy.reject(this, t);
            } else {
                //有空闲
                log.debug("加入任务队列，{}", t);
                taskQueue.addLast(t);
                emptySet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}