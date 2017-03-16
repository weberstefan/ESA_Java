package de.weber.esa.searching.fmIndex;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.bwt.FMIndex;

/**
 * Created by Stefan on 16.03.2017.
 *
 * Compute the FM-Index for a given ESA and query in O(n * m)
 */
public class FMIndexSearch {

    /**
     * empty constructor
     */
    public FMIndexSearch() {
    }

    /**
     * calculating the left and right bounds in the enhanced suffix array
     * for given query pattern
     * <p>computing time: O(n*m) with n=length of suffix array and m=length of query</p>
     *
     * @param esa   : enhanced suffix array for given string
     * @param query : pattern to be searched whether it is inside the string and if so at what suffix array positions
     * @return left and right indices in suffix array for query pattern
     */
    public FMIndex backwardSearch(final EnhancedSuffixArray esa,
                                  final String query) {
        final int m = query.length();
        final int n = esa.length - 1;

        int l = 0;
        int r = n;

        for (int i = m; i > 0; i = i - 1) {
            final char p = query.charAt(i - 1);
            int posP;
            try {
                posP = esa.bwtCMap.get(p).getPosSequence();
            } catch (NullPointerException eNP) {
                System.err.println(p + " is not in the sequence, so no pattern will be found..");
                return new FMIndex(- 1, - 1);
            }
            l = posP + this.getOCCValue(esa, p, l - 1);
            r = posP + this.getOCCValue(esa, p, r) - 1;

            if (l > r) {
                return new FMIndex(- 1, - 1);
            }
        }

        return new FMIndex(l, r);
    }

    /**
     * get the times that character p appeared until now in the BWT
     *
     * @param esa : enhanced suffix array for given string
     * @param p   : current character to be checked
     * @param k   : current (left -1) or (right) position to be checked
     * @return times p appeared in BWT until k
     */
    private final int getOCCValue(final EnhancedSuffixArray esa,
                                  final char p,
                                  final int k) {
        if (k < 0) {
            return 0;
        }

        final int pos = esa.bwtCMap.get(p).getPosMap();
        int q = k;
        int result = 0;
        while (q >= 0) {
            result = result + ((int) esa.bwt.OCC[q][pos]);
            q = q - 1;
        }

        return result;
    }

}
