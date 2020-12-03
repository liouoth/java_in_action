package com.leo.concurrent_tool.thread_joinfork;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.ls.LSOutput;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

//创建一个任务对象
public class JoinForkTest {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        System.out.println(forkJoinPool.invoke(new AddTask2(1,5)));
    }
}

//有返回值 RecursiveTask
//无返回值 RecursiveAction
//任务拆分
@Slf4j
class AddTask1 extends RecursiveTask<Integer> {
    private int n;

    public AddTask1(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        // 如果 n 已经为 1，可以求得结果了
        if (n == 1) {
            log.debug("join() {}", n);
            return n;
        }

        // 将任务进行拆分(fork)
        AddTask1 t1 = new AddTask1(n - 1);
        t1.fork();
        log.debug("fork() {} + {}", n, t1);

        // 合并(join)结果
        int result = n + t1.join();
        log.debug("join() {} + {} = {}", n, t1, result);
        return result;
    }

    @Override
    public String toString() {
        return "" + n + '}';
    }
}

@Slf4j
class AddTask2 extends RecursiveTask<Integer> {
    private int start, end;

    public AddTask2(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int mid = start + ((end - start) >> 1);

        if (start>=end) {
            return mid;
        }

        // 将任务进行拆分(fork)
        AddTask2 t2 = new AddTask2(start, mid);
        t2.fork();
        log.debug("fork() {} + {}", start, mid);

        AddTask2 t3 = new AddTask2(mid+1, end);
        t3.fork();
        log.debug("fork() {} + {}", mid+1, end);

        // 合并(join)结果
        int result = t2.join()+t3.join();
        log.debug("join() {} + {} = {}", start, end, result);
        return result;
    }

    @Override
    public String toString() {
        return "" + start +"-"+end + '}';
    }
}
