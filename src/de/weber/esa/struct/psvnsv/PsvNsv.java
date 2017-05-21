package de.weber.esa.struct.psvnsv;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Stefan on 21.05.2017.
 */
public class PsvNsv {

    public static void main(String[] args) {
        final String s = "CAGCAACTGCAGT";
        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        PsvNsv psvNsv = new PsvNsv(esa);
        System.out.println(psvNsv.toString());
    }

    public final int[] psv;
    public final int[] nsv;

    public PsvNsv(final EnhancedSuffixArray esa) {
        this.psv = new int[esa.length];
        this.nsv = new int[esa.length];

        this.computePsvNsv(esa);
    }

    private void computePsvNsv(final EnhancedSuffixArray esa) {
        // first entry to be validated!!
        Stack<Integer> S = new Stack<>();
        for (int i = 1; i < esa.length; i = i + 1) {
            while (! S.empty() && esa.lcp.getCurrentLcpValue(S.peek()) > esa.lcp.getCurrentLcpValue(i)) {
                this.nsv[S.pop()] = i;
            }
            S.push(i);
        }
        while (! S.empty()) {
            this.nsv[S.pop()] = esa.length;
        }

        S.removeAllElements();
        for (int i = esa.length - 1; i > 0; i = i - 1) {
            while (! S.empty() && esa.lcp.getCurrentLcpValue(S.peek()) > esa.lcp.getCurrentLcpValue(i)) {
                this.psv[S.pop()] = i;
            }
            S.push(i);
        }
        while (! S.empty()) {
            this.psv[S.pop()] = 0;
        }
    }

    @Override
    public String toString() {
        return "PSV: " + Arrays.toString(this.psv) + "\n" +
                "NSV: " + Arrays.toString(this.nsv);
    }

}
