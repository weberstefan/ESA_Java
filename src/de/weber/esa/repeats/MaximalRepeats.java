package de.weber.esa.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stefan on 02.02.2017.
 * <p>
 * Computing all maximal repeats for a given sequence
 */
public class MaximalRepeats {

    /**
     * Map storing all supermaximal repeapts (i, j, l) as list with key: length of supermaximal repeat
     */
    private Map<Integer, List<Repeats>> maximalRepeats;

    /**
     * empty constructor for call
     */
    public MaximalRepeats() {
    }

    /**
     * compute all maximal repeats using LCP
     *
     * @param esa : enhanced suffix array for given sequence
     * @return map of all supermaximal repeats with key: length; value: (i, j, l)
     */
    public Map<Integer, List<Repeats>> computeMaximalRepeats(final EnhancedSuffixArray esa) {
        final int n = esa.length - 1;

        int j = 1; // 0 = $
        int seqPosI = - 1;
        int seqPosJ = - 1;

        this.maximalRepeats = new HashMap<>();

        while (j < n) {
            // only get maximal repeats of length > 1
            if (esa.lcp.lcps[j] < esa.lcp.lcps[j + 1] &&
                    esa.lcp.lcps[j + 1] > 1) {
                // left character different
                if (esa.bwt.bwt[j] != esa.bwt.bwt[j + 1]) {
                    seqPosI = j;
                    seqPosJ = j + 1;
                    Repeats.fillMap(this.maximalRepeats, esa, seqPosI, seqPosJ, esa.lcp.lcps[j + 1]);

                    while (seqPosJ < n &&
                            esa.lcp.lcps[seqPosJ] < esa.lcp.lcps[seqPosJ + 1] &&
                            esa.lcp.lcps[seqPosJ + 1] > esa.lcp.lcps[seqPosJ + 2]) {
                        if (esa.bwt.bwt[seqPosJ + 1] != esa.bwt.bwt[j]) {
                            Repeats.fillMap(this.maximalRepeats, esa, seqPosI, (seqPosJ + 1), esa.lcp.lcps[seqPosI + 1]);
                        }
                        seqPosJ = seqPosJ + 1;
                    }
                }

            }
            j = j + 1;
        }

        Repeats.getRemainingRepeats(this.maximalRepeats);

        return this.maximalRepeats;
    }

    public Map<Integer, List<Repeats>> getMaximalRepeats() {
        return this.maximalRepeats;
    }
}
