package zettel_7;

public class ComparisonCounter {
    private long count = 0;
    private boolean isLocked = false; // used to prevent further counting after sorting is done
                                      // e.g. for checking sortedness

    public <T> T count(T b) {
        if (!isLocked)
            ++count;
        return b;
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
    }

    public long getCount() {
        return count;
    }
}
