package threads.buffers;

public interface Buffer {
    default void addToBuffer(int x) throws InterruptedException {
        System.out.println("no impl");
    }

    default int getFromBuffer() throws InterruptedException {
        System.out.println("no impl");
        return -1;
    }
}
