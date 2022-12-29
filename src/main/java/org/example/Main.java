package org.example;

import static org.example.CodeSamples.createRandomArr;
import static org.example.CodeSamples.parallelHandsOn;
import static org.example.CodeSamples.parallelWithStreams;
import static org.example.Consts.CHUNKS;
import static org.example.Consts.SAMPLE_ARR_SIZE;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] arr = createRandomArr();

        System.out.println( "Starting to test a " + Runtime.getRuntime().availableProcessors() + " core(s) machine" );
        System.out.println( "Array sample size : " + SAMPLE_ARR_SIZE );
        System.out.println( "Chunks for parallel bubble are : " + CHUNKS );
        System.out.println( "Size per chunk : " + SAMPLE_ARR_SIZE / CHUNKS + "\n" );

        System.out.println("=== parallel hands on section start ===");
        parallelHandsOn( arr );
        System.out.println("=== parallel hands on section end ===");

        System.out.println();

        System.out.println( "=== parallel with streams section start ===" );
        parallelWithStreams( arr );
        System.out.println( "=== parallel with streams section end ===" );

        // sequential( arr );
    }
}
