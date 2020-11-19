package com.leo.object;

public class Person {
    public String name;

    private void say(){
        System.out.println("hello 原生！");
    }

    public final void look (){
        System.out.println("look 原生！");
    }

    public void fuck (){
        System.out.println("fuck 原生！");
    }
}
