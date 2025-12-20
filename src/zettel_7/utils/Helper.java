package zettel_7.utils;

public class Helper {

    public static <Key extends Comparable<Key>> void swap(Key a[], int l, int r) {
        Key tmp = a[l];
        a[l] = a[r];
        a[r] = tmp;
    }

    public static int parent(int i) {
        return i >> 1;
    }

    public static int leftChild(int i) {
        return (i << 1) + 1;
    }

    public static int rightChild(int i) {
        return leftChild(i) + 1;
    }
}
