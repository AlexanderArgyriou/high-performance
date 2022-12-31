package org.example;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort extends RecursiveAction {
    private final int left;
    private final int right;
    private final int[] arr;

    public ParallelQuickSort(int left, int right, int[] arr) {
        this.left = left;
        this.right = right;
        this.arr = arr;
    }

    private int partition(int[] arr, long pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;

        while ( true ) {
            while ( arr[++leftPtr] < pivot ) ;
            while ( rightPtr > 0 && arr[--rightPtr] > pivot ) ;

            if ( leftPtr >= rightPtr ) {
                break;
            } else {
                swap( arr, leftPtr, rightPtr );
            }
        }
        swap( arr, leftPtr, right );
        return leftPtr;
    }

    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    @Override
    protected void compute() {
        if ( right - left <= 0 ) {
            return;
        }
        long pivot = arr[right];
        int partition = partition( arr, pivot );
        ForkJoinTask.invokeAll( createSubTasks( partition ) );
    }

    private List<ParallelQuickSort> createSubTasks(int partition) {
        return List.of(
                new ParallelQuickSort( left, partition - 1, arr ),
                new ParallelQuickSort( partition + 1, right, arr )
        );
    }
}
