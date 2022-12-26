package threads.soures;

import static threads.ConstantCount.COUNT;

public class DataSource {
    private final int[] data = new int[COUNT];

    public DataSource() {
        for (int i = 0; i < COUNT; ++i) {
            data[i] = i;
        }
    }

    public int[] getData() {
        return data;
    }
}
