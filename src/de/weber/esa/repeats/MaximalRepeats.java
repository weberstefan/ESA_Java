package de.weber.esa.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 02.02.2017.
 * <p>
 * Computing all maximal repeats for a given sequence
 */
public class MaximalRepeats {

    /**
     * List storing all supermaximal repeapts (i, j, l) with l = length of supermaximal repeat
     */
    private List<Repeats> maximalRepeats;

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
    public List<Repeats> computeMaximalRepeats(final EnhancedSuffixArray esa,
                                               final int minSizeMaxRepeats) {
        final int n = esa.length - 1;

        int j = 1; // 0 = $
        int seqPosI = - 1;
        int seqPosJ = - 1;

        this.maximalRepeats = new ArrayList<>();

        while (j < n) {

            if (esa.lcp.getCurrentLcpValue(j) < esa.lcp.getCurrentLcpValue(j + 1)) {
                // left character different
                if (esa.bwt.bwt[j] != esa.bwt.bwt[j + 1]) {
                    seqPosI = j;
                    seqPosJ = j + 1;
                    if (esa.lcp.getCurrentLcpValue(j + 1) >= minSizeMaxRepeats) {
                        Repeats.fillList(this.maximalRepeats, esa, seqPosI, seqPosJ, esa.lcp.getCurrentLcpValue(j + 1));
                    }

                    while (seqPosJ < n &&
                            esa.lcp.getCurrentLcpValue(seqPosJ) <= esa.lcp.getCurrentLcpValue(seqPosJ + 1) &&
                            esa.lcp.getCurrentLcpValue(seqPosJ + 2) <= esa.lcp.getCurrentLcpValue(seqPosJ + 1)) {
                        if (esa.bwt.bwt[seqPosJ + 1] != esa.bwt.bwt[j] &&
                                esa.lcp.getCurrentLcpValue(seqPosI + 1) >= minSizeMaxRepeats) {
                            Repeats.fillList(this.maximalRepeats, esa, seqPosI, (seqPosJ + 1), esa.lcp.getCurrentLcpValue(seqPosI + 1));
                        }
                        seqPosJ = seqPosJ + 1;
                    }
                }

            }
            j = j + 1;
        }

        return this.maximalRepeats;
    }

    public List<Repeats> getMaximalRepeats() {
        return this.maximalRepeats;
    }
}