package org.example;

import java.util.NoSuchElementException;

public class BubbleTask implements Runnable {
    private final SharedContext sharedContext;

    public BubbleTask(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
    }

    @Override
    public void run() {
        boolean qEmpty = false;
        while ( !qEmpty ) {
            try {
                process(sharedContext.getJobPool().remove());
            } catch ( NoSuchElementException e ) {
                qEmpty = true;
            }
        }
    }

    private void process(int[] arr) {
        //System.out.println(Thread.currentThread().getName() + " assigned to sort: " + arr.length + " elements");
        BubbleSort.sort( arr );
        sharedContext.addToDone( arr );
    }
}
