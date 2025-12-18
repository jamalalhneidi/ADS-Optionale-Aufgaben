package zettel_6;

public class Sorter {

    public static long insertionSort(int[] a) {
        ComparisonCounter comparison = new ComparisonCounter();
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i;
            while (j > 0 && comparison.count(a[j - 1] > key)) {
                a[j] = a[j - 1];
                --j;
            }
            a[j] = key;
        }
        assertSorted(a, "Insertion Sort");
        return comparison.getCount();
    }

    public static long quicksortMiddleElement(int[] a) {
        ComparisonCounter comparison = new ComparisonCounter();
        quicksort(a, 0, a.length - 1, comparison, PivotStrategy.MIDDLE);
        assertSorted(a, "Quicksort (Middle Pivot)");
        return comparison.getCount();
    }

    public static long quicksortRandom3Element(int[] a) {
        ComparisonCounter comparison = new ComparisonCounter();
        quicksort(a, 0, a.length - 1, comparison, PivotStrategy.RANDOM3);
        assertSorted(a, "Quicksort (Random 3 Pivot)");
        return comparison.getCount();
    }

    // [l,r]
    private static void quicksort(int a[], int l, int r, ComparisonCounter comparison, int pivotStrategy) {
        if (l >= r)
            return;
        int pivot = PivotStrategy.choosePivot(a, l, r, pivotStrategy);
        int key = a[pivot];
        int ll = l, rr = r;
        while (ll < rr) {
            int minIndex = ll, maxIndex = rr;
            while (comparison.count(a[minIndex] < key)) {
                ++minIndex;
            }
            while (comparison.count(a[maxIndex] > key)) {
                --maxIndex;
            }
            if (minIndex == maxIndex)
                break;
            swap(a, minIndex, maxIndex);
            if (minIndex == pivot) {
                pivot = maxIndex;
                ll = minIndex + 1;
            } else if (maxIndex == pivot) {
                pivot = minIndex;
                rr = maxIndex - 1;
            } else {
                ll = minIndex + 1;
                rr = maxIndex - 1;
            }
        }
        quicksort(a, l, pivot - 1, comparison, pivotStrategy);
        quicksort(a, pivot + 1, r, comparison, pivotStrategy);
    }

    private static void swap(int a[], int l, int r) {
        int tmp = a[l];
        a[l] = a[r];
        a[r] = tmp;
    }

    private static class PivotStrategy {
        public static final int MIDDLE = 0;
        public static final int RANDOM3 = 1;

        public static int choosePivot(int[] a, int l, int r, int strategy) {
            switch (strategy) {
                case MIDDLE:
                    return l + (r - l) / 2;
                case RANDOM3:
                    int i1 = l + (int) (Math.random() * (r - l + 1));
                    int i2 = l + (int) (Math.random() * (r - l + 1));
                    int i3 = l + (int) (Math.random() * (r - l + 1));
                    int v1 = a[i1];
                    int v2 = a[i2];
                    int v3 = a[i3];
                    if ((v1 <= v2 && v2 <= v3) || (v3 <= v2 && v2 <= v1)) {
                        return i2;
                    } else if ((v2 <= v1 && v1 <= v3) || (v3 <= v1 && v1 <= v2)) {
                        return i1;
                    } else {
                        return i3;
                    }
                default:
                    throw new IllegalArgumentException("Unknown pivot strategy");
            }
        }

    }

    private static void assertSorted(int[] a, String sortName) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                throw new AssertionError(sortName + " did not sort the array correctly.");
            }
        }
    }

    private static class ComparisonCounter {
        private long count = 0;

        public boolean count(boolean b) {
            ++count;
            return b;
        }

        public long getCount() {
            return count;
        }
    }

}