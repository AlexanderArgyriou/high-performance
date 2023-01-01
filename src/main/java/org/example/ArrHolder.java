package org.example;

import java.security.SecureRandom;
import java.util.stream.IntStream;

public class ArrHolder {
    public static final int[] arr =
            IntStream.range( 0, 30_000_000 ).map(i -> new SecureRandom().nextInt( 200_000 ) ).toArray();
}
