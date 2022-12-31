package org.example;

public class QuickSort {
    public static void sort(int[] arr, int left, int right) {
        if ( right - left <= 0 ) {
            return;
        }
        long pivot = arr[right];
        int partition = partition( arr, left, right, pivot );
        sort( arr, left, partition - 1 );
        sort( arr, partition + 1, right );
    }

    private static int partition(int[] arr, int left, int right, long pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;

        while ( true ) {
            while ( arr[++leftPtr] < pivot );
            while ( rightPtr > 0 && arr[--rightPtr] > pivot );

            if ( leftPtr >= rightPtr ) {
                break;
            } else {
                swap( arr, leftPtr, rightPtr );
            }
        }
        swap( arr, leftPtr, right );
        return leftPtr;
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
