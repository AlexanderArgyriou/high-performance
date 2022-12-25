package threads.buffers;

public class SharedBuffer implements Buffer {
    private int x = -1;
    private boolean occupied = false;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public synchronized void addToBuffer(int x) throws InterruptedException {
        while (occupied) {
            System.out.println("Producer tries to write but buffer is full");
            wait();
        }
        setX(x);
        System.out.println(Thread.currentThread() + " , producer added to shared buffer: " + x);
        occupied = true;

        notifyAll();
    }

    @Override
    public synchronized int getFromBuffer() throws InterruptedException {
        while (!occupied) {
            System.out.println("Consumer tries to retrieve from buffer but buffer is empty");
            wait();
        }
        System.out.println(Thread.currentThread() + " , consumer consumed from buffer: " + x);
        occupied = false;

        notifyAll();

        return getX();
    }
}
