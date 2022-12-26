package threads.buffers;

import java.util.concurrent.ArrayBlockingQueue;

public class SharedBuffer implements Buffer {
    private final ArrayBlockingQueue<Integer> x = new ArrayBlockingQueue<>(1);

    public int getX() throws InterruptedException {
        return x.take();
    }

    public void setX(int x) throws InterruptedException {
        this.x.put(x);
    }

    @Override
    public void addToBuffer(int x) throws InterruptedException {
        setX(x);
        System.out.println(Thread.currentThread() + " , producer added to shared buffer: " + x);
    }

    @Override
    public int getFromBuffer() throws InterruptedException {
        int result = getX();
        System.out.println(Thread.currentThread() + " , consumer consumed from buffer: " + result);
        return result;
    }
}
