package com.leo.jmm.visibility.singleton;

public class ExampleDoubleCheckLocking {
    private ExampleDoubleCheckLocking(){} // 防止使用构造方法
    private static ExampleDoubleCheckLocking example;

    public static ExampleDoubleCheckLocking getInstance(){
        if (example==null){
            synchronized (ExampleDoubleCheckLocking.class){
                if (example==null){
                    example = new ExampleDoubleCheckLocking();
                }
            }
        }
        return example;
    }
}
