package de.weber.esa.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Stefan on 16.05.2017.
 */
public class MaxRepPsvNsv {

    public static void main(String[] args) {
//        final String s = "ACAAACATAT";
        final String s = "XABCYABCWABCYZ";
//        final String s = "MISSISSIPPI";
//        final String s = "ASKDNBUBDIJFNCASIDBUBABDFJKCASNCIUDBFJKALSNCIBDFUIQOMDOANSICBNDIFNQWWIODNASJDNASDNBAIBFQNIWOMNCID";
        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        final int minSizeMaxRepeats = 2;

        MaximalRepeats maximalRepeats = new MaximalRepeats();
        System.out.println(maximalRepeats.computeMaximalRepeats(esa, minSizeMaxRepeats));

        MaxRepPsvNsv maxRepChildTest = new MaxRepPsvNsv();
        System.out.println(maxRepChildTest.maxRep(esa, minSizeMaxRepeats));

    }

    private int[] P;
    private int[] N;

    private List<Repeats> repeatsList;

    public MaxRepPsvNsv() {
    }

    public List<Repeats> maxRep(final EnhancedSuffixArray esa,
                                 final int minSizeMaxRepeats) {
        this.fillPsvNsv(esa);
        this.repeatsList = new ArrayList<>();
        int i = 1;
        while (i < esa.length) {
            if (this.P[i - 1] != 0) {
                int nsv = this.N[i - 1];
                int psv = this.P[nsv - 2];

                if (esa.bwt.bwt[nsv == esa.length ? esa.length - 1 : nsv] != esa.bwt.bwt[psv] &&
                        esa.bwt.bwt[i] != esa.bwt.bwt[i - 1] &&
                        esa.lcp.getCurrentLcpValue(i) >= esa.lcp.getCurrentLcpValue(i - 1)) {

                    if (esa.lcp.getCurrentLcpValue(i) >= minSizeMaxRepeats) {
//                        System.out.print(i + ":" + (i - 1));
//                        System.out.println("\t" + esa.lcp.getCurrentLcpValue(i));

                        Repeats.fillList(this.repeatsList, esa, i, i - 1, esa.lcp.getCurrentLcpValue(i));
                    }

                    int j = i + 1;
                    while (j < nsv &&
                            esa.bwt.bwt[i - 1] != esa.bwt.bwt[j] &&
                            esa.lcp.getCurrentLcpValue(i) <= esa.lcp.getCurrentLcpValue(j) &&
                            esa.lcp.getCurrentLcpValue(i) > esa.lcp.getCurrentLcpValue(j + 1)) {

                        if (esa.lcp.getCurrentLcpValue(i) >= minSizeMaxRepeats) {
//                            System.out.println((i - 1) + ":" + j + " - " + esa.lcp.getCurrentLcpValue(i));

                            Repeats.fillList(this.repeatsList, esa, i - 1, j, esa.lcp.getCurrentLcpValue(i));
                        }
                        j = j + 1;
                    }
                }
            }
            i = i + 1;
        }

        return this.repeatsList;
    }

    private void fillPsvNsv(final EnhancedSuffixArray esa) {
        // first entry to be validated!!
        Stack<Integer> S = new Stack<>();
        P = new int[esa.length];
        N = new int[esa.length];
        for (int i = 1; i < esa.length; i = i + 1) {
            while (! S.empty() && esa.lcp.getCurrentLcpValue(S.peek()) > esa.lcp.getCurrentLcpValue(i)) {
                N[S.pop() - 1] = i;
            }
            S.push(i);
        }
        while (! S.empty()) {
            N[S.pop() - 1] = esa.length;
        }

        S.removeAllElements();
        for (int i = esa.length - 1; i > 0; i = i - 1) {
            while (! S.empty() && esa.lcp.getCurrentLcpValue(S.peek()) > esa.lcp.getCurrentLcpValue(i)) {
                P[S.pop() - 1] = i;
            }
            S.push(i);
        }
        while (! S.empty()) {
            P[S.pop() - 1] = 0;
        }
    }


}
