package com.leo.jmm.visibility.singleton;

public class ExampleDCLSolution {
    private ExampleDCLSolution(){} // 防止使用构造方法
    private static volatile ExampleDCLSolution example;

    public static ExampleDCLSolution getInstance(){
        if (example==null){
            synchronized (ExampleDCLSolution.class){
                if (example==null){
                    example = new ExampleDCLSolution();
                }
            }
        }
        return example;
    }
}
