package zettel_7.Keys;

import zettel_7.ComparisonCounter;

public class Key implements Comparable<Key> {
    Integer key;
    ComparisonCounter comparison;

    public Key(Integer key) {
        this.key = key;
    }

    public ComparisonCounter getComparisonCounter() {
        return comparison;
    }

    public void setComparisonCounter(ComparisonCounter comparison) {
        this.comparison = comparison;
    }

    @Override
    public int compareTo(Key that) {
        return comparison.count(this.key.compareTo(that.key));
    }

    @Override
    public String toString() {
        return key.toString();
    }

}
