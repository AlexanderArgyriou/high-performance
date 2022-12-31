package org.example;

import org.openjdk.jmh.annotations.Scope;
import java.security.SecureRandom;
import java.util.stream.IntStream;

@org.openjdk.jmh.annotations.State(Scope.Benchmark)
public class State {
    private int[] arr;
    SecureRandom secureRandom = new SecureRandom();

    public State() {
        arr = IntStream.range( 0, 3_000_000 ).map( i -> secureRandom.nextInt( 200_000 ) ).toArray();;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public SecureRandom getSecureRandom() {
        return secureRandom;
    }

    public void setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
    }
}
