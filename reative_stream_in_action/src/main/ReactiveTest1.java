package main;

import javax.swing.*;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveTest1 {
    public static void main(String[] args) throws InterruptedException {
        //定义发布者，发布类型为Integer
        //SubmissionPublisher实现了Publisher接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<Integer>();

        //定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {
            private Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                //保存数据关系
                this.subscription = subscription;
                //请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                //响应式关键，处理的快慢
                //接收到一个数据 处理
                System.out.println("接受到数据："+item);
                //处理完调用request再请求一个数据
                this.subscription.request(1);
                //在这里可以添加条件，比如接收数据够了，那么就调用cancel
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("处理完了！！");
            }
        };
        publisher.subscribe(subscriber);

        //发布者与订阅者建立订阅关系
        Integer data = 1;
        publisher.submit(data);
        publisher.submit(data+1);
        publisher.submit(data+2);

        //可以使用finally 或者 try resource确保关闭
        publisher.close();

        Thread.currentThread().join(1000);
    }
}
