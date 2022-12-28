package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class BubbleTask extends RecursiveAction {
    private final int[] arr;
    private final int chunkThreshHold;
    private final SharedResult sharedResult;

    public BubbleTask(int[] arr, int chunkThreshHold, SharedResult sharedResult) {
        this.arr = arr;
        this.chunkThreshHold = chunkThreshHold;
        this.sharedResult = sharedResult;
    }

    @Override
    protected void compute() {
        if ( arr.length / 2 >= chunkThreshHold ) {
            ForkJoinTask.invokeAll( createSubTasks() );
        } else {
            process();
        }
    }

    private void process() {
        //System.out.println(Thread.currentThread().getName() + " assigned to sort: " + arr.length + " elements");
        BubbleSort.sort( arr );
        sharedResult.mergeASortedArr( arr );
    }

    private List<BubbleTask> createSubTasks() {
        BubbleTask bubbleTask1 = new BubbleTask( Arrays.copyOfRange( arr, 0, arr.length / 2 ), chunkThreshHold, sharedResult );
        BubbleTask bubbleTask2 = new BubbleTask( Arrays.copyOfRange( arr, arr.length / 2, arr.length ), chunkThreshHold, sharedResult );

        return List.of( bubbleTask1, bubbleTask2 );
    }
}
