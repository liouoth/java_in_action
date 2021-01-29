package main;

        import java.util.concurrent.Flow;
        import java.util.concurrent.SubmissionPublisher;
        import java.util.concurrent.TimeUnit;

public class ReactiveTest2 {
    public static void main(String[] args) throws InterruptedException {
        //1.定义发布者，发布String
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<Integer>();
        //2.自定义Processor，用于处理中间数据,传入Integer,传出String
        MyProcessor myProcessor = new MyProcessor();
        //3.定义订阅者
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                //保存数据关系
                this.subscription = subscription;
                //请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                //响应式关键，处理的快慢
                //接收到一个数据 处理
                System.out.println("订阅者接受到数据："+item);
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
        //4.处理器与订阅者建立关系，因为数据是由处理器发送给订阅者的
        myProcessor.subscribe(subscriber);
        //5.发布者与处理器建立关系，由上游建立关系，就像1和0，一般都是1主动
        publisher.subscribe(myProcessor);
        //6.发布数据
        for (int i =0;i<1000;i++){
            System.out.println("发布数据："+i);
            publisher.submit(i);
        }
        //可以使用finally 或者 try resource确保关闭
        publisher.close();

        Thread.currentThread().join(1000);
    }
}
//Processor<T,R> extends Subscriber<T>, Publisher<R>,订阅前者，发布后者
//处理器是接受Integer，发布String，所以作为发布者，泛型是String,SubmissionPublisher<String>
//处理器需要接受订阅，订阅的是上游的数据，Integer，然后发送String
class MyProcessor extends SubmissionPublisher<String>
        implements Flow.Processor<Integer,String> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println("处理器接收到数据："+item);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //可选进行过滤
        if (item>0){
            submit(String.valueOf(item));
        }
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("处理完了！");
    }
}
