package de.weber.esa.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 01.02.2017.
 * <p>
 * Compute all supermaximal repeats using LCP and BWT
 */
public class SupermaximalRepeats {

    /**
     * List storing all supermaximal repeapts (i, j, l) with l = length of supermaximal repeat
     */
    private List<Repeats> supermaximalRepeats;

    /**
     * empty constructor for call
     */
    public SupermaximalRepeats() {
    }

    /**
     * Computing all supermaximal repeats in O(n + |supermaximal repeats|);
     * <p>
     * - [i..j] is a local maximum in the lcp table
     * - characters bwt[i], bwt[i+1], ..., bwt[j] are pairwise distinct
     *
     * @param esa : enhanced suffix array for given sequence
     * @return map of all supermaximal repeats with key: length; value: (i, j, l)
     */
    public List<Repeats> computeSupermaximalRepeats(final EnhancedSuffixArray esa,
                                                    final int minSizeMaxRepeats) {
        final int n = esa.length - 1;

        this.supermaximalRepeats = new ArrayList<>();

        // lcp[0] = -1 == $ --> not necessary
        int j = 1;
        int i = - 1;
        int i2 = - 1;
        int j2 = - 1;

        while (j < n) {
            i = j;
            // only get supermaximal repeats of length > 1
            while (j < n &&
                    esa.lcp.getCurrentLcpValue(j) <= esa.lcp.getCurrentLcpValue(j + 1) &&
                    esa.lcp.getCurrentLcpValue(j + 1) > 1) {
                if (j < n &&
                        (esa.lcp.getCurrentLcpValue(j) == 0 &&
                                esa.lcp.getCurrentLcpValue(j + 1) == 0) ||
                        (j < n - 1 && esa.lcp.getCurrentLcpValue(i) < esa.lcp.getCurrentLcpValue(j + 1) &&
                                esa.lcp.getCurrentLcpValue(j + 1) < esa.lcp.getCurrentLcpValue(j + 2))) {
                    i = j + 1;
                }
                j = j + 1;
                if (j < n &&
                        (esa.lcp.getCurrentLcpValue(j) == esa.lcp.getCurrentLcpValue(j + 1) &&
                                esa.lcp.getCurrentLcpValue(j) != 0) ||
                        (esa.lcp.getCurrentLcpValue(j - 1) == esa.lcp.getCurrentLcpValue(j - 2) &&
                                esa.lcp.getCurrentLcpValue(j - 1) != 0)) {
                    i2 = i;
                    j2 = j;
                    if (Repeats.isPairwiseDistinctBwt(esa, i2, j2) &&
                            esa.lcp.getCurrentLcpValue(j2) >= minSizeMaxRepeats) {
                        Repeats.fillList(this.supermaximalRepeats, esa, i2, j2, esa.lcp.getCurrentLcpValue(j2));
                    }
                    i = j - 1;
                }
            }

            if (i2 != i || j2 != j) {
                if (Repeats.isPairwiseDistinctBwt(esa, i, j) &&
                        esa.lcp.getCurrentLcpValue(j) >= minSizeMaxRepeats) {
                    Repeats.fillList(this.supermaximalRepeats, esa, i, j, esa.lcp.getCurrentLcpValue(j));
                }
            }

            j = i + 1;
        }

        return this.supermaximalRepeats;
    }

//
//    private final boolean isBWTPairwiseDistinct(final BWT bwt,
//                                                int i,
//                                                final int j) {
//        if (i == j)
//            return false;
//
//        while (i <= j) {
//            int x = i + 1;
//
//            while (x <= j) {
//                if (bwt.bwt[i] == bwt.bwt[x]) {
//                    return false;
//                }
//                x = x + 1;
//            }
//            i = i + 1;
//        }
//        return true;
//    }

    public List<Repeats> getSupermaximalRepeats() {
        return this.supermaximalRepeats;
    }

}
