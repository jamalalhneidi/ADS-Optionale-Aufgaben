package zettel_7.Keys;

public class SimpleKey extends Key {
    final Integer pos;

    public SimpleKey(Integer key, Integer pos) {
        super(key);
        this.pos = pos;
    }

    @Override
    public int compareTo(Key that) {
        return comparison.count(this.key.compareTo(that.key));
    }

    @Override
    public String toString() {
        return "(" + key.toString() + ", " + pos.toString() + ")";
    }

}
