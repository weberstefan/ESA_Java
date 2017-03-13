package de.weber.esa.struct.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Stefan on 01.02.2017.
 * <p>
 * This is a wrapper class for all kinds of repeats (maximal, supermaximal)
 */
public class Repeats {

    private final int i;
    private final int j;
    private final int l;

    public Repeats(final int i,
                   final int j,
                   final int l) {
        this.i = i;
        this.j = j;
        this.l = l;
    }

    /**
     * insert repeat into map at key=length
     *
     * @param map : repeats map
     * @param esa : current esa for given sequence
     * @param i   : first chracater position of "left" repeat in sequence
     * @param j   : first character position of "right" repeat in sequence
     * @return updated repeat map
     */
    public static Map<Integer, List<Repeats>> fillMap(Map<Integer, List<Repeats>> map,
                                                      final EnhancedSuffixArray esa,
                                                      final int i,
                                                      final int j,
                                                      final int l) {
        int seqPosI = Math.min(esa.suffices[i], esa.suffices[j]);
        int seqPosJ = Math.max(esa.suffices[i], esa.suffices[j]);

        List<Repeats> r = null;

        if (map.containsKey(l)) {
            r = map.get(l);
        } else {
            r = new ArrayList<>();
        }
        r.add(new Repeats(seqPosI, seqPosJ, l));
        map.put(l, r);
        return map;
    }

    public final int getI() {
        return this.i;
    }

    public final int getJ() {
        return this.j;
    }

    public final int getL() {
        return this.l;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (! (o instanceof Repeats)) {
            return false;
        }

        final Repeats r = (Repeats) o;
        return (r.i == this.i &&
                r.j == this.j &&
                r.l == this.l);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.i, this.j, this.l);
    }

    @Override
    public String toString() {
        return "(" + this.i + "," + this.j + "," + this.l + ")";
    }

}
