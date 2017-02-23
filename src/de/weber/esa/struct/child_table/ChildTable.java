package de.weber.esa.struct.child_table;

import de.weber.esa.struct.lcp.LCP;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Stefan on 13.01.2017.
 * <p>
 * Calculating the Child Table in O(n) <p>esa_child_table.pdf</p>
 */
public class ChildTable {

    /**
     * The length of the child table
     * <p>16 bit</p>
     */
    public final int length;

    /**
     * Represents the child table
     * <p>12 + (N * 4[bytes per int]) -> Round up to next multiple of 8</p>
     */
    public final int[] cld; // TODO REPRESENT AS BIT ARRAY

    /**
     * Compute the child table for a given suffix array and its corresponding LCP table
     *
     * @param lcp : LCP table for given sequence
     */
    public ChildTable(final LCP lcp) {
        // set the length N
        this.length = lcp.length;
        // initialize the child array
        this.cld = new int[this.length];

        Stack<Integer> S = new Stack();
        S.push(0);
        int last = - 1;

        for (int i = 1; i <= this.length; i = i + 1) {
            while (lcp.lcps[i] < lcp.lcps[S.lastElement()]) {
                last = S.pop();
                if (lcp.lcps[i] < lcp.lcps[S.lastElement()] &&
                        lcp.lcps[S.lastElement()] != lcp.lcps[last]) {
                    cld[S.lastElement()] = last;
                }
            }

            if (last != - 1) {
                cld[i - 1] = last;
                last = - 1;
            }

            if (lcp.lcps[S.lastElement()] == lcp.lcps[i] &&
                    S.lastElement() != 0 /* there is none for $ [first entry in suffix array] */) {
                cld[S.lastElement()] = i;
            }
            S.push(i);
        }

        S.removeAllElements();



    }

    @Override
    public String toString() {
        return "Child:\t" + Arrays.toString(this.cld);
    }

}
