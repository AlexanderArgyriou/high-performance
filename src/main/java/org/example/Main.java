package org.example;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.example.Consts.SIZE;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int[] arr1 = new int[SIZE];
        int[] arr2 = new int[SIZE];

        SecureRandom secureRandom = new SecureRandom();
        long start;
        long end;

        for ( int i = 0; i < SIZE; ++i ) {
            int random = secureRandom.nextInt( SIZE );
            arr1[i] = random;
            arr2[i] = random;
        }

        int numOfThreads = 2;
        for ( int j = 0; j < 6; ++j ) {
            long time = 0L;
            for ( int i = 0; i < 20; ++i ) {
                ExecutorService executorService = Executors.newFixedThreadPool( numOfThreads );
                int[] sample = Arrays.copyOfRange( arr1, 0, arr1.length );
                SharedContext sharedContext = new SharedContext();
                for ( int k = 0; k < sample.length; k += sample.length / 500 ) {
                    sharedContext.addToJobPool( Arrays.copyOfRange( sample, k, k + sample.length / 500 ) );
                }
                start = System.currentTimeMillis();
                for ( int t = 0; t < numOfThreads; t++ ) {
                    executorService.submit( new BubbleTask( sharedContext ) );
                }
                executorService.shutdown();
                executorService.awaitTermination( 1, TimeUnit.HOURS );

                sharedContext.mergeAllSortedArraysAndGetResult();
                end = System.currentTimeMillis();
                time += end - start;

                //Arrays.stream( sharedContext.mergeAllSortedArraysAndGetResult()).forEach( e -> System.out.print( e + " " ) );
                //System.out.println();
            }
            System.out.println( "parallel time with active threads : " + numOfThreads + ", ran 5 times with avg_time = " + time / 20 + "ms" );
            numOfThreads *= 2;
        }
//
//        Arrays.stream( sharedResult.getArr() ).forEach( e -> System.out.print( e + " " ) );
//        System.out.println();

        //Arrays.stream( arr ).forEach( e -> System.out.print( e + " " ) );

//        start = Instant.now();
//        BubbleSort.sort( arr2 );
//        end = Instant.now();
//        System.out.println( "sequential time : " + Duration.between( start, end ).toMillis() + "ms" );

        //Arrays.stream( arr ).forEach( e -> System.out.print( e + " " ) );
    }
}
