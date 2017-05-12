package de.weber.esa.struct.childtable;

import de.weber.esa.struct.lcp.LCP;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Stefan on 13.01.2017.
 * <p>
 * Calculating the Child Table in O(n) <p>esa_child_table.pdf</p>
 */
public class ChildTable {

    /*
        Computing the Child Table with THashMap for LCP Exception
     */

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

    public final boolean[] down;
    public final boolean[] next;

    /**
     * Compute the child table for a given suffix array and its corresponding LCP table
     *
     * @param lcp : LCP table for given sequence
     */
    public ChildTable(final LCP lcp) {
        // set the length N
        this.length = lcp.length;
        // initialize the arrays
        this.cld = new int[this.length];
        this.down = new boolean[this.length];
//        this.up = new boolean[this.length];
        this.next = new boolean[this.length];

        Stack<Integer> S = new Stack();
        S.push(0);
        int last = - 1;

        for (int k = 1; k <= this.length; k = k + 1) {
            while (lcp.getCurrentLcpValue(k) < lcp.getCurrentLcpValue(S.lastElement())) {
                last = S.pop();

                if (lcp.getCurrentLcpValue(k) <= lcp.getCurrentLcpValue(S.lastElement()) &&
                        lcp.getCurrentLcpValue(S.lastElement()) != lcp.getCurrentLcpValue(last)) {
                    this.cld[S.lastElement()] = last; /* DOWN */
                    this.down[S.lastElement()] = true;
                }
            }

            if (last != - 1) {
                this.cld[k - 1] = last;
                last = - 1;
            }

            if (lcp.getCurrentLcpValue(S.lastElement()) == lcp.getCurrentLcpValue(k) &&
                    S.lastElement() != 0 /* there is none for $ (first entry in suffix array */) {
                this.cld[S.lastElement()] = k;
                this.next[S.lastElement()] = true;
            }

            S.push(k);
        }

        S.removeAllElements();

    }

    @Override
    public String toString() {
        return "Child:\t" + Arrays.toString(this.cld)
                + "\nDOWN: " + Arrays.toString(this.down) +
//                "\nUP:   " + Arrays.toString(this.up) +
                "\nNext: " + Arrays.toString(this.next);
    }

}
