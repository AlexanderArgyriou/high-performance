package threads.accessors;

import threads.buffers.Buffer;

public class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void consume() throws InterruptedException {
        for (int i = 0; i < 100; ++i) {
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
