package com.leo.concurrent_tool.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Main {
    public static void main(String[] args) {
        B b = new B();
    }
}
abstract class A{
    private int a;
    protected final int getA(){
        return a;
    }
}
class B extends AbstractQueuedSynchronizer{
    protected void main() {
        this.getState();
    }
}
