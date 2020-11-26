package com.leo.jmm.visibility.singleton;

public class ExampleTranditional {
    private ExampleTranditional(){} // 防止使用构造方法
    private static ExampleTranditional example;

    public static ExampleTranditional getInstance(){
        if (example==null){
            example = new ExampleTranditional();
        }
        return example;
    }
}
