package zettel_5.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class PrimeIterator implements Iterator<Integer> {
    private int current = -1;
    private static final int MAX = (int) 1e6;
    private static final ArrayList<Integer> primes;
    static {
        primes = new ArrayList<>();
        primes.add(2);
        for (int i = 3; i <= MAX; i += 2) {
            boolean isPrime = true;
            for (int p : primes) {
                if ((long) p * p > i)
                    break;
                if (i % p == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return current < primes.size();
    }

    @Override
    public Integer next() {
        return primes.get(++current);
    }

    public Integer previous() {
        return primes.get(--current);
    }

}
