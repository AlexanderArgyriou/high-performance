package threads.accessors;

import threads.buffers.Buffer;

import static threads.ConstantCount.COUNT;

public class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void consume() {
        for (int i = 0; i < COUNT; ++i) {
            System.out.println(Thread.currentThread() + " , consumer consumed from buffer: " + buffer.getFromBuffer());
        }
    }

    @Override
    public void run() {
        consume();
    }
}
