package threads.buffers;

public class SharedBuffer implements Buffer {
    private int x = -1;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void addToBuffer(int x) {
        setX(x);
    }

    @Override
    public int getFromBuffer() {
        return getX();
    }
}
