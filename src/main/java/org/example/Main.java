package org.example;

import org.example.shared.Arr;
import org.example.threads.FillerA;
import org.example.threads.FillerB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Arr arr = new Arr();
        executorService.execute(new FillerA(arr));
        executorService.execute(new FillerB(arr));

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        for (int i = 0; i < Arr.CAPACITY; ++i) {
            if (i % 20 == 0) {
                System.out.println();
            }
            System.out.print(arr.getArr()[i] + " ");
        }
    }
}