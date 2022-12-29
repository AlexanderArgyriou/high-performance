package org.example;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static org.example.Consts.CHUNKS;
import static org.example.Consts.MAX_THREADS;
import static org.example.Consts.SAMPLE_ARR_SIZE;
import static org.example.Consts.TESTS;

public class CodeSamples {
    public static void sequential(int[] arr) {
        long start = System.currentTimeMillis();
        BubbleSort.sort( arr );
        long end = System.currentTimeMillis();
        System.out.println( "sequential time : " + ( end - start ) + "ms" );
    }

    public static void parallelWithStreams(int[] arr) {
        long end;
        long start;
        long time = 0;

        for ( int i = 0; i < TESTS; ++i) {
            int[] sample = Arrays.copyOfRange( arr, 0, arr.length );

            List<int[]> tasks = new ArrayList<>();
            SharedContext sharedContext = new SharedContext();

            for ( int k = 0; k < sample.length; k += sample.length / CHUNKS ) {
                tasks.add( Arrays.copyOfRange( sample, k, k + sample.length / CHUNKS ) );
            }

            start = System.currentTimeMillis();
            tasks
                    .parallelStream()
                    .forEach( e -> {
                        // System.out.println( Thread.currentThread() );
                        BubbleSort.sort( e );
                        sharedContext.addToDone( e );
                    } );
            int[] result = sharedContext.mergeAllSortedArraysAndGetResult();
            end = System.currentTimeMillis();
            time += end - start;
        }
        System.out.println( "parallel time with parallel stream & threads : " + (ForkJoinPool.commonPool().getParallelism() + 1) + ", ran " + TESTS + " times with avg_time = " + time / TESTS + "ms" );
    }

    public static int[] createRandomArr() {
        int[] arr1 = new int[SAMPLE_ARR_SIZE];

        SecureRandom secureRandom = new SecureRandom();
        long start;
        long end;

        for ( int i = 0; i < SAMPLE_ARR_SIZE; ++i ) {
            int random = secureRandom.nextInt( SAMPLE_ARR_SIZE );
            arr1[i] = random;
        }
        return arr1;
    }

    public static void parallelHandsOn(int[] arr1) throws InterruptedException {
        long start;
        long end;

        for ( int numOfThreads = 2; numOfThreads <= MAX_THREADS; numOfThreads *= 2 ) {
            long time = 0L;
            for ( int i = 0; i < TESTS; ++i ) {
                ExecutorService executorService = Executors.newFixedThreadPool( numOfThreads );
                int[] sample = Arrays.copyOfRange( arr1, 0, arr1.length );
                SharedContext sharedContext = new SharedContext();
                for ( int k = 0; k < sample.length; k += sample.length / CHUNKS ) {
                    sharedContext.addToJobPool( Arrays.copyOfRange( sample, k, k + sample.length / CHUNKS ) );
                }
                start = System.currentTimeMillis();
                for ( int t = 0; t < numOfThreads; t++ ) {
                    executorService.execute( new BubbleTask( sharedContext ) );
                }
                executorService.shutdown();
                executorService.awaitTermination( 1, TimeUnit.HOURS );

                int[] result = sharedContext.mergeAllSortedArraysAndGetResult();
                end = System.currentTimeMillis();
                time += end - start;
                // System.out.println( "is array sorted ? " + ArrayUtils.isSorted( result ) + " , length : " + result.length );
            }
            System.out.println( "parallel time with active threads : " + numOfThreads + ", ran " + TESTS + " times with avg_time = " + time / TESTS + "ms" );
        }
    }
}
