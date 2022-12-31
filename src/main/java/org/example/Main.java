package org.example;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        int[] arr = IntStream.range( 0, 30_000_000 ).map( i -> secureRandom.nextInt( 200_000 ) ).toArray();
        int tests = 10;

        System.out.println( "CPU Cores : " + Runtime.getRuntime().availableProcessors() + "\n" );
        for ( int threads = 1; threads <= 64; threads *= 2 ) {
            long time = 0;
            for ( int i = 0; i < tests; ++i ) {
                int[] sample = Arrays.copyOfRange( arr, 0, arr.length );
                long start = System.currentTimeMillis();
                new ForkJoinPool(threads).invoke( new ParallelQuickSort( 0, arr.length - 1, sample ) );
                long end = System.currentTimeMillis();
                time += end - start;
                //System.out.println( isSorted( sample ) );
            }
            System.out.println( tests + " tests ran with " + threads +
                    " thread(s), on an array of size : " + arr.length + ". Sorting process took on avg for each test : " + time / tests );
        }
    }

    private static boolean isSorted(int[] arr) {
        boolean isSorted = true;
        for ( int i = 0; i < arr.length - 1; ++i ) {
            if ( arr[i] > arr[i + 1] ) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }
}