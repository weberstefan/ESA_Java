package de.weber.esa.struct.child_table;

import de.weber.esa.struct.lcp.LCP;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Stefan on 22.01.2017.
 * <p>
 * Compute the Child Table as in Heun Script page 213
 */
public class ChildTable2 {

    public final int[] DOWN;
    public final int[] UP;
    public final int[] NEXT;
    public final int[] C;

    private final int n;

    public ChildTable2(final LCP lcp) {
        this.n = lcp.length + 1;
        this.UP = new int[this.n];
        this.DOWN = new int[this.n];
        this.NEXT = new int[this.n];
        this.C = new int[this.n - 1];

        int last = - 1;
        Stack<Integer> S = new Stack<>();
        S.push(0);

        for (int k = 1; k < this.n; k = k + 1) {
            while (lcp.lcps[k] < lcp.lcps[S.lastElement()]) {
                last = S.pop();
                if ((lcp.lcps[k] <= lcp.lcps[S.lastElement()]) &&
                        (lcp.lcps[S.lastElement()] < lcp.lcps[last]) &&
                        S.lastElement() != 0 /* there is none for $ [first entry in suffix array] */ ) {
                    this.DOWN[S.lastElement()] = last;
                }
            }

            // Now lcp[k] >= lcp[S.top()]
            if (last != - 1) {
                this.UP[k] = last;
                last = - 1;
            }

            S.push(k);
        }

        S.removeAllElements();
        S.push(0);
        for (int k = 0; k < this.n; k = k + 1) {
            while (lcp.lcps[k] < lcp.lcps[S.lastElement()]) {
                S.pop();
            }
            if (lcp.lcps[k] == lcp.lcps[S.lastElement()]) {
                last = S.pop();
                if (last != 0 /* there is none for $ [first entry in suffix array] */ ) {
                    this.NEXT[last] = k;
                }
            }
            S.push(k);
        }

        for (int i = 0; i < this.n - 1; i = i + 1) {
            if (this.NEXT[i] != 0) {
                this.C[i] = this.NEXT[i];
            } else if (this.DOWN[i] != 0) {
                this.C[i] = this.DOWN[i];
            } else {
                this.C[i] = this.UP[i + 1];
            }
        }

//        System.out.println("DOWN: " + Arrays.toString(this.DOWN));
//        System.out.println("UP  : " + Arrays.toString(this.UP));
//        System.out.println("NEXT: " + Arrays.toString(this.NEXT));
//        System.out.println("C   : " + Arrays.toString(this.C));
    }

    @Override
    public String toString() {
        return "DOWN: " + Arrays.toString(this.DOWN) + "\nUP  : " + Arrays.toString(this.UP) + "\nNEXT: " + Arrays.toString(this.NEXT) + "\nC   : " + Arrays.toString(this.C);
    }

}