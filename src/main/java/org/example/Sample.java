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
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

//4 threads
//Result "org.example.Sample.run":
//        145.647 ±(99.9%) 26.328 ms/op [Average]
//        (min, avg, max) = (120.948, 145.647, 181.042), stdev = 17.414
//        CI (99.9%): [119.319, 171.974] (assumes normal distribution)
//8 threads
//Result "org.example.Sample.run":
//        110.237 ±(99.9%) 4.193 ms/op [Average]
//        (min, avg, max) = (107.040, 110.237, 115.932), stdev = 2.774
//        CI (99.9%): [106.044, 114.431] (assumes normal distribution)

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Sample {

    @State(Scope.Benchmark)
    public static class Data {
        public int[] arr;
        SecureRandom secureRandom = new SecureRandom();

        @Setup(Level.Invocation)
        public void setUp() {
            arr = IntStream.range( 0, 3_000_000 ).map( i -> secureRandom.nextInt( 200_000 ) ).toArray();
        }
    }

    @Benchmark
    public void run(Data data) {
        new ForkJoinPool( 8 ).invoke( new ParallelQuickSort( 0, data.arr.length - 1, data.arr ) );
    }
}
