package org.example.shared;

import java.security.SecureRandom;

public class Arr {
    public static final int CAPACITY = 10;
    private int index = 0;
    private final int[] arr = new int[CAPACITY];

    public synchronized void addToArr() throws InterruptedException {
        arr[index] = index;
        System.out.println(Thread.currentThread() + " added " + index);
        index++;
        Thread.sleep(new SecureRandom().nextInt(500));
    }

    public int getIndex() {
        return index;
    }

    public int[] getArr() {
        return arr;
    }
}
