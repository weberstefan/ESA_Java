package de.weber.esa.struct.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.bwt.BWT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stefan on 01.02.2017.
 * <p>
 * Compute all supermaximal repeats using LCP and BWT
 */
public class SupermaximalRepeats {

    /**
     * Map storing all supermaximal repeapts (i, j, l) as list with key: length of supermaximal repeat
     */
    private Map<Integer, List<Repeats>> supermaximalRepeats;

    /**
     * Computing all supermaximal repeats in O(n);
     * <p>
     * - [i..j] is a local maximum in the lcp table
     * - characters bwt[i], bwt[i+1], ..., bwt[j] are pairwise distinct
     *
     * @param esa : enhanced suffix array for given sequence
     */
    public SupermaximalRepeats(final EnhancedSuffixArray esa) {
        final int n = esa.length - 1;

        this.supermaximalRepeats = new HashMap<>();

        // lcp[0] = -1 == $ --> not necessary
        int j = 1;
        int i = - 1;
        int i2 = - 1;
        int j2 = - 1;

        while (j < n) {
            i = j;
            while (j < n &&
                    esa.lcp.lcps[j] <= esa.lcp.lcps[j + 1]) {
                if (j < n &&
                        (esa.lcp.lcps[j] == 0 &&
                                esa.lcp.lcps[j + 1] == 0) ||
                        (j < n - 1 && esa.lcp.lcps[i] < esa.lcp.lcps[j + 1] &&
                                esa.lcp.lcps[j + 1] < esa.lcp.lcps[j + 2])) {
                    i = j + 1;
                }
                j = j + 1;
                if (j < n &&
                        (esa.lcp.lcps[j] == esa.lcp.lcps[j + 1] &&
                                esa.lcp.lcps[j] != 0) ||
                        (esa.lcp.lcps[j - 1] == esa.lcp.lcps[j - 2] &&
                                esa.lcp.lcps[j - 1] != 0)) {
                    i2 = i;
                    j2 = j;
                    if (this.isBWTPairwiseDistinct(esa.bwt, i2, j2)) {
                        Repeats.fillMap(this.supermaximalRepeats, esa, i2, j2, esa.lcp.lcps[j2]);
                    }
                    i = j - 1;
                }
            }

            if (i2 != i || j2 != j) {
                if (this.isBWTPairwiseDistinct(esa.bwt, i, j)) {
                    Repeats.fillMap(this.supermaximalRepeats, esa, i, j, esa.lcp.lcps[j]);
                }
            }

//            j = j + 1;
            j = i + 1;
        }

        for (int k : this.supermaximalRepeats.keySet()) {
            for (int p = 0; p < this.supermaximalRepeats.get(k).size(); p = p + 1) {
                if ((p + 1) < this.supermaximalRepeats.get(k).size() &&
                        this.supermaximalRepeats.get(k).get(p).getJ() == this.supermaximalRepeats.get(k).get(+ 1).getI()) {
                    this.supermaximalRepeats.get(k).add(new Repeats(this.supermaximalRepeats.get(k).get(p).getI(), this.supermaximalRepeats.get(k).get(p + 1).getJ(), k));
                }
            }
        }
    }

    /**
     * check for BWT[i:j] to be pairswise distinct
     *
     * @param bwt : BWT for sequence
     * @param i   : starting index
     * @param j   : ending index
     * @return true if BWT[i:j] pairwise distinct, else false
     */
    private final boolean isBWTPairwiseDistinct(final BWT bwt,
                                                final int i,
                                                final int j) {
        if (i == j) {
            return false;
        }
        for (int k = i; k < j; k = k + 1) {
            if (bwt.bwt[k] == bwt.bwt[k + 1]) {
                return false;
            }
        }

        return true;
    }

    public Map<Integer, List<Repeats>> getSupermaximalRepeats() {
        return this.supermaximalRepeats;
    }

}