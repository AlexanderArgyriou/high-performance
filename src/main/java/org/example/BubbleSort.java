package org.example;

public class BubbleSort {
    public static void sort(int[] arr) {
        if ( arr.length <= 1 ) {
            return;
        }
        for ( int i = 0; i < arr.length - 1; ++i ) {
            for ( int j = i + 1; j < arr.length; ++j) {
                if ( arr[i] > arr[j] ) {
                    swap( i, j, arr );
                }
            }
        }
    }

    private static void swap(int i, int j, int arr[]) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
