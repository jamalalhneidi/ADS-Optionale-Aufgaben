package zettel_5;

import java.util.ArrayList;
import java.util.LinkedList;

import zettel_5.utils.MyLogger;
import zettel_5.utils.PrimeIterator;

/**
 * Keys and values are always strings in this implemenation.
 * Table resizes itself when load factor > MAX_LOAD_FACTOR.
 **/
public class HashTable {

    public class Bucket {
        private final String key;
        private LinkedList<String> values;

        public Bucket(String key) {
            this.key = key;
            this.values = new LinkedList<>();
        }

        public String getKey() {
            return key;
        }

        public LinkedList<String> getValues() {
            return values;
        }

        public void addValue(String value) {
            this.values.add(value);
        }

    }

    private PrimeIterator primeIterator = new PrimeIterator(); // for resizing

    private static final double MIN_LOAD_FACTOR = 0.5;
    private static final double MAX_LOAD_FACTOR = 0.75;
    private static final int P = (int) (1e9 + 7);
    private final int a, b; // for universal hashing
    private int n = 0; // number of elements in the table
    private int m; // size of the table

    {
        primeIterator.next(); // skip 2, cuz for hashing we use (% m - 2) in h2
        m = primeIterator.next(); // initial size 3
    }

    private ArrayList<Bucket> table;
    private String desc;

    public HashTable(String desc) {
        this.desc = desc;
        table = new ArrayList<Bucket>();
        for (int i = 0; i < m; i++) {
            table.add(null);
        }
        this.a = (int) (Math.random() * (P / 10)) + 1;
        this.b = (int) (Math.random() * (P / 10)) + 1;
        // this.a = 597484171;
        // this.b = 92602245;
        MyLogger.getInstance().logln("Initialized Hashtable \"" + desc + "\": a = " + a + ", b = " + b);
    }

    public void put(String key, String value) {
        int i = hash(key);
        if (table.get(i) == null) {
            table.set(i, new Bucket(key));
            n++;
        }
        table.get(i).addValue(value);
        grow();
    }

    public LinkedList<String> get(String key) {
        int i = hash(key);
        Bucket bucket = table.get(i);
        return bucket == null ? null : bucket.getValues();
    }

    public String getFirst(String key) {
        LinkedList<String> list = get(key);
        return list == null ? null : list.getFirst();
    }

    public int size() {
        return n;
    }

    /*
     * ================
     * Internal private methods
     * ================
     */

    private int hash(String key) {
        int x = rabinKarpHash(key);
        int h1 = h1(x);
        int index = h1;
        boolean first = true;

        // The condition checks for either an empty bucket or a matching key
        // Case 1: Empty bucket -> Player to Club mapping (Player names are unique)
        // Case 2: Matching key -> Club to Players mapping (multiple players per club)
        // This makes this implementation kinda hybrid between closed and open hashing
        // Prolly not ideal in real-world scenarios, but works well for this task
        for (int i = 0; table.get(index) != null && !table.get(index).getKey().equals(key); i = (i + 1) % m) {
            index = (int) (h1 + h2(x, i)) % m;
            if (!first && i == 0) // we circled back to the start
                throw new RuntimeException("HashTable \"" + desc + "\" is full, cannot insert key " + key);
            first = false;
        }
        assert table.get(index) == null || table.get(index).getKey().equals(key);
        return index;

    }

    private int h1(int x) {
        return (int) (((long) a * x % P + b) % P) % m;
    }

    private int h2(int x, int i) {
        return i * (1 + x % (m - 2)) % m;
    }

    private int rabinKarpHash(String key) {
        final long D = 256; // Extended ASCII character set
        long hash = 0;
        for (char c : key.toCharArray()) {
            hash = (D * hash % P + c) % P;
        }
        return (int) (hash % m);
    }

    private void grow() {
        double loadFactor = (double) n / m;
        if (loadFactor <= MAX_LOAD_FACTOR)
            return;

        MyLogger.getInstance()
                .logln("Resizing table \"" + desc + "\" n =  " + n + ", m =  " + m + " load factor = " + loadFactor);

        // We need to make a new table, cuz with the change of the table size
        // the hash function calculations will also change
        // So we need to rehash all existing entries

        // updating m before updating the table is important
        // cuz it affects the calculations of the hash function
        while ((double) n / m >= MIN_LOAD_FACTOR) {
            if (!primeIterator.hasNext())
                throw new RuntimeException("HashTable \"" + desc + "\" cannot grow anymore, reached max limit.");
            m = primeIterator.next();
        }
        // after the loop we crossed the min limit we need to go a step back
        m = primeIterator.previous();

        ArrayList<Bucket> oldTable = table;
        table = new ArrayList<Bucket>();
        for (int i = 0; i < m; i++) {
            table.add(null);
        }
        for (Bucket bucket : oldTable) {
            if (bucket != null) {
                int i = hash(bucket.getKey());
                table.set(i, bucket);
            }
        }
        MyLogger.getInstance()
                .logln("Resized table \"" + desc + "\" to new size m = " + m + " new load factor = " + (double) n / m);
    }

    public void debugTable() {
        MyLogger.getInstance().logln("HashTable " + desc + " Debug Info:");
        System.out.println(
                "[" + desc + "] " + " Number of elements n = " + n + ", Table size m = " + m + ", Load factor = "
                        + ((double) n / m));
        for (int i = 0; i < table.size(); i++) {
            Bucket bucket = table.get(i);
            if (bucket != null) {
                MyLogger.getInstance().log("Index " + i + ": Key = " + bucket.getKey() + ", Values = ");
                for (String value : bucket.getValues()) {
                    MyLogger.getInstance().log(value + ", ");
                }
                MyLogger.getInstance().logln("");
            } else {
                MyLogger.getInstance().logln("Index " + i + ": null");
            }
        }
    }

}
