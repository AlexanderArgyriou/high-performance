package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class SharedContext {
    private final ArrayBlockingQueue<int[]> jobPool = new ArrayBlockingQueue<>( 500 );
    private List<int[]> doneJobs = new ArrayList<>();

    public ArrayBlockingQueue<int[]> getJobPool() {
        return jobPool;
    }

    public List<int[]> getDoneJobs() {
        return doneJobs;
    }

    public void setDoneJobs(List<int[]> doneJobs) {
        this.doneJobs = doneJobs;
    }

    public synchronized void addToDone(int[] arr) {
        doneJobs.add( arr );
    }

    public void addToJobPool(int[] part) {
        jobPool.add( part );
    }

    public synchronized int[] mergeAllSortedArraysAndGetResult() {
        if ( doneJobs.size() > 1 ) {
            int[] result = mergeTwoSortedArrays( doneJobs.get( 0 ), doneJobs.get( 1 ) );
            for ( int i = 2; i < doneJobs.size(); i++ ) {
                result = mergeTwoSortedArrays( result, doneJobs.get( i ) );
            }
            return result;
        }
        return doneJobs.get( 0 );
    }

    public int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {
        int i = 0, j = 0, k = 0;
        int[] result = new int[arr1.length + arr2.length];

        while ( i < arr1.length && j < arr2.length ) {
            if ( arr1[i] < arr2[j] ) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while ( i < arr1.length ) {
            result[k++] = arr1[i++];
        }
        while ( j < arr2.length ) {
            result[k++] = arr2[j++];
        }

        return result;
    }
}
