package threads.accessors;

import threads.buffers.Buffer;

import static threads.ConstantCount.COUNT;

public class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void consume() throws InterruptedException {
        for (int i = 0; i < COUNT; ++i) {
            buffer.getFromBuffer();
        }
    }

    @Override
    public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
