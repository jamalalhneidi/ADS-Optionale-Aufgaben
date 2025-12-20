package zettel_7;

import zettel_7.Keys.ExtendedKey;
import zettel_7.Keys.SimpleKey;

public class Main {
    public static void main(String[] args) {
        autoTester();
    }

    private static void autoTester() {
        System.out.println("Running auto-tester...");
        final int MAX = (int) (1e2 / 2);
        // int n = (int) (Math.random() * MAX);
        int n = 10;

        System.out.println("Array SimpleKey & ExtendedKey of size: " + n);

        String divider = "+--------------------------------+-----------------+-----------------+--------------------------------------------------------------+%n";

        SimpleKey[] simpleKeys = new SimpleKey[n];
        ExtendedKey[] extendedKeys = new ExtendedKey[n];
        for (int i = 0; i < simpleKeys.length; i++) {
            simpleKeys[i] = new SimpleKey(i < n / 2 ? 0 : 1, i);
            extendedKeys[i] = new ExtendedKey(i < n / 2 ? 0 : 1, i);
        }

        System.out.println("SimpleKeys before sorting:");
        for (int i = 0; i < simpleKeys.length; i++) {
            System.out.print(simpleKeys[i] + ", ");
        }
        System.out.println();

        System.out.format(divider);
        printRow("", "Simple Key", "Extended Key", "");
        System.out.format(divider);

        printRow("Insertion Sort",
                String.valueOf(Sorter.insertionSort(simpleKeys.clone())),
                String.valueOf(Sorter.insertionSort(extendedKeys.clone())), "");

        printRow("Quicksort (Middle Pivot)",
                String.valueOf(Sorter.quicksortMiddleElement(simpleKeys.clone())),
                String.valueOf(Sorter.quicksortMiddleElement(extendedKeys.clone())), "");

        printRow("Heapsort",
                String.valueOf(Sorter.heapsort(simpleKeys)),
                String.valueOf(Sorter.heapsort(extendedKeys)), "");
        System.out.format(divider);

        System.out.println("SimpleKeys after sorting:");
        for (int i = 0; i < simpleKeys.length; i++) {
            System.out.print(simpleKeys[i] + ", ");
        }
        System.out.println();
        System.out.println(
                "In the case of ExtendedKeys, the number of comparisons is higher becuase we run more compaisons to break ties when keys are equal.");
        System.out.println("Heapsort is also not stable.");
    }

    private static void printRow(String col1, String col2, String col3, String col4) {
        String format = "| %-30s | %-15s | %-15s | %-60s |%n";
        System.out.format(format,
                truncate(col1, 30),
                truncate(col2, 15),
                truncate(col3, 15),
                truncate(col4, 60));
    }

    private static String truncate(String text, int length) {
        if (text.length() <= length)
            return text;
        return text.substring(0, length - 3) + "...";
    }

}
