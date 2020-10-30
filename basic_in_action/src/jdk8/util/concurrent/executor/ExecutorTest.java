package jdk8.util.concurrent.executor;

import java.util.concurrent.*;

/**
 * {@link java.util.concurrent.Executor}
 * Executor是一个任务的框架
 */
public class ExecutorTest {
    private final static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("asynchronous task!");
//            }
//        });
        Future future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "how are you!";
            }
        });
        System.out.println("lll");
        System.out.println(future.get());
        System.out.println("lll");
        executorService.shutdown();
    }
}
