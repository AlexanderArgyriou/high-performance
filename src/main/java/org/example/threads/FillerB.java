package org.example.threads;

import org.example.shared.Arr;

import java.util.Random;

public class FillerB implements Runnable {
    private final Arr arr;

    private final int workChunk = Arr.CAPACITY / 2;

    public FillerB(Arr arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < workChunk; ++i) {
            try {
                arr.addToArr();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
