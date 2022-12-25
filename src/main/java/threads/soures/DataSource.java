package threads.soures;

public class DataSource {
    private final int[] data = new int[100];

    public DataSource() {
        for (int i = 0; i < 100; ++i) {
            data[i] = i;
        }
    }

    public int[] getData() {
        return data;
    }
}
