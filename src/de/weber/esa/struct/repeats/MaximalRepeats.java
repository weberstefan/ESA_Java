package de.weber.esa.struct.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stefan on 02.02.2017.
 */
public class MaximalRepeats {

    /**
     * use of child table?? (UP, DOWN, NEXT, CHILD)
     */


    private Map<Integer, List<Repeats>> maximalRepeats;

    public MaximalRepeats(final EnhancedSuffixArray esa) {
        final int n = esa.length - 1;

        int j = 1; // 0 = $
        int i = - 1;

        int l = - 1;
        int seqPosI = - 1;
        int seqPosJ = - 1;

        this.maximalRepeats = new HashMap<>();

        while (j < n) {

            if (esa.lcp.lcps[j] < esa.lcp.lcps[j + 1]) {
                // left character different
                if (esa.bwt.bwt[j] != esa.bwt.bwt[j + 1]) {
                    seqPosI = j;
                    seqPosJ = j + 1;
                    Repeats.fillMap(this.maximalRepeats, esa, seqPosI, seqPosJ, esa.lcp.lcps[j + 1]);
//                    System.out.println("(" + seqPosI + ", " + seqPosJ + ", " + esa.lcp.lcps[j + 1] + ")");

                    while (seqPosJ < n &&
                            esa.lcp.lcps[seqPosJ] < esa.lcp.lcps[seqPosJ + 1] &&
                            esa.lcp.lcps[seqPosJ + 1] > esa.lcp.lcps[seqPosJ + 2]) {
                        if (esa.bwt.bwt[seqPosJ + 1] != esa.bwt.bwt[j]) {
//                            System.out.println("(" + seqPosI + ", " + (seqPosJ + 1) + ", " + esa.lcp.lcps[seqPosI + 1] + ")");
                            Repeats.fillMap(this.maximalRepeats, esa, seqPosI, (seqPosJ + 1), esa.lcp.lcps[seqPosI + 1]);
                        }
                        seqPosJ = seqPosJ + 1;
                    }
                }

            }
            j = j + 1;
        }
    }

    public Map<Integer, List<Repeats>> getMaximalRepeats() { return this.maximalRepeats; }
}
