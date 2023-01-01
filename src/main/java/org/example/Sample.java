package org.example;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 2, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Sample {

    @State(Scope.Benchmark)
    public static class Data {
        public int[] arr;

        @Setup(Level.Invocation)
        public void setUp() {
            arr = Arrays.copyOfRange( ArrHolder.arr, 0, ArrHolder.arr.length );
        }
    }

    @Benchmark
    public void run1Thread(Data data) {
        new ForkJoinPool( 1 ).invoke( new ParallelQuickSort( 0, data.arr.length - 1, data.arr ) );
    }

    @Benchmark
    public void run2Threads(Data data) {
        new ForkJoinPool( 2 ).invoke( new ParallelQuickSort( 0, data.arr.length - 1, data.arr ) );
    }

    @Benchmark
    public void run4Threads(Data data) {
        ForkJoinPool.commonPool().invoke( new ParallelQuickSort( 0, data.arr.length - 1, data.arr ) );
    }

    @Benchmark
    public void run8Threads(Data data) {
        new ForkJoinPool( 8 ).invoke( new ParallelQuickSort( 0, data.arr.length - 1, data.arr ) );
    }

    @Benchmark
    public void run16Threads(Data data) {
        new ForkJoinPool( 16 ).invoke( new ParallelQuickSort( 0, data.arr.length - 1, data.arr ) );
    }

    @Benchmark
    public void run32Threads(Data data) {
        new ForkJoinPool( 32 ).invoke( new ParallelQuickSort( 0, data.arr.length - 1, data.arr ) );
    }

    @Benchmark
    public void run64Threads(Data data) {
        new ForkJoinPool( 64 ).invoke( new ParallelQuickSort( 0, data.arr.length - 1, data.arr ) );
    }
}
