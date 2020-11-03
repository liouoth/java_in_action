package com.leo.thread.线程;

public class ThreadFunc {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        //Thread 对象方法
        thread.start();//让线程进行runnable状态，可运行状态，只有cpu时间片分配到才能运行，不可调用多次，否则会排除非法线程状态异常。
        /**调用线程的run方法，直接使用属于方法调用。如果runnable对象不为空，则会调用runnabel对象的run方法
         * 为空，则调用Thread对象的run方法。和start有本质区别，run方法只是调用方法，并未涉及线程操作。
         */
        thread.run();
        thread.join();//用于线程通信时，等待另一个线程结束
        thread.join(1000);//设置超时时间
        thread.setName("");thread.getName();//线程名称
        thread.getId();//线程唯一id
        thread.getPriority();thread.setPriority(10);//线程的优先级，1-10的整数，越大优先级越高，但是java中设置此项说了不算。
        /**
         * 返回现成的状态，返回一个State内部enum对象
         * 6种状态：NEW/RUNNABLE/BLOCKED/WAITING/TIMED_WAITING/TERMINATED
         */
        thread.getState();
        thread.isInterrupted(); //判断线程是否被打断，不会清除打断标记
        thread.isAlive();//判断线程是否存活
        /**打断线程。如果线程处于sleep、wait、join会导致排除InterruptedException，并清除标记
         * 如果打断正在运行的线程则会打断标记，park的线程也会被打断，并设置标记
         */
        thread.interrupt();
        //static
        Thread.interrupted();//判断当前线程是否被打断（可能会被其他线程打断，会做标记，会清除打断标记）
        Thread.currentThread();//获取当前正在执行的线程
        Thread.sleep(10); //让当前线程睡眠一段时间，让出cpu的时间片给其他线程
        Thread.yield();//提示线程调度器让出当前线程对CPU时间片的使用，和sleep相比，没有时间的设置。
    }
}
