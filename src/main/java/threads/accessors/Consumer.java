package threads.accessors;

import threads.buffers.Buffer;

public class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void consume() {
        for (int i = 0; i < 100; ++i) {
            System.out.println(Thread.currentThread() + " , consumer consumed from buffer: " + buffer.getFromBuffer());
        }
    }

    @Override
    public void run() {
        consume();
    }
}
