package threads.buffers;

public interface Buffer {
    default void addToBuffer(int x) {
        System.out.println("no impl");
    }

    default int getFromBuffer() {
        System.out.println("no impl");
        return -1;
    }
}
