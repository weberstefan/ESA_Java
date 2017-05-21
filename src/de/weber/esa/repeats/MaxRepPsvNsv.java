package de.weber.esa.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.psvnsv.PsvNsv;

import java.util.ArrayList;
import java.util.List;

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

        MaxRepPsvNsv maxRepChildTest = new MaxRepPsvNsv(esa);
        System.out.println(maxRepChildTest.maxRep(esa, minSizeMaxRepeats));

    }

    private List<Repeats> repeatsList;
    private PsvNsv psvNsv;

    public MaxRepPsvNsv(final EnhancedSuffixArray esa) {
        this.psvNsv = new PsvNsv(esa);
    }

    public List<Repeats> maxRep(final EnhancedSuffixArray esa,
                                 final int minSizeMaxRepeats) {
        this.repeatsList = new ArrayList<>();
        int i = 1;
        while (i < esa.length) {
            if (psvNsv.psv[i - 1] != 0) {
                int nsv = psvNsv.nsv[i - 1];
                int psv = psvNsv.psv[nsv - 2];

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

}
