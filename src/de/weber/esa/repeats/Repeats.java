package de.weber.esa.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.List;
import java.util.Objects;

/**
 * Created by Stefan on 01.02.2017.
 * <p>
 * This is a wrapper class for all kinds of repeats (maximal, supermaximal, ...)
 * <p>
 * <ul>
 * <li>i: starting position of first appearance in sequence</li>
 * <li>j: starting position of second appearance in sequence</li>
 * <li>l: length of current repeat</li>
 * </ul>
 */
public class Repeats {

    /**
     * Represents the starting position of first sequence's appearance of current repeat
     */
    private final int i;

    /**
     * Represents the starting position of second sequence's appearance of current repeat
     */
    private final int j;

    /**
     * Represents the length of current repeat
     */
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
     * @param list : repeats list
     * @param esa  : current esa for given sequence
     * @param i    : first chracater position of "left" repeat in sequence
     * @param j    : first character position of "right" repeat in sequence
     * @return updated repeat list
     */
    public static List<Repeats> fillList(List<Repeats> list,
                                         final EnhancedSuffixArray esa,
                                         int i,
                                         final int j,
                                         final int l) {
        if (i + 1 == j) {
            list.add(new Repeats(Math.min(esa.suffices[i], esa.suffices[j]), Math.max(esa.suffices[i], esa.suffices[j]), l));
            return list;
        }

        while (i <= j) {
            int x = i + 1;

            while (x <= j) {
                list.add(new Repeats(Math.min(esa.suffices[i], esa.suffices[x]), Math.max(esa.suffices[i], esa.suffices[x]), l));

                x = x + 1;
            }
            i = i + 1;
        }

        return list;
    }

    /**
     * check for BWT[i:j] to be pairswise distinct
     *
     * @param esa : enhanced suffix array for current sequence
     * @param i   : starting index
     * @param j   : ending index
     * @return true if BWT[i:j] pairwise distinct, else false
     */
    public static final boolean isPairwiseDistinctBwt(final EnhancedSuffixArray esa,
                                                      int i,
                                                      final int j) {
        if (i == j)
            return false;

        while (i <= j) {
            int x = i + 1;

            while (x <= j) {
                if (esa.bwt.bwt[i] == esa.bwt.bwt[x]) {
                    return false;
                }
                x = x + 1;
            }
            i = i + 1;
        }
        return true;
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
