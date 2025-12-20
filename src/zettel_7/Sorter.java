package zettel_7;

import zettel_7.Keys.Key;
import zettel_7.utils.Helper;

public class Sorter {

    public static long heapsort(Key[] a) {
        ComparisonCounter comparison = initComparisonCounter(a);
        buildHeap(a);
        int n = a.length;
        while (n > 1) {
            Helper.swap(a, 0, n - 1);
            --n;
            sink(a, 0, n);
        }
        assertSorted(a, "Heapsort");
        return comparison.getCount();
    }

    public static long insertionSort(Key[] a) {
        ComparisonCounter comparison = initComparisonCounter(a);
        for (int i = 1; i < a.length; i++) {
            Key key = a[i];
            int j = i;
            while (j > 0 && a[j - 1].compareTo(key) > 0) {
                a[j] = a[j - 1];
                --j;
            }
            a[j] = key;
        }
        assertSorted(a, "Insertion Sort");
        return comparison.getCount();
    }

    public static long quicksortMiddleElement(Key[] a) {
        ComparisonCounter comparison = initComparisonCounter(a);
        quicksort(a, 0, a.length - 1, PivotStrategy.MIDDLE);
        assertSorted(a, "Quicksort (Middle Pivot)");
        return comparison.getCount();
    }

    public static long quicksortRandom3Element(Key[] a) {
        ComparisonCounter comparison = initComparisonCounter(a);
        quicksort(a, 0, a.length - 1, PivotStrategy.RANDOM3);
        assertSorted(a, "Quicksort (Random 3 Pivot)");
        return comparison.getCount();
    }

    private static ComparisonCounter initComparisonCounter(Key[] a) {
        ComparisonCounter comparison = new ComparisonCounter();
        for (Key el : a) {
            el.setComparisonCounter(comparison);
        }
        return comparison;
    }

    private static void buildHeap(Key[] a) {
        for (int i = a.length / 2; i >= 0; i--) {
            sink(a, i, a.length); // aka heapify
        }
    }

    // aka heapify
    private static void sink(Key[] a, int i, int n) {
        while (i < n / 2) {
            int j = Helper.leftChild(i);
            if (j < n - 1 && a[j].compareTo(a[j + 1]) < 0)
                j = j + 1;
            if (a[i].compareTo(a[j]) < 0)
                Helper.swap(a, i, j);
            i = j;
        }
    }

    // [l,r]
    private static void quicksort(Key[] a, int l, int r,
            int pivotStrategy) {
        if (l >= r)
            return;
        int pivot = PivotStrategy.choosePivot(a, l, r, pivotStrategy);
        Key key = a[pivot];
        int ll = l, rr = r;
        while (ll < rr) {
            int minIndex = ll, maxIndex = rr;
            while (a[minIndex].compareTo(key) < 0) {
                ++minIndex;
            }
            while (a[maxIndex].compareTo(key) > 0) {
                --maxIndex;
            }
            if (minIndex == maxIndex)
                break;
            Helper.swap(a, minIndex, maxIndex);
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
        quicksort(a, l, pivot - 1, pivotStrategy);
        quicksort(a, pivot + 1, r, pivotStrategy);
    }

    private static class PivotStrategy {
        public static final int MIDDLE = 0;
        public static final int RANDOM3 = 1;

        public static int choosePivot(Key[] a, int l, int r, int strategy) {
            switch (strategy) {
                case MIDDLE:
                    return l + (r - l) / 2;
                case RANDOM3:
                    int i1 = l + (int) (Math.random() * (r - l + 1));
                    int i2 = l + (int) (Math.random() * (r - l + 1));
                    int i3 = l + (int) (Math.random() * (r - l + 1));
                    Key v1 = a[i1];
                    Key v2 = a[i2];
                    Key v3 = a[i3];
                    if ((v1.compareTo(v2) <= 0 && v2.compareTo(v3) <= 0)
                            || (v3.compareTo(v2) <= 0 && v2.compareTo(v1) <= 0)) {
                        return i2;
                    } else if ((v2.compareTo(v1) <= 0 && v1.compareTo(v3) <= 0)
                            || (v3.compareTo(v1) <= 0 && v1.compareTo(v2) <= 0)) {
                        return i1;
                    } else {
                        return i3;
                    }
                default:
                    throw new IllegalArgumentException("Unknown pivot strategy");
            }
        }

    }

    private static void assertSorted(Key[] a, String sortName) {
        a[0].getComparisonCounter().lock(); // prevent counting during assertion
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1].compareTo(a[i]) > 0) {
                throw new AssertionError(sortName + " did not sort the array correctly.");
            }
        }
        a[0].getComparisonCounter().unlock();
    }

}