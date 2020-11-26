package com.leo.jmm.visibility.singleton;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class HappensBeforeExample {
    public static void main(String[] args) {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
    }
}
