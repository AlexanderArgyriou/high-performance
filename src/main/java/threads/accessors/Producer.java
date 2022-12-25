package threads.accessors;

import threads.buffers.Buffer;
import threads.soures.DataSource;


public class Producer implements Runnable {
    private final Buffer buffer;
    private final DataSource dataSource;

    public Producer(Buffer buffer, DataSource dataSource) {
        this.buffer = buffer;
        this.dataSource = dataSource;
    }

    public void produce() {
        for (int i = 0; i < 100; ++i) {
            buffer.addToBuffer(dataSource.getData()[i]);
            System.out.println(Thread.currentThread() + " , producer added to shared buffer: " + dataSource.getData()[i]);
        }
    }

    @Override
    public void run() {
        produce();
    }
}
