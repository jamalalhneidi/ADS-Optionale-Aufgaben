package zettel_7.Keys;

public class ExtendedKey extends SimpleKey {

    public ExtendedKey(Integer key, Integer pos) {
        super(key, pos);
    }

    @Override
    public int compareTo(Key that) {
        int cmp = super.compareTo(that); // super is responsible for counting its comparisons
        if (cmp != 0)
            return cmp;
        return comparison.count(this.pos.compareTo(((ExtendedKey) that).pos));
    }

}
