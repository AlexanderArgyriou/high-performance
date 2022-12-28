package org.example;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

import static org.example.Consts.SIZE;

public class Main {
    public static void main(String[] args) {

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
            long time = 0;
            for ( int i = 0; i < 5; ++i ) {
                int[] sample = Arrays.copyOfRange( arr1, 0, arr1.length );
                SharedResult sharedResult = new SharedResult();
                start = System.currentTimeMillis();
                new ForkJoinPool( numOfThreads ).invoke( new BubbleTask( sample, sample.length / 30, sharedResult ) );
                end = System.currentTimeMillis();
                time += end - start;
            }
            System.out.println( "parallel time with active threads : " + numOfThreads + ", ran 5 times with avg_time = " +  time / 5 + "ms" );
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
