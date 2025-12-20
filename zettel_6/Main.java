package zettel_6;

public class Main {
    public static void main(String[] args) {
        autoTester();
    }

    private static void autoTester() {
        System.out.println("Running auto-tester...");
        final int MAX = (int) (1e5);
        int n = (int) (Math.random() * MAX);
        System.out.println("Array size = " + n);
        int[] sortedAsc = new int[n];
        int[] sortedDesc = new int[n];
        for (int i = 0; i < n; i++) {
            sortedAsc[i] = i;
            sortedDesc[i] = n - i - 1;
        }
        int[] randomPermutation = generateRandomPermutation(n);

        String divider = "+--------------------------------+-----------------+-----------------+--------------------------------------------------------------+%n";
        System.out.format(divider);
        printRow("", "Sorted Asc", "Sorted Desc", "Random Permutation");
        System.out.format(divider);

        printRow("Insertion Sort",
                String.valueOf(Sorter.insertionSort(sortedAsc.clone())),
                String.valueOf(Sorter.insertionSort(sortedDesc.clone())),
                String.valueOf(Sorter.insertionSort(randomPermutation.clone())));

        printRow("Quicksort (Middle Pivot)",
                String.valueOf(Sorter.quicksortMiddleElement(sortedAsc.clone())),
                String.valueOf(Sorter.quicksortMiddleElement(sortedDesc.clone())),
                String.valueOf(Sorter.quicksortMiddleElement(randomPermutation.clone())));

        long mn = Long.MAX_VALUE, mx = Long.MIN_VALUE;
        double avg = 0.0;
        int trials = 100;
        for (int t = 0; t < trials; t++) {
            long count = Sorter.quicksortRandom3Element(randomPermutation.clone());
            mn = Math.min(mn, count);
            mx = Math.max(mx, count);
            avg += count;
        }
        avg /= trials;
        printRow("Quicksort (Random 3 Pivot)",
                String.valueOf(Sorter.quicksortRandom3Element(sortedAsc.clone())),
                String.valueOf(Sorter.quicksortRandom3Element(sortedDesc.clone())),
                "Min: " + mn + ", Max: " + mx + ", Avg: " + String.format("%.2f", avg));

        System.out.format(divider);
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

    private static int[] generateRandomPermutation(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int j = (int) (Math.random() * n);
            int tmp = res[i];
            res[i] = res[j];
            res[j] = tmp;
        }
        for (int i = 0; i < n; i++) {
            int j = (int) (Math.random() * n);
            int tmp = res[i];
            res[i] = res[j];
            res[j] = tmp;
        }
        return res;
    }

}
